package nl.abby.plugintest.file

import nl.abby.plugintest.LatexLanguage
import nl.abby.plugintest.TexifyIcons
import com.intellij.openapi.fileTypes.LanguageFileType

/**
 * @author Ruben Schellekens
 */
object ClassFileType : LanguageFileType(nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun getName() = "LaTeX class file"

    override fun getDescription() = "LaTeX class file"

    override fun getDefaultExtension() = "cls"

    override fun getIcon() = nl.abby.plugintest.TexifyIcons.CLASS_FILE!!
}
