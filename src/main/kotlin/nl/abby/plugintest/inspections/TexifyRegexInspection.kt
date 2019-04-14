package nl.abby.plugintest.inspections

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import nl.abby.plugintest.util.document
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.reflect.jvm.internal.impl.utils.SmartList

/**
 * @author Ruben Schellekens
 */
abstract class TexifyRegexInspection(

    /**
     * The display name of the inspection.
     */
    val inspectionDisplayName: String,

    /**
     * The regex pattern that targets the text for the inspection.
     */
    val pattern: Pattern,

    /**
     * The error message that shows up when you hover over the problem descriptor.
     */
    val errorMessage: (Matcher) -> String,

    /**
     * What to replace in the document.
     */
    val replacement: (Matcher, PsiFile) -> String = { _, _ -> "" },

    /**
     * Fetches different groups from a matcher.
     */
    val groupFetcher: (Matcher) -> List<String> = { listOf() },

    /**
     * The range in the found pattern that must be replaced.
     */
    val replacementRange: (Matcher) -> IntRange = { it.start()..it.end() },

    /**
     * The highlight level of the problem, WEAK_WARNING by default.
     */
    val highlight: ProblemHighlightType = ProblemHighlightType.WEAK_WARNING,

    /**
     * Name of the quick fix.
     */
    val quickFixName: (Matcher) -> String = { "Do fix pls" },

    /**
     * Predicate that if `true`, cancels the inspection.
     */
    val cancelIf: (Matcher, PsiFile) -> Boolean = { _, _ -> false },

    /**
     * Provides the text ranges that mark the squiggly warning thingies.
     */
    val highlightRange: (Matcher) -> TextRange = {
        TextRange(
            it.start(),
            it.end()
        )
    }

) : TexifyInspectionBase() {

    companion object {

        /**
         * Get the IntRange that spans the group with the given id.
         */
        fun Matcher.groupRange(groupId: Int): IntRange = start(groupId)..end(groupId)

    }

    override fun getDisplayName() = inspectionDisplayName

    override fun inspectFile(file: PsiFile, manager: InspectionManager, isOntheFly: Boolean): MutableList<ProblemDescriptor> {
        val descriptors = SmartList<ProblemDescriptor>()

        // Find all patterns.
        val text = file.text
        val matcher = pattern.matcher(text)
        while (matcher.find()) {
            // Pre-checks.
            if (cancelIf(matcher, file)) {
                continue
            }

            val groups = groupFetcher(matcher)
            val textRange = highlightRange(matcher)
            val range = replacementRange(matcher)
            val error = errorMessage(matcher)
            val quickFix = quickFixName(matcher)
            val replacementContent = replacement(matcher, file)

            descriptors.add(manager.createProblemDescriptor(
                    file,
                    textRange,
                    error,
                    highlight,
                    isOntheFly,
                RegexFix(
                    quickFix,
                    replacementContent,
                    range,
                    groups,
                    this::applyFix
                )
            ))
        }

        return descriptors
    }

    /**
     * Replaces all text in the replacementRange by the correct replacement.
     */
    open fun applyFix(project: Project, descriptor: ProblemDescriptor, replacementRange: IntRange, replacement: String, groups: List<String>) {
        val file = descriptor.psiElement as PsiFile
        val document = file.document() ?: return

        document.replaceString(replacementRange.start, replacementRange.endInclusive, replacement)
    }

    /**
     * @author Ruben Schellekens
     */
    private class RegexFix(
            val fixName: String,
            val replacement: String,
            val replacementRange: IntRange,
            val groups: List<String>,
            val fixFunction: (Project, ProblemDescriptor, IntRange, String, List<String>) -> Unit
    ) : LocalQuickFix {

        override fun getFamilyName(): String = fixName

        override fun applyFix(project: Project, problemDescriptor: ProblemDescriptor) {
            fixFunction(project, problemDescriptor, replacementRange, replacement, groups)
        }
    }
}