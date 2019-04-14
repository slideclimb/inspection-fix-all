package nl.abby.plugintest.util

import java.util.regex.Pattern

typealias RegexPattern = Pattern

/**
 * Magic constants are awesome!
 *
 * @author Ruben Schellekens
 */
object Magic {

    /**
     * @author Ruben Schellekens
     */
    object Command {

        /**
         * All commands that define new commands.
         */
        @JvmField val commandDefinitions = hashSetOf(
                "\\newcommand",
                "\\let",
                "\\def",
                "\\DeclareMathOperator",
                "\\newif",
                "\\NewDocumentCommand",
                "\\ProvideDocumentCommand",
                "\\DeclareDocumentCommand"
        )

        /**
         * All commands that define new documentclasses.
         */
        @JvmField val classDefinitions = hashSetOf("\\ProvidesClass")

        /**
         * All commands that define new environments.
         */
        @JvmField val environmentDefinitions = hashSetOf(
                "\\newenvironment",
                "\\NewDocumentEnvironment",
                "\\ProvideDocumentEnvironment",
                "\\DeclareDocumentEnvironment"
        )

        /**
         * All commands that define stuff like classes, environments, and definitions.
         */
        @JvmField val definitions = commandDefinitions + classDefinitions + environmentDefinitions

        /**
         * All commands that are able to redefine other commands.
         */
        @JvmField val redefinitions = hashSetOf("\\renewcommand", "\\def", "\\let", "\\renewenvironment")

        /**
         * All commands that include other files.
         */
        @JvmField val includes = hashSetOf(
                "\\includeonly", "\\include", "\\input", "\\RequirePackage", "\\usepackage"
        )

        /**
         * Extensions that should only be scanned for the provided include commands.
         */
        @JvmField val includeOnlyExtensions = mapOf(
                "\\include" to hashSetOf("tex"),
                "\\includeonly" to hashSetOf("tex"),
                "\\RequirePackage" to hashSetOf("sty"),
                "\\usepackage" to hashSetOf("sty")
        )

        /**
         * All commands that mark some kind of section.
         */
        @JvmField val sectionMarkers = listOf(
                "\\part", "\\chapter",
                "\\section", "\\subsection", "\\subsubsection",
                "\\paragraph", "\\subparagraph"
        )
    }

    /**
     * @author Ruben Schellekens
     */
    object Pattern {

        /**
         * Matches the end of a sentence.
         *
         * Includes `[^.][^.]` because of abbreviations (at least in Dutch) like `s.v.p.`
         */
        @JvmField val sentenceEnd = RegexPattern.compile("([^.A-Z][^.A-Z][.?!;;] +[^%])|(^\\. )")!!

        /**
         * Matches all interpunction that marks the end of a sentence.
         */
        @JvmField val sentenceSeperator = RegexPattern.compile("[.?!;;]")!!

        /**
         * Matches all leading whitespace.
         */
        @JvmField val leadingWhitespace = RegexPattern.compile("^\\s*")!!

        /**
         * Matches newlines.
         */
        @JvmField val newline = RegexPattern.compile("\\n")!!

    }

    /**
     * @author Ruben Schellekens
     */
    object File {

        /**
         * All file extensions of files that can be included.
         */
        @JvmField val includeExtensions = hashSetOf("tex")

    }
    
    /**
     * @author Ruben Schellekens
     */
    object Package

}
