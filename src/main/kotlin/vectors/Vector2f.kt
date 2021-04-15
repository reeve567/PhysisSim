package vectors

class Vector2f(x: Float, y: Float): Vector2<Float>(x, y) {
	override fun add(vector2: Vector2<Float>) {
		x += vector2.x
		y += vector2.y
	}
	
	override fun times(double: Double): Vector2<Float> {
		return Vector2f(x * double.toFloat(), y * double.toFloat())
	}
	
}