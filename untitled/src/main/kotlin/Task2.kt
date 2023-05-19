class Node(var value: Int) {
    var left: Node? = null
    var right: Node? = null
}

class Tree {
    private var root: Node? = null

    private fun insert(current: Node?, value: Int): Node {
        current ?: return Node(value)
        when {
            value < current.value -> current.left = insert(current.left, value)
            value > current.value -> current.right = insert(current.right, value)
            else -> return current
        }
        return current
    }

    fun insert(value: Int) {
        root = insert(root, value)
        println("Элемент добавлен: $value")
    }

    private fun containswh(current: Node?, value: Int): Boolean {
        current ?: return false

        return when {
            value < current.value -> containswh(current.left, value)
            value > current.value -> containswh(current.right, value)
            else -> true
        }
    }

    fun contains(value: Int): Boolean {
        print("Есть элемент $value? - ")
        return containswh(root, value)
    }

    private fun removewh(qq: Node?, value: Int): Node? {
        qq ?: return null

        when {
            value < qq.value -> qq.left = removewh(qq.left, value)
            value > qq.value -> qq.right = removewh(qq.right, value)
            else -> {
                when {
                    qq.left == null -> return qq.right
                    qq.right == null -> return qq.left
                    else -> {
                        qq.value = findMin(qq.right)
                        qq.right = removewh(qq.right, qq.value)
                    }
                }
            }
        }
        return qq
    }

    fun remove(value: Int) {
        root = removewh(root, value)
        println("Удален элемент: $value")
    }

    private fun findMin(node: Node?): Int {
        return node?.left?.let {
            findMin(it)
        }
            ?: node?.value ?: throw IllegalArgumentException("Корень не может быть пустым.")
    }

    private fun conversion(node: Node?, result: MutableList<Int>) {
        node?.let {
            conversion(it.left, result)
            result.add(it.value)
            conversion(it.right, result)
        }
    }

    fun all(): List<Int> {
        print("Все элементы дерева: ")
        val result = mutableListOf<Int>()
        conversion(root, result)
        return result
    }
}

fun main() {
    val binaryTree = Tree()
    binaryTree.insert(11)
    binaryTree.insert(6)
    binaryTree.insert(21)
    binaryTree.insert(17)
    binaryTree.insert(4)
    binaryTree.insert(5)
    binaryTree.insert(15)
    println()

    println(binaryTree.all())
    println()

    println(binaryTree.contains(17))
    println(binaryTree.contains(14))
    println()

    binaryTree.remove(21)
    println()

    println(binaryTree.all())
}