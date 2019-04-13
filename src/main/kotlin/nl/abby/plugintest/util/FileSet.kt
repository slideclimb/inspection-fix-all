package nl.abby.plugintest.util

import com.intellij.psi.PsiFile
import nl.abby.plugintest.index.LatexCommandsIndex
import nl.abby.plugintest.index.LatexDefinitionIndex
import nl.abby.plugintest.index.LatexIncludesIndex

/**
 * Finds all the files in the project that are somehow related using includes.
 * <p>
 * When A includes B and B includes C then A, B & C will all return a set containing A, B & C.
 *
 * @param baseFile
 *         The file to find the reference set of.
 * @return All the files that are cross referenced between each other.
 */
fun findReferencedFileSet(baseFile: PsiFile): Set<PsiFile> {
    // Setup.
    val project = baseFile.project
    val includes = LatexIncludesIndex.getItems(project)

    // Find all root files.
    val roots = includes.asSequence()
            .map { it.containingFile }
            .distinct()
            .filter { it.isRoot() }
            .toSet()

    // Map root to all directly referenced files.
    val sets = HashMap<PsiFile, Set<PsiFile>>()
    for (root in roots) {
        val referenced = root.referencedFiles() + root

        if (referenced.contains(baseFile)) {
            return referenced + baseFile
        }

        sets[root] = referenced
    }

    // Look for matching root.
    for (referenced in sets.values) {
        if (referenced.contains(baseFile)) {
            return referenced + baseFile
        }
    }

    return setOf(baseFile)
}

/**
 * Finds all the files in the project that are somehow related using includes.
 *
 * When A includes B and B includes C then A, B & C will all return a set containing A, B & C.
 *
 * @return All the files that are cross referenced between each other.
 */
fun PsiFile.referencedFileSet(): Set<PsiFile> = findReferencedFileSet(this)

/**
 * @see [LatexCommandsIndex.getItemsInFileSet]
 */
fun PsiFile.commandsInFileSet(): Collection<nl.abby.plugintest.psi.LatexCommands> = LatexCommandsIndex.getItemsInFileSet(this)

/**
 * Get all the definitions in the file set.
 */
fun PsiFile.definitionsInFileSet(): Collection<nl.abby.plugintest.psi.LatexCommands> {
    return LatexDefinitionIndex.getItemsInFileSet(this)
            .filter { it.isDefinition() }
}

/**
 * Get all the definitions and redefinitions in the file set.
 */
fun PsiFile.definitionsAndRedefinitionsInFileSet(): Collection<nl.abby.plugintest.psi.LatexCommands> {
    return LatexDefinitionIndex.getItemsInFileSet(this)
}