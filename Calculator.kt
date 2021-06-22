import kotlin.math.*

class Point (val x: Double, val y: Double, val z: Double) { //Points in 3D space


    fun read() {
        print("(%.2f\t|\t ".format(x))
        print("%.2f\t|\t".format(y))
        print("%.2f)".format(z))
    }

}

class Vector (p1: Point, cam: Point) { //Vector from camera to point

    val x = p1.x - cam.x
    val y = p1.y - cam.y
    val z = p1.z - cam.z

}

fun factor (z1: Double, z2: Double): Double { //Factor of vector to reach z=0 from point

    val factor = z2/(z1*(-1))
    //println("z1: $z1, z2: $z2; Faktor: $factor")

    return factor
}

fun mult (p: Point, v: Vector, f: Double) : Point { //Using vector and factor to calculate the vector from point to z=0

    val x = p.x + (v.x * f)
    val y = p.y + (v.y * f)
    val z = p.z + (v.z * f)

    return Point(x, y, z)

}

fun dist (p1: Point, p2: Point): Double {
    return sqrt((p1.x-p2.x).pow(2)+(p1.y-p2.y).pow(2))
}



fun main () {

    //First, initializing Points
    val camPosition = Point(15.0, 18.0, 30.0)
    val bottomBackLeft = Point(0.0, 0.0, 0.0)
    val topLeft = Point(1.5, 0.0, 2.5)
    val bottomFrontLeft = Point(3.0, 0.0, 0.0)
    val bottomBackRight = Point(0.0, 6.0, 0.0)
    val topRight = Point(1.5, 6.0, 2.5)
    val bottomFrontRight = Point(3.0, 6.0, 0.0)
    val parallelCamVector = Vector(Point(0.0, 0.0, 0.0), camPosition)

    //Next, creating lists
    val vector = ArrayList<Vector>()
    val factor = ArrayList<Double>()
    val point = arrayListOf(bottomBackLeft, topLeft, bottomFrontLeft, bottomBackRight, topRight, bottomFrontRight)
    val endP = ArrayList<Point>()
    val parallelFactor = ArrayList<Double>()
    val parallelEndP = ArrayList<Point>()

    //Iterate through lists for each point
    for (i in 0..5) {
        vector.add(Vector(point[i], camPosition))
        factor.add(factor(vector[i].z, point[i].z))
        endP.add(mult(point[i], vector[i], factor[i]))
        parallelFactor.add(factor(parallelCamVector.z, point[i].z))
        parallelEndP.add(mult(point[i], parallelCamVector,parallelFactor[i]))
    }

    //println("\t\t\t(\tx\t|\t\ty\t|\tz\t)")

    println("\t\t\t\t\t\tZentralprojektion    ||    Parallelprojektion")
    for (i in 0..5) {
        print("Punkt ${i+1}:\t")
        endP[i].read()
        print("    ||    ")
        parallelEndP[i].read()
        println()
    }

}