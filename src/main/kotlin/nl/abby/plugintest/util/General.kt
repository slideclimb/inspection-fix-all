package nl.abby.plugintest.util

import com.intellij.openapi.util.TextRange

/**
 * Creates a pair of two objects, analogous to [to].
 */
infix fun <T1, T2> T1.and(other: T2) = Pair(this, other)

/**
 * Converts an [IntRange] to [TextRange].
 */
fun IntRange.toTextRange() = TextRange(this.start, this.endInclusive + 1)

