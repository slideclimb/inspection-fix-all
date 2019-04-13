package nl.abby.plugintest.file

import com.intellij.openapi.fileTypes.LanguageFileType

/**
 * @author Ruben Schellekens
 */
object StyleFileType : LanguageFileType(nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun getName() = "LaTeX style file"

    override fun getDescription() = "LaTeX style file"

    override fun getDefaultExtension() = "sty"

    override fun getIcon() = nl.abby.plugintest.TexifyIcons.STYLE_FILE!!
}
