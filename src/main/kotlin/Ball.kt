import vectors.Vector2d
import vectors.Vector2f
import vectors.Vector3

class Ball(
	val mass: Double,
	val radius: Double,
	val color: Vector3<Float>,
	var position: Vector2d,
	var velocity: Vector2f,
	var acceleration: Vector2f,
	private var box: Box,
	var previousPosition: Vector2d? = null,
	var collided: Boolean = false,
) {
	private val drawLines = true
	private var highest = bottom.toDouble()
	
	fun draw(delta: Double) {
		previousPosition = Vector2d(position.x, position.y)
		
		velocity.add(acceleration * delta)
		position.add(velocity * delta)
		box.handleBall(this, delta)
		
		val pos = position.getFloat()
		Utility.drawDot(pos.x, pos.y, radius, 15, color.x, color.y, color.z)
		
		if (drawLines) {
			if (collided) {
				if (position.y + radius > highest) {
					highest = position.y + radius
				}
				Utility.drawLine(Vector2d(left.toDouble(), highest), Vector2d(right.toDouble(), highest), Vector3(0f, 0f, 1f))
			}
			
			Utility.drawLine(Vector2d(left.toDouble(), position.y + radius), Vector2d(right.toDouble(), position.y + radius), Vector3(0f, 1f, 1f))
			
			Utility.drawLine(
				position,
				Vector2d(position.x + (0.5 * velocity.x), position.y + (0.5 * velocity.y)),
				Vector3(1f, 0f, 0f)
			)
		}
	}
}