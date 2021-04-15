package vectors

import Settings.halfHeight
import Settings.halfWidth

open class Vector2d(x: Double, y: Double): Vector2<Double>(x, y) {
	fun getFloat(): Vector2f {
		val xf: Float = (x.toFloat() / halfWidth) - 1
		val yf: Float = (y.toFloat() / halfHeight) - 1
		return Vector2f(xf, yf)
	}
	
	override fun add(vector2: Vector2<Float>) {
		x += vector2.x
		y += vector2.y
	}
	
	override fun times(double: Double): Vector2<Double> {
		return Vector2d(x * double, y * double)
	}
}