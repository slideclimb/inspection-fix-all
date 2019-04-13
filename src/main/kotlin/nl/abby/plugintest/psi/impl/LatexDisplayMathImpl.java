// This is a generated file. Not intended for manual editing.
package nl.abby.plugintest.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import nl.abby.plugintest.psi.LatexDisplayMath;
import nl.abby.plugintest.psi.LatexMathContent;
import nl.abby.plugintest.psi.LatexVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static nl.abby.plugintest.psi.LatexTypes.*;

public class LatexDisplayMathImpl extends ASTWrapperPsiElement implements LatexDisplayMath {

  public LatexDisplayMathImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LatexVisitor visitor) {
    visitor.visitDisplayMath(this);
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

}
