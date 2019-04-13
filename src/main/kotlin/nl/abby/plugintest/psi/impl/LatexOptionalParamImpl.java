// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import nl.abby.plugintest.psi.LatexOpenGroup;
import nl.abby.plugintest.psi.LatexOptionalParam;
import nl.abby.plugintest.psi.LatexVisitor;
import org.jetbrains.annotations.NotNull;

import static nl.abby.plugintest.psi.LatexTypes.*;

public class LatexOptionalParamImpl extends ASTWrapperPsiElement implements LatexOptionalParam {

  public LatexOptionalParamImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitOptionalParam(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LatexOpenGroup getOpenGroup() {
    return findNotNullChildByClass(LatexOpenGroup.class);
  }

}
