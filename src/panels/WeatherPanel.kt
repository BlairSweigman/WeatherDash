package panels

import workers.WeatherWorker
import java.awt.Color
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * main panel for application
 */
class WeatherPanel : JPanel()
{
    private val weatherLine = JLabel("Loading...")
    private val weatherIcon = JLabel()
    init {
       initGui()
    }

    /**
     * initGui - initialize the GUI
     */
    private fun initGui() {
        this.background = Color.CYAN
        this.add(weatherLine)
        this.add(weatherIcon)
        val worker = WeatherWorker(weatherLine,weatherIcon)
        worker.execute()
    }
}