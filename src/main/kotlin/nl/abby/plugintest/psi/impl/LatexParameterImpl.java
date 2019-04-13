// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import nl.abby.plugintest.psi.LatexOptionalParam;
import nl.abby.plugintest.psi.LatexParameter;
import nl.abby.plugintest.psi.LatexRequiredParam;
import nl.abby.plugintest.psi.LatexVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LatexParameterImpl extends ASTWrapperPsiElement implements LatexParameter {

  public LatexParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitParameter(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LatexOptionalParam getOptionalParam() {
    return findChildByClass(LatexOptionalParam.class);
  }

  @Override
  @Nullable
  public LatexRequiredParam getRequiredParam() {
    return findChildByClass(LatexRequiredParam.class);
  }

}
