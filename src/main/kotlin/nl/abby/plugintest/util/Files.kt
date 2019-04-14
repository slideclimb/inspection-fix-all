package nl.abby.plugintest.util

import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.GlobalSearchScope
import nl.abby.plugintest.algorithm.IsChildDFS
import nl.abby.plugintest.file.LatexFileType
import nl.abby.plugintest.index.LatexCommandsIndex
import nl.abby.plugintest.psi.LatexCommands
import java.util.*
import kotlin.collections.HashSet

/**
 * Looks up the PsiFile that corresponds to the Virtual File.
 */
fun VirtualFile.psiFile(project: Project): PsiFile? = PsiManager.getInstance(project).findFile(this)

/**
 * Looks for a certain file relative to this directory.
 *
 * First looks if the file including extensions exists, when it doesn't it tries to append all
 * possible extensions until it finds a good one.
 *
 * @param fileName
 *         The name of the file relative to the directory.
 * @param extensions
 *         Set of all supported extensions to look for.
 * @return The matching file, or `null` when the file couldn't be found.
 */
fun VirtualFile.findFile(fileName: String, extensions: Set<String>): VirtualFile? {
    var file = findFileByRelativePath(fileName)
    if (file != null) return file

    extensions.forEach { extension ->
        val lookFor = if (fileName.endsWith(".$extension")) fileName else "$fileName.$extension"
        file = findFileByRelativePath(lookFor)

        if (file != null) return file
    }

    return null
}

/**
 * Recursively finds all files in a directory (thus, also the files in sub-directories etc.)
 */
fun VirtualFile.allChildFiles(): Set<VirtualFile> {
    val set = HashSet<VirtualFile>()
    allChildFiles(set)
    return set
}

/**
 * Recursive implementation of [allChildFiles].
 */
private fun VirtualFile.allChildFiles(files: MutableSet<VirtualFile>) {
    if (isDirectory) {
        children.forEach {
            it.allChildFiles(files)
        }
    }
    else files.add(this)
}

/**
 * Scans all file inclusions and finds the file that is at the base of all inclusions.
 *
 * When no file is included, `this` file will be returned.
 */
fun PsiFile.findRootFile(): PsiFile {
    if (LatexCommandsIndex.getItems(this).any { "\\documentclass" == it.name }) {
        return this
    }

    val inclusions = project.allFileinclusions()
    inclusions.forEach { (file, _) ->
        // For each root, IsChildDFS until found.
        if (!file.isRoot()) {
            return@forEach
        }

        if (file.isIncludedBy(file, inclusions)) {
            return file
        }
    }

    return this
}

/**
 * Checks if the given file is included by `this` file.
 *
 * @param childMaybe
 *              The file to check for if it is a child of this file.
 * @param mapping
 *              Map that maps each psi file to all the files that get included by said file.
 * @return `true` when `childMaybe` is a child of `this` file, `false` otherwise.
 */
private fun PsiFile.isIncludedBy(childMaybe: PsiFile, mapping: Map<PsiFile, Set<PsiFile>>): Boolean {
    return IsChildDFS(
            this,
            { mapping[it] ?: emptySet() },
            { childMaybe == it }
    ).execute()
}

/**
 * Looks up all the files that include each other.
 *
 * @return A map that maps each file to a set of all files that get included by said file. E.g.
 * when `A`↦{`B`,`C`}. Then the files `B` and `C` get included by `A`.
 */
fun Project.allFileinclusions(): Map<PsiFile, Set<PsiFile>> {
    val commands = LatexCommandsIndex.getItems(this)

    // Maps every file to all the files it includes.
    val inclusions: MutableMap<PsiFile, MutableSet<PsiFile>> = HashMap()

    // Find all related files.
    for (command in commands) {
        val includedName = command.includedFileName() ?: continue
        val declaredIn = command.containingFile
        val referenced = declaredIn.findRelativeFile(includedName, null) ?: continue

        val inclusionSet = inclusions[declaredIn] ?: HashSet()
        inclusionSet.add(referenced)
        inclusions[declaredIn] = inclusionSet
    }

    return inclusions
}

/**
 * Checks whether the psi file is a tex document root or not.
 *
 * A document root is where the compilation of a tex file starts.
 *
 * @return `true` if the file is a tex document root, `false` if the file is not a root.
 */
fun PsiFile.isRoot(): Boolean {
    if (fileType != LatexFileType) {
        return false
    }

    return commandsInFile().find { it.commandToken.text == "\\documentclass" } != null
}

/**
 * Looks up the the that is in the documentclass command.
 */
fun PsiFile.documentClassFile(): PsiFile? {
    val command = commandsInFile().asSequence()
            .filter { it.name == "\\documentclass" }
            .firstOrNull() ?: return null
    val argument = command.requiredParameter(0) ?: return null
    return findRelativeFile("$argument.cls")
}

/**
 * Looks up a file relative to this file.
 *
 * @param path
 *         The path relative to this file.
 * @return The found file, or `null` when the file could not be found.
 */
fun PsiFile.findRelativeFile(path: String, extensions: Set<String>? = null): PsiFile? {
    val directory = containingDirectory.virtualFile

    val file = directory.findFile(path, extensions ?: Magic.File.includeExtensions)
            ?: return scanRoots(path, extensions)
    val psiFile = PsiManager.getInstance(project).findFile(file)
    if (psiFile == null || LatexFileType != psiFile.fileType) {
        return scanRoots(path, extensions)
    }

    return psiFile
}

/**
 * [findRelativeFile] but then it scans all content roots.
 *
 * @param path
 *         The path relative to {@code original}.
 * @return The found file, or `null` when the file could not be found.
 */
fun PsiFile.scanRoots(path: String, extensions: Set<String>? = null): PsiFile? {
    val rootManager = ProjectRootManager.getInstance(project)
    rootManager.contentSourceRoots.forEach { root ->
        val file = root.findFile(path, extensions ?: Magic.File.includeExtensions)
        if (file != null) {
            return file.psiFile(project)
        }
    }

    return null
}

/**
 * Get the corresponding document of the PsiFile.
 */
fun PsiFile.document(): Document? = PsiDocumentManager.getInstance(project).getDocument(this)

/**
 * @see [LatexCommandsIndex.getIndexedCommands]
 */
fun PsiFile.commandsInFile(): Collection<LatexCommands> = LatexCommandsIndex.getItems(this)

