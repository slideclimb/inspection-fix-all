package nl.abby.plugintest.util

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import nl.abby.plugintest.lang.DefaultEnvironment
import nl.abby.plugintest.lang.Environment
import nl.abby.plugintest.lang.Environment.Context
import nl.abby.plugintest.psi.*
import kotlin.reflect.KClass

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// PSI ELEMENT ///////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * @see [PsiTreeUtil.getChildrenOfType]
 */
fun <T : PsiElement> PsiElement.childrenOfType(clazz: KClass<T>): Collection<T> {
    return PsiTreeUtil.findChildrenOfType(this, clazz.java)
}

/**
 * Finds the first child of a certain type.
 */
@Suppress("UNCHECKED_CAST")
fun <T : PsiElement> PsiElement.firstChildOfType(clazz: KClass<T>): T? {
    for (child in this.children) {
        if (clazz.java.isAssignableFrom(child.javaClass)) {
            return child as? T
        }

        val first = child.firstChildOfType(clazz)
        if (first != null) {
            return first
        }
    }

    return null
}

/**
 * @see [PsiTreeUtil.getParentOfType]
 */
fun <T : PsiElement> PsiElement.parentOfType(clazz: KClass<T>): T? = PsiTreeUtil.getParentOfType(this, clazz.java)

/**
 * Checks if the psi element has a parent of a given class.
 */
fun <T : PsiElement> PsiElement.hasParent(clazz: KClass<T>): Boolean = parentOfType(clazz) != null

/**
 * Checks if the psi element is in math mode or not.
 *
 * @return `true` when the element is in math mode, `false` when the element is in no math mode.
 */
fun PsiElement.inMathContext(): Boolean {
    return hasParent(LatexMathContent::class) || hasParent(LatexDisplayMath::class) || inDirectEnvironmentContext(
        Context.MATH)
}

/**
 * Checks whether the psi element is part of a comment or not.
 */
fun PsiElement.isComment(): Boolean {
    return this is PsiComment || inDirectEnvironmentContext(Context.COMMENT)
}

/**
 * Runs the given predicate on the direct environment element of this psi element.
 *
 * @return `true` when the predicate tests `true`, or `false` when there is no direct environment or when the
 *              predicate failed.
 */
inline fun PsiElement.inDirectEnvironmentMatching(predicate: (LatexEnvironment) -> Boolean): Boolean {
    val environment = parentOfType(LatexEnvironment::class) ?: return false
    return predicate(environment)
}

/**
 * Checks if the psi element has a direct environment with the given context.
 */
fun PsiElement.inDirectEnvironmentContext(context: Environment.Context): Boolean {
    val environment = parentOfType(LatexEnvironment::class) ?: return context == Context.NORMAL
    return inDirectEnvironmentMatching {
        DefaultEnvironment.fromPsi(environment)?.context == context
    }
}

/**
 * Performs the given [action] on each child.
 */
inline fun PsiElement.forEachChild(action: (PsiElement) -> Unit) {
    for (child in children) action(child)
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// LATEX ELEMENTS ////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * Looks up the name of the environment in the required parameter.
 */
fun LatexEnvironment.name(): LatexNormalText? {
    return firstChildOfType(LatexNormalText::class)
}