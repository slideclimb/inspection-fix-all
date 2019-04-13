package nl.abby.plugintest.lang

/**
 * @author Sten Wessel
 */
@Suppress("ConvertSecondaryConstructorToPrimary")
class OptionalArgument : Argument {

    @JvmOverloads
    internal constructor(name: String, type: Type = Type.NORMAL) : super(name, type)

    override fun toString() = "[$name]"
}