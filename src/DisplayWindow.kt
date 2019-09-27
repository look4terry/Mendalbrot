import java.awt.BorderLayout
import java.awt.Graphics
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import java.awt.image.BufferedImage
import javax.swing.JFrame

// This is the is the real working class
class DisplayWindow : JFrame() {

    private val resolution = 1000

    private var xStart = -2.0
    private var xEnd = 1.0
    private var yStart = 1.5
    private var yEnd = -1.5
    private var zoomChanged = false

    // Explicit constructor for the class, this is used when you have
    // more than just initializing properties
    init {
        createUI()
    }

    // Setup the window and display the first image
    private fun createUI() {
        title = "Mandelbrot Set"
        defaultCloseOperation = EXIT_ON_CLOSE
        location = Point(50,50)
        contentPane.layout = BorderLayout()

        setSize(resolution, resolution)

        addMouseListener( object : MouseAdapter() {

            override fun mousePressed(e: MouseEvent) {
                pressedPoint = e.point
            }

            override fun mouseReleased(e: MouseEvent) {
                releasedPoint = e.point

                val xDiff = Math.abs(pressedPoint.x - releasedPoint.x)
                val yDiff = Math.abs(pressedPoint.y - releasedPoint.y)
                if(xDiff or yDiff < 50)
                    return

                val xWidth = xEnd - xStart

                val xEndPercent = releasedPoint.x.toDouble() / width
                xEnd = xStart + (xWidth * xEndPercent)

                val xStartPercent = pressedPoint.x.toDouble() / width
                xStart += xWidth * xStartPercent

                val yHeight = yEnd - yStart

                val yEndPercent = releasedPoint.y.toDouble() / height
                yEnd = yStart + (yHeight * yEndPercent)

                val yStartPercent = pressedPoint.y.toDouble() / height
                yStart += yHeight * yStartPercent

                zoomChanged = true

                repaint()
            }
        })

        addMouseMotionListener( object : MouseMotionAdapter() {

            override fun mouseDragged(e: MouseEvent) {
                val currentPoint = e.point

                repaint()

                graphics.drawLine(pressedPoint.x, pressedPoint.y, currentPoint.x, pressedPoint.y)  // Top
                graphics.drawLine(pressedPoint.x, pressedPoint.y, pressedPoint.x, currentPoint.y)  // Left
                graphics.drawLine(currentPoint.x, pressedPoint.y, currentPoint.x, currentPoint.y)  // Right
                graphics.drawLine(pressedPoint.x, currentPoint.y, currentPoint.x, currentPoint.y)  // Bottom
            }
        })
    }

    private var xSize = 0
    private var ySize = 0
    private var img = BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB)

    private fun generateImage(g : Graphics){
        if((width != xSize) or (height != ySize) or zoomChanged) {
            xSize = width
            ySize = height
            zoomChanged = false

            val xInc = (xEnd - xStart) / xSize
            val yInc = (yEnd - yStart) / ySize
            img = BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB)

            repeat(ySize) { x ->
                repeat(xSize) {y ->
                    val real = x * xInc + xStart
                    val imaginary = y * yInc + yStart
                    val c = Complex(real, imaginary)
                    val v = isSet(c) / 50.0
                    val alp = (256.0 * v).toInt()

                    val red = 80
                    val grn = 80
                    val blu = 200
                    val p = (alp shl 24) or (red shl 16) or (grn shl 8) or blu
                    img.setRGB(x, y, p)
                }
           }
        }
        g.color = background
        g.fillRect(0,0,width,height)
        g.drawImage(img, 0, 0, null)
    }

    private fun isSet(c: Complex): Int
    {
        var z = Complex(0.0, 0.0)
        repeat(50) {
            z = z.square() + c

            if(z.abs() > 2.0)
                return it
        }

        return 50
    }

    override fun paint(g : Graphics)
    {
        generateImage(g)
    }

    companion object {
        private var pressedPoint : Point = Point(0,0)
        private var releasedPoint : Point = Point(0,0)
    }
}