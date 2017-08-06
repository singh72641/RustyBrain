package com.punwire.brain

open class Node(val id: Int, val type: NodeType, val afunc: (Float) -> Float) {
    var output: Float = 0.0f
    var seq: Int = -1
    val inputs: MutableList<Connection> = mutableListOf()

    companion object {
        var nodeSeq: Int = 0

        fun nextId(): Int {
            nodeSeq++
            return nodeSeq
        }
    }

    fun calc(s: Int): Float {
        if (seq == s) return output
        var out = 0.0f
        for (nc in inputs) {
            out += nc.weight * nc.source.calc(s)
        }
        output = afunc.invoke(out)
        return output
    }

    fun dump() {
        println("       Node  - ${id}(${type}) -->  ${output}")
        for (nc in inputs) {
            println("              ---->      ${nc.source.id}(${nc.source.type}) @  ${nc.weight}   *    ${nc.source.output}")
        }
    }
}