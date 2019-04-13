package nl.abby.plugintest.psi

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

/**
 * @author Sten Wessel
 */
class LatexTokenType(@NonNls debugName: String) : IElementType(debugName, nl.abby.plugintest.LatexLanguage.INSTANCE) {

    override fun toString() = "LatexTokenType." + super.toString()
}