package nl.abby.plugintest.reference;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import nl.abby.plugintest.TexifyIcons;
import nl.abby.plugintest.completion.handlers.LatexReferenceInsertHandler;
import nl.abby.plugintest.psi.LatexCommands;
import nl.abby.plugintest.psi.LatexRequiredParam;
import nl.abby.plugintest.util.FileSetKt;
import nl.abby.plugintest.util.LabelsKt;
import nl.abby.plugintest.util.Magic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Ruben Schellekens, Sten Wessel
 */
public class LatexLabelReference extends PsiReferenceBase<LatexCommands> implements PsiPolyVariantReference {

    private String key;

    public LatexLabelReference(@NotNull LatexCommands element, LatexRequiredParam param) {
        super(element);
        key = param.getText().substring(1, param.getText().length() - 1);

        // Only show Ctrl+click underline under the reference name
        setRangeInElement(new TextRange(param.getTextOffset() - element.getTextOffset() + 1, param.getTextOffset() - element.getTextOffset() + key.length() + 1));
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean b) {
        Project project = myElement.getProject();
        final Collection<PsiElement> labels = LabelsKt.findLabels(project, key);
        return labels.stream().map(PsiElementResolveResult::new).toArray(ResolveResult[]::new);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        String token = myElement.getCommandToken().getText();
        PsiFile file = myElement.getContainingFile().getOriginalFile();
        Collection<PsiElement> labels = new ArrayList<>();
        for (PsiFile referenced : FileSetKt.referencedFileSet(file)) {
            labels.addAll(LabelsKt.findLabels(referenced));
        }

        labels.removeIf(label -> {
            if (label instanceof LatexCommands) {
                String name = ((LatexCommands)label).getName();
                return (Magic.Command.bibliographyReference.contains(token) && "\\label".equals(name)) ||
                        (Magic.Command.reference.contains(token) &&
                                "\\bibitem".equals(name) && !Magic.Command.bibliographyReference.contains(token));
            }

            return false;
        });

        return labels.stream().map(
                l -> {
                    if (l instanceof LatexCommands) {
                        LatexCommands cmd = (LatexCommands)l;
                        Icon icon = "\\bibitem".equals(cmd.getName()) ?
                                TexifyIcons.DOT_BIB :
                                TexifyIcons.DOT_LABEL;

                        if (cmd.getRequiredParameters().isEmpty()) {
                            return null;
                        }

                        return LookupElementBuilder.create(cmd.getRequiredParameters().get(0))
                                .bold()
                                .withInsertHandler(new LatexReferenceInsertHandler())
                                .withTypeText(
                                        l.getContainingFile().getName() + ":" +
                                                (1 + StringUtil.offsetToLineNumber(l.getContainingFile().getText(), l.getTextOffset())),
                                        true
                                )
                                .withIcon(icon);
                    }

                    return null;
                }
        ).filter(Objects::nonNull).toArray();
    }
}
