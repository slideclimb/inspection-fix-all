package nl.abby.plugintest.modules

import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager
import nl.abby.plugintest.TexifyIcons

/**
 * @author Sten Wessel
 */
class LatexModuleType : ModuleType<LatexModuleBuilder>(ID) {

    companion object {

        private const val ID = "LATEX_MODULE_TYPE"

        val INSTANCE: LatexModuleType
            get() = ModuleTypeManager.getInstance().findByID(ID) as LatexModuleType
    }

    override fun createModuleBuilder() = LatexModuleBuilder()

    override fun getName() = "LaTeX"

    override fun getDescription() = "LaTeX"

    override fun getNodeIcon(isOpened: Boolean) = TexifyIcons.LATEX_MODULE!!
}