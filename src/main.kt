import java.awt.EventQueue

// Entry point for running the application
fun main() {
    println("Start.")
    EventQueue.invokeLater(::createAndShowGUI)
}

//  Create the window
private fun createAndShowGUI() {
    // Equivalent to
    // DisplayWindow frame = new DisplayWindow in Java or C#
    val frame = DisplayWindow()

    // Make if visible
    frame.isVisible = true
}