package com.punwire.brain

fun main(args: Array<String>) {

    val brain = Brain.create()
    // Add Input Layer
    val iLayer = brain.addLayer()
    val node = iLayer.addNode(NodeType.NI)
    brain.inputs.add(node)

    // Add tick Layer
    val tLayer = brain.addLayer()
    var nNode = tLayer.addNode(NodeType.NN)
    var pNode = tLayer.addNode(NodeType.NP)

    //Connect tick layer
    nNode.inputs.add(Connection(node, nNode, 1.0f))
    pNode.inputs.add(Connection(node, pNode, 1.0f))

    // Add Hidden Layer
    val hLayer = brain.addLayer()
    var hnode = hLayer.addNode(NodeType.NH)

    //Add output Layer
    val oLayer = brain.addLayer()
    val oNode = oLayer.addNode(NodeType.NO)
    brain.outputs.add(oNode)


    val train = Train(brain)
    train.init()
    train.next()
    brain.dump()
    train.next()
    brain.dump()
}