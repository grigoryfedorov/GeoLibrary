import org.grigoryfedorov.geolibrary.DEFAULT_DISTANCE_CALCULATOR
import org.grigoryfedorov.geolibrary.DEFAULT_POINT_ON_LINE_FINDER
import org.grigoryfedorov.geolibrary.GeoLibrary
import org.grigoryfedorov.geolibrary.extentions.getLocationByDistance
import org.grigoryfedorov.geolibrary.extentions.length
import org.grigoryfedorov.geolibrary.extentions.toLines


/**
 * Test main function
 */
fun main(args: Array<String>) {
    println("Hello World!")

    val geoLibrary = GeoLibrary()
    val pointFactory = geoLibrary.createDefaultPointFactory()
    val polyLineFactory = geoLibrary.createDefaultPolyLineFactory()

    val point1 = pointFactory.createPoint(50.0, 60.0, 0.0)
    val point2 = pointFactory.createPoint(51.0, 62.0, 0.0)

    val polyLine = polyLineFactory.createPolyLine(listOf(point1, point2))

    for (line in polyLine.toLines()) {
        println("Length ${line.length(DEFAULT_DISTANCE_CALCULATOR)} for line $line")
    }

    val locationByDistance = polyLine.getLocationByDistance(DEFAULT_POINT_ON_LINE_FINDER, 100.0)

    println("locationByDistance $locationByDistance for polyline $polyLine")


}
