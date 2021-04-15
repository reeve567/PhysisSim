import org.lwjgl.opengl.GL11
import vectors.Vector2d
import vectors.Vector2i
import vectors.Vector3
import kotlin.math.cos
import kotlin.math.sin

object Utility {
	fun drawDot(cx: Float, cy: Float, rad: Double, segments: Int, r: Float, g: Float, b: Float) {
		val xRadius = rad / Settings.halfWidth
		val yRadius = rad / Settings.halfHeight
		GL11.glColor3f(r, g, b)
		
		GL11.glBegin(GL11.GL_TRIANGLE_FAN)
		GL11.glVertex2f(cx, cy)
		for (i in 0..segments) {
			GL11.glVertex2f(
				(cx + xRadius * cos(i * (2 * Math.PI) / segments)).toFloat(),
				(cy + yRadius * sin(i * (2 * Math.PI) / segments)).toFloat()
			)
		}
		GL11.glEnd()
	}
	
	fun drawLine(one: Vector2d, two: Vector2d, color: Vector3<Float>) {
		GL11.glColor3f(color.x, color.y, color.z)
		
		GL11.glBegin(GL11.GL_LINES)
		
		val floatOne = one.getFloat()
		val floatTwo = two.getFloat()
		
		GL11.glVertex2f(floatOne.x, floatOne.y)
		GL11.glVertex2f(floatTwo.x, floatTwo.y)
		
		GL11.glEnd()
	}
}