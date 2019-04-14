package nl.abby.plugintest.insight

import com.intellij.openapi.fileTypes.FileType
import nl.abby.plugintest.file.LatexFileType

/**
 * @author Ruben Schellekens
 */
enum class InsightGroup(

        /**
         * The name that gets displayed in the inspection settings.
         */
        val displayName: String,

        /**
         * The prefix of all internal inspection names.
         */
        val prefix: String,

        /**
         * The filetypes to which the inspection must be applied.
         */
        val fileTypes: Set<FileType>
) {

    LATEX("LaTeX", "Latex", setOf(LatexFileType)),
}