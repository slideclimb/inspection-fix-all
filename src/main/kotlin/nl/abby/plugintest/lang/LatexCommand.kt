package nl.abby.plugintest.lang

import nl.abby.plugintest.psi.LatexCommands
import nl.abby.plugintest.util.inMathContext
import kotlin.reflect.KClass

/**
 * @author Ruben Schellekens, Sten Wessel
 */
interface LatexCommand : Dependend {

    companion object;

    /**
     * Get the name of the command without the first backslash.
     */
    val command: String

    val commandDisplay: String
        get() = "\\$command"

    /**
     * Get the display value of the command.
     */
    val display: String?

    /**
     * Get all the command arguments.
     */
    val arguments: Array<out Argument>

    /**
     * Concatenates all arguments to each other.
     *
     * @return e.g. `{ARG1}{ARG2}[ARG3]`
     */
    fun getArgumentsDisplay(): String {
        val sb = StringBuilder()
        for (arg in arguments) {
            sb.append(arg.toString())
        }

        return sb.toString()
    }

    /**
     * Checks whether `{}` must be automatically inserted in the auto complete.
     *
     * @return `true` to insert automatically, `false` not to insert.
     */
    fun autoInsertRequired() = arguments.filter { arg -> arg is RequiredArgument }.count() >= 1

    @Suppress("UNCHECKED_CAST")
    fun <T : Argument> getArgumentsOf(clazz: KClass<T>): List<T> {
        return arguments.asSequence()
                .filter { clazz.java.isAssignableFrom(it.javaClass) }
                .mapNotNull { it as? T }
                .toList()
    }

    fun <T : Argument> getArgumentsOf(clazz: Class<T>) = getArgumentsOf(clazz.kotlin)
}

internal fun String.asRequired(type: Argument.Type = Argument.Type.NORMAL) = RequiredArgument(this, type)

internal fun String.asOptional(type: Argument.Type = Argument.Type.NORMAL) = OptionalArgument(this, type)