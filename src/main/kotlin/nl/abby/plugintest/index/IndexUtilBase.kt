package nl.abby.plugintest.index

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.StubIndexKey

/**
 * @author Ruben Schellekens
 */
abstract class IndexUtilBase<T : PsiElement>(

        /**
         * The class of the elements that are stored in the index.
         */
        val elementClass: Class<T>,

        /**
         * The key of the index.
         */
        val indexKey: StubIndexKey<String, T>
) {

    /**
     * Get the key of the index.
     */
    fun key() = indexKey
}