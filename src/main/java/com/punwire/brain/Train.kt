package com.punwire.brain

class Train(val brain: Brain) {
    val inputs: MutableList<List<Float>> = mutableListOf()
    val outputs = listOf<Float>(-1.0f, 1.0f)
    var currentInput = 0
    var currentSeq = 0
    fun init() {
        var input = listOf<Float>(1.0f)
        inputs.add(input)
        input = listOf<Float>(-1.0f)
        inputs.add(input)
    }

    fun next() {
        if (currentInput >= inputs.size) currentInput = 0
        brain.setInput(currentSeq, inputs.get(currentInput))

        // Calculate output stating from inputs
        val result = brain.calc(currentSeq)

        //Got to Next Input
        currentInput++

        currentSeq++
    }
}