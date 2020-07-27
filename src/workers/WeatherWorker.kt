package workers


import models.Weather
import network.OpenWeather
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.awt.image.BufferedImage
import java.io.*
import java.lang.Exception
import java.net.URL
import java.util.*
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.SwingWorker

/** WeatherWorker - Parses JSON from OpenWeather API and displays weather in gui and saves
 * data under reports
 *
 * @param status JLabel containing status
 * @param icon JLabel holding icon
 */
class WeatherWorker(private var status: JLabel, private var icon : JLabel) : SwingWorker<Weather?, Void>() {

    private lateinit var img : BufferedImage
    private val properties = Properties()


    /**
     * doInBackGround - gets Data from API and processes it
     * @return Weather
     * @throws Exception if getData or loadConfig has error
     */
    override fun doInBackground(): Weather? {
        try {
            loadConfig()
            return getData()
        }
        catch (e: Exception) {
            throw  e
        }
    }

    /**
     * done - sets weather status and icon
     */
    override fun done() {
        try {
            get()?.let {
                status.text = it.toString()
                icon.icon = ImageIcon(img)
                it.toFile()
            }
        }
        catch (e: Exception) {
            status.text ="Error: " + e.message
           // println(e.message)
        }
        finally {
            super.done()
        }

    }

    /**
     * getDate -retrieves data from OpenWeather and icon image
     * @return Weather model
     * @throws IOException on network or file error
     */
    private fun getData() : Weather? {
        val url = URL(OpenWeather.API_URL +
                "?id=" + properties.getProperty("cityid") +
                "&units=" + properties.getProperty("units")+
                "&appid=" + properties.getProperty("appid"))
        val weather : Weather
        try {
            val inReader : Reader = InputStreamReader(url.openStream())

            val obj= JSONParser().parse(inReader)
            val jo: JSONObject  = obj as JSONObject

            val wMain = jo["main"] as JSONObject
            val arrWeather = jo["weather"] as JSONArray
            val sWeather = arrWeather[0] as JSONObject

            weather = Weather(
                    jo["id"].toString(),
                    jo["name"].toString(),
                    wMain["temp"].toString(),
                    wMain["humidity"].toString(),
                    sWeather["main"].toString(),
                    sWeather["icon"].toString(),
                    jo["dt"].toString().toLong(),
                    jo["dt"].toString().toInt()
            )
            img = ImageIO.read(URL(OpenWeather.IMG_URL + weather.icon + ".png"))
            return weather
        } catch (e: IOException) {

            throw  IOException("Unable to retrieve data from server")
        }

    }

    /**
     * Loads configuration that contains city id, appid and units
     * @throws IOException on file error
     */
    private fun loadConfig() {
        try {
            val inStream= FileInputStream("config.ini")
            properties.load(inStream)
        } catch (e: IOException) {
          throw IOException("Error opening config.ini, please check if file exists")
        }
    }
}