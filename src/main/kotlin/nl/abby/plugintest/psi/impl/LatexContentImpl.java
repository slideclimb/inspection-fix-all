// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import nl.abby.plugintest.psi.LatexContent;
import nl.abby.plugintest.psi.LatexNoMathContent;
import nl.abby.plugintest.psi.LatexVisitor;
import org.jetbrains.annotations.NotNull;

public class LatexContentImpl extends ASTWrapperPsiElement implements LatexContent {

  public LatexContentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitContent(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LatexNoMathContent getNoMathContent() {
    return findNotNullChildByClass(LatexNoMathContent.class);
  }

}
