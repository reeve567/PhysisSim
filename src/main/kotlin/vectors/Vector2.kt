package vectors

abstract class Vector2<T: Number>(var x: T, var y: T) {
	abstract fun add(vector2: Vector2<Float>)
	abstract operator fun times(double: Double): Vector2<T>
	override fun toString(): String {
		return "[$x, $y]"
	}
}