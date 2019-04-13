// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import nl.abby.plugintest.psi.LatexInlineMath;
import nl.abby.plugintest.psi.LatexMathContent;
import nl.abby.plugintest.psi.LatexVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static nl.abby.plugintest.psi.LatexTypes.*;

public class LatexInlineMathImpl extends ASTWrapperPsiElement implements LatexInlineMath {

  public LatexInlineMathImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitInlineMath(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LatexMathContent getMathContent() {
    return findChildByClass(LatexMathContent.class);
  }

  @Override
  @NotNull
  public PsiElement getInlineMathEnd() {
    return findNotNullChildByType(INLINE_MATH_END);
  }

  @Override
  @NotNull
  public PsiElement getInlineMathStart() {
    return findNotNullChildByType(INLINE_MATH_START);
  }

}
