// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import nl.abby.plugintest.psi.LatexDisplayMath;
import nl.abby.plugintest.psi.LatexInlineMath;
import nl.abby.plugintest.psi.LatexMathEnvironment;
import nl.abby.plugintest.psi.LatexVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class LatexMathEnvironmentImpl extends ASTWrapperPsiElement implements LatexMathEnvironment {

  public LatexMathEnvironmentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitMathEnvironment(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LatexDisplayMath getDisplayMath() {
    return findChildByClass(LatexDisplayMath.class);
  }

  @Override
  @Nullable
  public LatexInlineMath getInlineMath() {
    return findChildByClass(LatexInlineMath.class);
  }

}
