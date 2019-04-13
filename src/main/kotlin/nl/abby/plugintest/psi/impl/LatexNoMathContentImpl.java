// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static nl.abby.plugintest.psi.LatexTypes.*;

public class LatexNoMathContentImpl extends ASTWrapperPsiElement implements LatexNoMathContent {

  public LatexNoMathContentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitNoMathContent(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LatexCommands getCommands() {
    return findChildByClass(LatexCommands.class);
  }

  @Override
  @Nullable
  public LatexComment getComment() {
    return findChildByClass(LatexComment.class);
  }

  @Override
  @Nullable
  public LatexEnvironment getEnvironment() {
    return findChildByClass(LatexEnvironment.class);
  }

  @Override
  @Nullable
  public LatexGroup getGroup() {
    return findChildByClass(LatexGroup.class);
  }

  @Override
  @Nullable
  public LatexMathEnvironment getMathEnvironment() {
    return findChildByClass(LatexMathEnvironment.class);
  }

  @Override
  @Nullable
  public LatexNormalText getNormalText() {
    return findChildByClass(LatexNormalText.class);
  }

  @Override
  @Nullable
  public LatexOpenGroup getOpenGroup() {
    return findChildByClass(LatexOpenGroup.class);
  }

}
