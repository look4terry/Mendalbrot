import java.awt.EventQueue

// Entry point for running the application
fun main() {
    println("Start.")
    EventQueue.invokeLater(::createAndShowGUI)
}

//  Create the window
private fun createAndShowGUI() {
    val frame = DisplayWindow()
    frame.isVisible = true
}