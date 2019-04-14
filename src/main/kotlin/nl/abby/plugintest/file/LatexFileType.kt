package nl.abby.plugintest.file
import com.intellij.openapi.fileTypes.LanguageFileType
import nl.abby.plugintest.LatexLanguage
import javax.swing.Icon

/**
 * @author Sten Wessel
 */
object LatexFileType : LanguageFileType(LatexLanguage.INSTANCE) {

    override fun getName() = "LaTeX source file"

    override fun getDescription() = "LaTeX source file"

    override fun getDefaultExtension() = "tex"

    override fun getIcon(): Icon? = null
}
