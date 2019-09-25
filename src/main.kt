import java.awt.EventQueue

fun main() {
    println("Start.")
    EventQueue.invokeLater(::createAndShowGUI)
}

private fun createAndShowGUI() {

    val frame = DisplayWindow()
    frame.isVisible = true
}