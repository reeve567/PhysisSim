import vectors.Vector2d
import vectors.Vector2f
import vectors.Vector2i
import vectors.Vector3
import kotlin.math.abs

val bottom = Settings.boxDistance + 20
val top = Settings.windowHeight - Settings.boxDistance - Settings.extraTopDistance

val left = Settings.boxDistance
var right = Settings.windowWigth - Settings.boxDistance

private val bottomLeft = Vector2i(left, bottom)
private val bottomRight = Vector2i(right, bottom)

private val topRight = Vector2i(right, top)
private val topLeft = Vector2i(left, top)

class Box {
	private val balls = mutableListOf<Ball>()
	
	init {
		for (i in 0 until Settings.ballAmount) {
			balls.add(Ball(
				1.0, // mass with no unit
				25.0, // radius in pixels
				Vector3(1f, 1f, 1f), // color
				Vector2d(Settings.halfWidth.toDouble(), Settings.halfHeight.toDouble()), // pos
				Vector2f(150f, 0f), // vel
				Vector2f(0f, -250f), // acc
				this
			))
		}
	}
	
	fun draw(delta: Double) {
		
		// bottom
		Utility.drawLine(bottomLeft, bottomRight, Vector3(1f, 1f, 1f))
		
		// left
		Utility.drawLine(bottomLeft, topLeft, Vector3(1f, 1f, 1f))
		
		//right
		Utility.drawLine(bottomRight, topRight, Vector3(1f, 1f, 1f))
		
		//top
		Utility.drawLine(topLeft, topRight, Vector3(1f, 1f, 1f))
		
		for (ball in balls) {
			ball.draw(delta)
		}
	}
	
	fun handleBall(ball: Ball, delta: Double) {
		if (ball.previousPosition == null) return
		
		val difference = Vector2d(
			abs(ball.previousPosition!!.x - ball.position.x),
			abs(ball.previousPosition!!.y - ball.position.y)
		)
		
		if ((ball.position.y >= (top - ball.radius) && ball.velocity.y >= 0) || (ball.position.y <= (bottom + ball.radius) && ball.velocity.y <= 0)) {
			
			ball.position = ball.previousPosition!!
			
			val y = if (ball.velocity.y <= 0) {
				bottom + ball.radius
				
			} else {
				top - ball.radius
			}
			val perc = abs(ball.previousPosition!!.y - y) / difference.y
			
			//val x = ball.position.x  + ((ball.previousLocation!!.x - ball.position.x) * perc)
			
			//val impactPoint = Vector2d(x, y)
			ball.velocity = Vector2f(ball.velocity.x, -ball.velocity.y)
			
			
			val percMove = 1 - (2 * perc)
			
			ball.position.add(Vector2f(0f, (ball.velocity.y * percMove * delta).toFloat()))
			
			ball.collided = true
		} else if (ball.position.x >= (right - ball.radius) || ball.position.x <= (left + ball.radius)) {
			ball.velocity = Vector2f(-ball.velocity.x, ball.velocity.y)
		}
	}
}