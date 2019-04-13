package nl.abby.plugintest.index

import com.intellij.psi.stubs.StringStubIndexExtension

/**
 * @author Ruben Schellekens
 */
class LatexIncludesIndex : StringStubIndexExtension<nl.abby.plugintest.psi.LatexCommands>() {

    companion object : IndexCommandsUtilBase(nl.abby.plugintest.index.IndexKeys.INCLUDES_KEY)

    override fun getKey() = Companion.key()
}
