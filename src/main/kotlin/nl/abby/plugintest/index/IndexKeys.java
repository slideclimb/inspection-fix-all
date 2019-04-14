package nl.abby.plugintest.index;

import com.intellij.psi.stubs.StubIndexKey;
import nl.abby.plugintest.psi.LatexCommands;

/**
 * @author Ruben Schellekens
 */
public class IndexKeys {

    public static final StubIndexKey<String, LatexCommands> COMMANDS_KEY =
            StubIndexKey.createIndexKey("nl.abby.plugintest.commands");

}
