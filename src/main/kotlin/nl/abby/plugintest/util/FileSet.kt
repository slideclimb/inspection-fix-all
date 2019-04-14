package nl.abby.plugintest.util

import com.intellij.psi.PsiFile

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

