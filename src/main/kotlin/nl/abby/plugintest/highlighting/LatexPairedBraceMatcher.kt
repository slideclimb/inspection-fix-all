package nl.abby.plugintest.highlighting

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import nl.abby.plugintest.psi.LatexTypes

/**
 * @author Sten Wessel
 */
class LatexPairedBraceMatcher : PairedBraceMatcher {

    companion object {

        private val bracePairs = arrayOf(
                BracePair(LatexTypes.DISPLAY_MATH_START, LatexTypes.DISPLAY_MATH_END, true),
                BracePair(LatexTypes.INLINE_MATH_START, LatexTypes.INLINE_MATH_END, true),
                BracePair(LatexTypes.OPEN_PAREN, LatexTypes.CLOSE_PAREN, false),
                BracePair(LatexTypes.OPEN_BRACE, LatexTypes.CLOSE_BRACE, false),
                BracePair(LatexTypes.OPEN_BRACKET, LatexTypes.CLOSE_BRACKET, false),
                BracePair(LatexTypes.M_OPEN_BRACKET, LatexTypes.M_CLOSE_BRACKET, false)
        )
    }

    override fun getPairs() = bracePairs

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean {
        // Automatic completion by IJ fails with multiple characters for rbrace.
        return lbraceType !== LatexTypes.DISPLAY_MATH_START
    }

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int) = openingBraceOffset
}
