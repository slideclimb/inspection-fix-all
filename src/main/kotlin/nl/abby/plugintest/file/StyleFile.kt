package nl.abby.plugintest.file

import nl.abby.plugintest.LatexLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

/**
 * @author Ruben Schellekens
 */
class StyleFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun getFileType() = StyleFileType

    override fun toString() = "LaTeX style file"
}
