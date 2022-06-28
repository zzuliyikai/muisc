package com.hanshow.myapplication

import org.junit.Test

import org.junit.Assert.*
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class htmldsl {

    interface Node {
        fun render(): String
    }

    class BlockNode(var name: String) : Node {
        var children = ArrayList<Node>()
        var properties = HashMap<String, Any>()
        override fun render(): String {
            // <html>xxx</html>
            return """<$name ${properties.map { "${it.key} = '${it.value}'" }.joinToString(" ")}>${
                children.joinToString(" ") { it.render() }
            }</$name>"""
        }

        operator fun String.invoke(block: BlockNode.() -> Unit) {
            val node = BlockNode(this)
            node.block()
            this@BlockNode.children.add(node)
        }

        operator fun String.invoke(value: Any) {
            this@BlockNode.properties[this] = value
        }

        operator fun String.unaryPlus() {
            this@BlockNode.children.add(StringNode(this))
        }


    }

    class StringNode(var content: String) : Node {
        override fun render(): String {
            return content
        }

    }

    fun html(block: BlockNode.() -> Unit): BlockNode {
        val html = BlockNode("html")
        html.block()
        return html
    }

    fun BlockNode.head(block: BlockNode.() -> Unit) {
        val head = BlockNode("head")
        head.block()
        this.children.add(head)
    }

    fun BlockNode.body(block: BlockNode.() -> Unit) {
        val body = BlockNode("body")
        body.block()
        this.children.add(body)
    }


    @Test
    fun addition_isCorrect() {
        println("-------------start-----------------")


        val htmlContent = html {
            head {
                "meta" { "charset" ("utf-8") }
            }
            body {
                "dev"{
                    "style"(
                        """
                width:200 px,
                height:200 px,
                line - height:200 px,
                background - color:#ff0000
                """.trimIndent()
                    )
                }

                "span"{
                    "style"(
                        """
                color:white;
                """.trimIndent()
                    )
                    +"hello!"
                }
            }

        }.render()


        File("kotlin.html").writeText(htmlContent)

        println()
        println("-------------end-----------------")
    }

    fun def(index: Int): String {
        return "index$index"
    }
}

