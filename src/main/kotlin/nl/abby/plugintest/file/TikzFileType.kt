package nl.abby.plugintest.file

import com.intellij.openapi.fileTypes.LanguageFileType

/**
 * @author Thomas Schouten
 */
object TikzFileType : LanguageFileType(nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun getName() = "TikZ picture file"

    override fun getDescription() = "TikZ picture file"

    override fun getDefaultExtension() = "tikz"

    override fun getIcon() = nl.abby.plugintest.TexifyIcons.LATEX_FILE!!
}
