package nl.abby.plugintest.file

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

/**
 * @author Sten Wessel
 */
class LatexFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun getFileType() = LatexFileType

    override fun toString() = "LaTeX source file"
}
