package com.punwire.brain

import org.apache.commons.math3.util.FastMath

class Layer(val id: Int) {
    val nodes: MutableMap<Int, Node> = mutableMapOf()

    fun addNode(type: NodeType): Node {
        var afunc = Layer.npFunc
        if (type == NodeType.NN) afunc = Layer.nnFunc
        if (type == NodeType.NH) afunc = Layer.reluFunc
        if (type == NodeType.NH) afunc = Layer.reluFunc
        val node = Node(Node.nextId(), type, afunc)
        nodes.put(node.id, node)
        return node
    }


    fun dump() {
        for (node in nodes.values) {
            node.dump();
        }
    }

    companion object {
        val npFunc: (Float) -> Float = { wo -> if (wo > 0) 1.0f else 0.0f }
        val nnFunc: (Float) -> Float = { wo -> if (wo < 0) -1.0f else 0.0f }
        val sigmoidFunc: (Float) -> Float = { wo -> (1 / (1 + FastMath.exp(-1 * wo.toDouble()))).toFloat() }
        val reluFunc: (Float) -> Float = { wo -> FastMath.max(wo, 0.0f) }
        val linearFunc: (Float) -> Float = { wo -> wo }
        val dnFunc: (Float) -> Float = { wo -> if (wo > 0.5) 1.0f else if (wo < -0.5f) -1.0f else 0.0f }
    }
}