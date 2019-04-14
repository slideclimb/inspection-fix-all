package nl.abby.plugintest.inspections;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Sten Wessel
 */
public abstract class TexifyInspectionBase extends LocalInspectionTool {

    @Nls
    @NotNull
    @Override
    public abstract String getDisplayName();

    @NotNull
    public abstract List<ProblemDescriptor> inspectFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOntheFly);

    @NotNull
    @Override
    public String getShortName() {
        return "shortname";
    }

    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        return "LaTeX";
    }

    @Nullable
    @Override
    public ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        List<ProblemDescriptor> descriptors = inspectFile(file, manager, isOnTheFly);
        return descriptors.toArray(new ProblemDescriptor[0]);
    }

}
