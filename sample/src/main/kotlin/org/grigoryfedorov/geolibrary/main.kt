import org.grigoryfedorov.geolibrary.GeoLibrary


/**
 * Test main function
 */
fun main() {
    println("GeoLibrary sample start.")

    val geoLibrary = GeoLibrary()
    val pointFactory = geoLibrary.createPointFactory()
    val lineFactory = geoLibrary.createLineFactory()
    val polyLineFactory = geoLibrary.createPolyLineFactory()
    val rectangleFactory = geoLibrary.createRectangleFactory()
    val vectorFactory = geoLibrary.createVectorFactory()

    val point1 = pointFactory.createPoint(51.0, 61.0, 10.0)
    val point2 = pointFactory.createPoint(52.0, 62.0, 20.0)
    val point3 = pointFactory.createPoint(53.0, 63.0, 30.0)
    val point4 = pointFactory.createPoint(54.0, 64.0, 40.0)

    val line12 = lineFactory.createLine(point1, point2)
    val line34 = lineFactory.createLine(point3, point4)

    println("line12 length ${line12.length()} $line12")
    println("line34 length ${line34.length()} $line34")

    val polyLine = polyLineFactory.createPolyLine(listOf(point1, point2, point3, point4))

    for (line in polyLine.toLines()) {
        println("Length ${line.length()} for line $line")
    }

    val locationByDistance = polyLine.getLocationByDistance(100.0)
    println("locationByDistance $locationByDistance for polyline $polyLine")

    val rectangle = rectangleFactory.createRectangle(
        southWest = point1,
        northEast = point4
    )

    println("rectangle $rectangle")

    val contains = rectangle.contains(point2)

    println("rectangle contains $contains point $point2 $rectangle")

    val vector = vectorFactory.createVector(10.0, 20.0, 30.0)

    val translatedPoint1 = point1.translate(vector)
    val translatedLine12 = line12.translate(vector)
    val translatedRectangle = rectangle.translate(vector)
    val translatedPolyLine = polyLine.translate(vector)

    println("Point1 $point1 translated by $vector is $translatedPoint1")
    println("Point1 $line12 translated by $vector is $translatedLine12")
    println("Point1 $rectangle translated by $vector is $translatedRectangle")
    println("Point1 $polyLine translated by $vector is $translatedPolyLine")

    println("GeoLibrary sample finish.")

}
