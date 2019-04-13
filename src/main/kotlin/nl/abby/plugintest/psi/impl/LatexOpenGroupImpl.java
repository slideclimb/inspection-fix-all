// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import nl.abby.plugintest.psi.LatexContent;
import nl.abby.plugintest.psi.LatexOpenGroup;
import nl.abby.plugintest.psi.LatexVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LatexOpenGroupImpl extends ASTWrapperPsiElement implements LatexOpenGroup {

  public LatexOpenGroupImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitOpenGroup(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LatexVisitor) accept((LatexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LatexContent> getContentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LatexContent.class);
  }

}
