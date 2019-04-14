package nl.abby.plugintest.index

import com.intellij.psi.stubs.StubIndexKey
import nl.abby.plugintest.psi.LatexCommands

/**
 * @author Ruben Schellekens
 */
abstract class IndexCommandsUtilBase(
    indexKey: StubIndexKey<String, LatexCommands>
) : IndexUtilBase<LatexCommands>(LatexCommands::class.java, indexKey)