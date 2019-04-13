// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface LatexNoMathContent extends PsiElement {

  @Nullable
  LatexCommands getCommands();

  @Nullable
  LatexComment getComment();

  @Nullable
  LatexEnvironment getEnvironment();

  @Nullable
  LatexGroup getGroup();

  @Nullable
  LatexMathEnvironment getMathEnvironment();

  @Nullable
  LatexNormalText getNormalText();

  @Nullable
  LatexOpenGroup getOpenGroup();

}
