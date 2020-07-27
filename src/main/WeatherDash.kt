package main

import frames.WeatherFrame

import java.awt.EventQueue

/**
 * WeatherDash
 * @author Blair Sweigman

 */

/**
 * creates GUI Frame for WeatherDash
 */
fun createFrame() {
    val frame = WeatherFrame()
    frame.isVisible = true


}

/**
 * Main function for WeatherDash
 * @param args = Currently not used
 */
fun main(args: Array<String>) {

    EventQueue.invokeLater ( ::createFrame )
}