// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LatexEnvironment extends PsiElement {

  @NotNull
  LatexBeginCommand getBeginCommand();

  @NotNull
  LatexEndCommand getEndCommand();

  @Nullable
  LatexEnvironmentContent getEnvironmentContent();

}
