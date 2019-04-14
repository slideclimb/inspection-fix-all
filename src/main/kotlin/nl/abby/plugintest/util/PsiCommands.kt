package nl.abby.plugintest.util

import com.intellij.psi.PsiElement
import nl.abby.plugintest.psi.LatexCommands

/**
 * Get all [LatexCommands] that are children of the given element.
 */
fun PsiElement.allCommands(): List<LatexCommands> {
    val commands = ArrayList<LatexCommands>()
    allCommands(commands)
    return commands
}

/**
 * Recursive implementation of [allCommands].
 */
private fun PsiElement.allCommands(commands: MutableList<LatexCommands>) {
    forEachChild { it.allCommands(commands) }
    if (this is LatexCommands) {
        commands.add(this)
    }
}