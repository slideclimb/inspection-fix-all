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
         * All commands that include other files.
         */
        @JvmField val includes = hashSetOf(
                "\\includeonly", "\\include", "\\input", "\\RequirePackage", "\\usepackage"
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

}
