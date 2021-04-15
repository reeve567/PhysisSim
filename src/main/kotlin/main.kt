import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryUtil.NULL
import java.lang.RuntimeException
import java.util.*

private var window: Long = 0
private val box: Box = Box()

fun main() {
	init()
	
	GL.createCapabilities()
	
	GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
	
	var lastDate = Date().time
	while (!glfwWindowShouldClose(window)) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
		
		GL11.glLoadIdentity()
		
		val time = Date().time
		
		loop((time - lastDate) / 1000.0)
		lastDate = time
		
		glfwSwapBuffers(window)
		// key events
		glfwPollEvents()
	}
}

fun init() {
	GLFWErrorCallback.createPrint(System.err).set()
	
	if (!glfwInit()) {
		throw IllegalStateException("No GLFW")
	}
	
	glfwDefaultWindowHints()
	glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
	glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
	
	window = glfwCreateWindow(Settings.windowWigth, Settings.windowHeight, "Physics", NULL, NULL)
	
	if (window == NULL) {
		throw RuntimeException("no window")
	}
	
	glfwMakeContextCurrent(window)
	
	glfwSwapInterval(1)
	
	glfwShowWindow(window)
}

fun loop(delta: Double) {
	box.draw(delta)
}