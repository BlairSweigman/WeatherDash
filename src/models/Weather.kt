package models

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Weather - model for Weather
 * @param id weather id
 * @param city location of forecast
 * @param temp current temperature
 * @param humidity relative humidity
 * @param conditions current weather conditions
 * @param icon OpenWeather icon for conditions
 * @param timestamp time when current weather observations were taken
 * @param timezone time zone of city
 *
 */
data class Weather (
        var id: String,
        var city: String,
        var temp: String,
        var humidity: String,
        var conditions: String,
        var icon: String,
        var timestamp: Long,
        var timezone: Int
 ) {
    /**
     * toString - converts model into a nice readable string
     * @return String to display or save
     */
    override fun toString(): String {

        return "Conditions in $city: $conditions, Temperature: $temp\u00B0C, Humidity: $humidity%, last updated: " +
                formatTimeStamp()
    }

    /**
     * toFile - Saves model to file under reports (creates if does not exists)
     */
    fun toFile() {
        val fDir = File("reports")
        if (!fDir.exists()) {
            fDir.mkdir()
        }
        val filename = "reports/" + this.city.replace("\\W+".toRegex(), "") + "$timestamp.txt"
        val writer = BufferedWriter(FileWriter(filename))
        writer.write(this.toString() +"\n")
        writer.close()
    }

    /**
     * formatTimeStamp - converts Unix timestamp to ISOish format
     * @return String formatted timestamp
     */
    private fun formatTimeStamp() : String {
        val date = Date(timestamp * 1000L)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(date)

    }
}

