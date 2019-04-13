package nl.abby.plugintest.inspections.latex

import nl.abby.plugintest.inspections.TexifyRegexInspection
import java.util.regex.Pattern

/**
 * @author Ruben Schellekens
 */
open class LatexCdotInspection : TexifyRegexInspection(
        inspectionDisplayName = "Use of . instead of \\cdot",
        myInspectionId = "Cdot",
        errorMessage = { "\\cdot expected" },
        pattern = Pattern.compile("\\s+(\\.)\\s+"),
        mathMode = true,
        replacement = { _, _ -> "\\cdot" },
        replacementRange = { it.groupRange(1) },
        quickFixName = { "Change to \\cdot" }
)