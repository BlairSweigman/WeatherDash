package frames

import panels.WeatherPanel
import java.lang.Exception
import javax.swing.ImageIcon
import javax.swing.JFrame

/**
 * WeatherFrame - JFrame for GUI
 *
 */
class WeatherFrame : JFrame() {

    init{
        createUI()
    }

    /**
     * cretateUI - creates the user interface, setting size, location and panel
     */
    private fun createUI() {
        title = "Weather Dash"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800,150)
        setLocationRelativeTo(null)
        val wp = WeatherPanel()
        contentPane = wp
        try {
            val url = javaClass.getResource("/img/snyder40.png")
            iconImage = ImageIcon(url).image
        }
        catch ( e: Exception) {
            println("Can't load Icon")
        }


    }

}