package com.punwire.brain

class Brain {
    val layers: MutableList<Layer> = mutableListOf()
    val inputs: MutableList<Node> = mutableListOf()
    val outputs: MutableList<Node> = mutableListOf()

    companion object {
        fun create(): Brain {
            val brain = Brain()
            return brain
        }
    }

    fun dump() {
        println("***************************")
        for (layer in layers) {
            println("  Layer.id " + layer.id)
            layer.dump()
        }
    }

    fun calc(seq: Int): List<Float> {
        for (l in 1..layers.size - 1) {
            val layer = layers[l]
            for (n in layer.nodes.values) {
                n.calc(seq)
            }
        }
        var result = mutableListOf<Float>()
        for (onode in outputs) {
            result.add(onode.output)
        }
        return result
    }

    fun setInput(seq: Int, inps: List<Float>) {
        for (n in inputs.indices) {
            inputs[n].seq = seq
            inputs[n].output = inps[n]
        }
    }

    fun addLayer(): Layer {
        val layer = Layer(layers.size)
        layers.add(layer)
        return layer
    }

    fun inputLayer(): Layer {
        return layers.get(0);
    }

    fun outputLayer(): Layer {
        return layers.get(layers.size - 1);
    }
}