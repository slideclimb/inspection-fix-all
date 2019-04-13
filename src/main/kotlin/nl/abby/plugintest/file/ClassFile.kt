package nl.abby.plugintest.file

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

/**
 * @author Ruben Schellekens
 */
class ClassFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun getFileType() = ClassFileType

    override fun toString() = "LaTeX class file"
}