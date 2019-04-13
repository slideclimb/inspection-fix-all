// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

import static nl.abby.plugintest.psi.LatexTypes.*;
import static nl.abby.plugintest.psi.LatexTypes.COMMENT_TOKEN;

public class LatexCommentImpl extends ASTWrapperPsiElement implements LatexComment {

  public LatexCommentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitComment(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getCommentToken() {
    return findNotNullChildByType(COMMENT_TOKEN);
  }

}
