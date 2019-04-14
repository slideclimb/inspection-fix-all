package nl.abby.plugintest.util

import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import nl.abby.plugintest.file.LatexFileType

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

