package nl.abby.plugintest;

import com.intellij.lexer.FlexAdapter;
import nl.abby.plugintest.grammar.LatexLexer;

/**
 * @author Sten Wessel
 */
public class LatexLexerAdapter extends FlexAdapter {

    public LatexLexerAdapter() {
        super(new LatexLexer());
    }
}
