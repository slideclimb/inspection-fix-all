package nl.abby.plugintest.index

import com.intellij.psi.stubs.StringStubIndexExtension

/**
 * @author Ruben Schellekens
 */
open class LatexDefinitionIndex : StringStubIndexExtension<nl.abby.plugintest.psi.LatexCommands>() {

    companion object : IndexCommandsUtilBase(nl.abby.plugintest.index.IndexKeys.DEFINITIONS_KEY)

    override fun getKey() = Companion.key()
}