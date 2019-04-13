package nl.abby.plugintest.editor

import com.intellij.lang.Commenter

/**
 * @author Sten Wessel
 */
open class LatexCommenter : Commenter {

    override fun getLineCommentPrefix() = "%"

    override fun getBlockCommentPrefix() = ""

    override fun getBlockCommentSuffix() = ""

    override fun getCommentedBlockCommentPrefix() = ""

    override fun getCommentedBlockCommentSuffix() = ""
}
