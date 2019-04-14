package nl.abby.plugintest.util

import com.intellij.psi.PsiElement
import nl.abby.plugintest.psi.LatexCommands
import nl.abby.plugintest.psi.LatexParameter
import nl.abby.plugintest.psi.LatexRequiredParam

/**
 * Checks whether the given LaTeX commands is an environment definition or not.
 *
 * @return `true` if the command is an environment definition, `false` when the command is `null` or otherwise.
 */
fun LatexCommands?.isEnvironmentDefinition(): Boolean {
    return this != null && ("\\newenvironment" == name ||
            "\\renewenvironment" == name)
}

/**
 * Get the text contents of the `index+1`th required parameter of the command.
 *
 * @throws IllegalArgumentException When the index is negative.
 */
@Throws(IllegalArgumentException::class)
fun LatexCommands.requiredParameter(index: Int): String? {
    require(index >= 0) { "Index must not be negative" }

    val parameters = requiredParameters
    if (parameters.isEmpty() || index >= parameters.size) {
        return null
    }

    return parameters[index]
}

/**
 * If the given command is an include command, the contents of the first argument will be read.
 *
 * @return The included filename or `null` when it's not an include command or when there
 * are no required parameters.
 */
fun LatexCommands.includedFileName(): String? {
    if (commandToken.text !in Magic.Command.includes) return null
    val required = requiredParameters
    if (required.isEmpty()) return null
    return required.first()
}

/**
 * Looks up all the required parameters of this command.
 *
 * @return A list of all required parameters.
 */
fun LatexCommands.requiredParameters(): List<LatexRequiredParam> = parameterList.asSequence()
        .filter { it.requiredParam != null }
        .mapNotNull(LatexParameter::getRequiredParam)
        .toList()

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