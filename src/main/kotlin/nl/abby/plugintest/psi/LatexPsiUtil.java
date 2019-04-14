package nl.abby.plugintest.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Schellekens
 */
public class LatexPsiUtil {

    private LatexPsiUtil() {
    }

    /**
     * Finds the previous sibling of an element but skips over whitespace.
     *
     * @param element
     *         The element to get the previous sibling of.
     * @return The previous sibling of the given psi element, or {@code null} when there is no
     * previous sibling.
     */
    @Nullable
    public static PsiElement getPreviousSiblingIgnoreWhitespace(@NotNull PsiElement element) {
        PsiElement sibling = element;
        while ((sibling = sibling.getPrevSibling()) != null) {
            if (!(sibling instanceof PsiWhiteSpace)) {
                return sibling;
            }
        }

        return null;
    }

    /**
     * Finds the next sibling of an element but skips over whitespace.
     *
     * @param element
     *         The element to get the next sibling of.
     * @return The next sibling of the given psi element, or {@code null} when there is no previous
     * sibling.
     */
    @Nullable
    public static PsiElement getNextSiblingIgnoreWhitespace(@NotNull PsiElement element) {
        PsiElement sibling = element;
        while ((sibling = sibling.getNextSibling()) != null) {
            if (!(sibling instanceof PsiWhiteSpace)) {
                return sibling;
            }
        }

        return null;
    }

    /**
     * Get all the elements of the subtree starting at the given Latex {@link PsiElement}.
     * <p>
     * This method uses a depth-first traversal.
     *
     * @param element
     *         The {@link PsiElement} contained in {@link nl.abby.plugintest.psi} of which you
     *         want to get all the elements of the subtree of.
     * @return A list of all elements in the subtree starting at, and including, the given element
     * at index 0. The list will be empty when the element has no children or when the element is
     * not a Latex element.
     */
    public static List<PsiElement> getAllChildren(PsiElement element) {
        return getAllChildren(new ArrayList<>(), element);
    }

    /**
     * See {@link LatexPsiUtil#getAllChildren(PsiElement)}, but appends all children to the given
     * list.
     */
    private static List<PsiElement> getAllChildren(List<PsiElement> result, PsiElement element) {
        result.add(element);

        for (PsiElement child : getChildren(element)) {
            getAllChildren(result, child);
        }

        return result;
    }

    /**
     * Get all the Latex children of the given Latex {@link PsiElement}s.
     *
     * @param element
     *         The {@link PsiElement} contained in {@link nl.abby.plugintest.psi} of which you
     *         want to get all children of.
     * @return A list of all children of the given element. The list will be empty when the element
     * has no children or when the element is not a Latex element.
     */
    public static List<PsiElement> getChildren(PsiElement element) {
        List<PsiElement> result = new ArrayList<>();

        // LatexCommands
        if (element instanceof LatexCommands) {
            LatexCommands commands = (LatexCommands)element;
            result.addAll(commands.getParameterList());
            result.add(commands.getCommandToken());
        }
        // LatexComment
        else if (element instanceof LatexComment) {
            LatexComment comment = (LatexComment)element;
            result.add(comment.getCommentToken());
        }
        // LatexContent
        else if (element instanceof LatexContent) {
            LatexContent content = (LatexContent)element;
            result.add(content.getNoMathContent());
        }
        // LatexDisplayMath
        else if (element instanceof LatexDisplayMath) {
            LatexDisplayMath displayMath = (LatexDisplayMath)element;
            result.add(displayMath.getMathContent());
        }
        // LatexGroup
        else if (element instanceof LatexGroup) {
            LatexGroup group = (LatexGroup)element;
            result.addAll(group.getContentList());
        }
        // LatexInlineMath
        else if (element instanceof LatexInlineMath) {
            LatexInlineMath inlineMath = (LatexInlineMath)element;
            result.add(inlineMath.getMathContent());
        }
        // LatexMathEnvironment
        else if (element instanceof LatexMathEnvironment) {
            LatexMathEnvironment mathEnvironment = (LatexMathEnvironment)element;
            result.add(mathEnvironment.getDisplayMath());
            result.add(mathEnvironment.getInlineMath());
        }
        // LatexNoMathContent
        else if (element instanceof LatexNoMathContent) {
            LatexNoMathContent noMathContent = (LatexNoMathContent)element;
            result.add(noMathContent.getCommands());
            result.add(noMathContent.getComment());
            result.add(noMathContent.getGroup());
            result.add(noMathContent.getOpenGroup());
            result.add(noMathContent.getNormalText());
        }
        // LatexOpenGroup
        else if (element instanceof LatexOpenGroup) {
            LatexOpenGroup openGroup = (LatexOpenGroup)element;
            result.addAll(openGroup.getContentList());
        }
        // LatexOptionalParam
        else if (element instanceof LatexOptionalParam) {
            LatexOptionalParam optionalParam = (LatexOptionalParam)element;
            result.add(optionalParam.getOpenGroup());
        }
        // LatexParameter
        else if (element instanceof LatexParameter) {
            LatexParameter parameter = (LatexParameter)element;
            result.add(parameter.getOptionalParam());
            result.add(parameter.getRequiredParam());
        }
        // LatexRequiredParam
        else if (element instanceof LatexRequiredParam) {
            LatexRequiredParam requiredParam = (LatexRequiredParam)element;
            result.add(requiredParam.getGroup());
        }
        // LatexMathContent
        else if (element instanceof LatexMathContent) {
            LatexMathContent mathContent = (LatexMathContent)element;
            result.addAll(mathContent.getNoMathContentList());
        }

        return result;
    }

}
