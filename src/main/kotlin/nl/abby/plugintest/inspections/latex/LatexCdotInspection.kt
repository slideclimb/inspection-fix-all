package nl.abby.plugintest.inspections.latex

import nl.abby.plugintest.inspections.TexifyRegexInspection
import java.util.regex.Pattern

/**
 * @author Ruben Schellekens
 */
open class LatexCdotInspection : TexifyRegexInspection(
    inspectionDisplayName = "Use of . instead of \\cdot",
    pattern = Pattern.compile("\\s+(\\.)\\s+"),
    errorMessage = { "\\cdot expected" },
    replacement = { _, _ -> "\\cdot" },
    replacementRange = { it.groupRange(1) },
    quickFixName = { "Change to \\cdot" }
) {
    override fun getShortName(): String = "LatexCdot"
}