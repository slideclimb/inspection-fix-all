package nl.abby.plugintest.file

import nl.abby.plugintest.LatexLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

/**
 * @author Thomas Schouten
 */
open class TikzFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun getFileType() = TikzFileType

    override fun toString() = "TikZ picture file"
}