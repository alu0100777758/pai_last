package es.ull.etsii.pai.practicafinal.lwjglImplement;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

import org.lwjgl.Sys;

public class Testlwjgl {
	int WIDTH = 1200;
	int HEIGHT = 1000;


	public void run() {
		Engine_lwjgl engine = new Engine_lwjgl();
		System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
		try {
			engine.init();
			engine.loop();

			// Release window and window callbacks
			glfwDestroyWindow(engine.getWindow());
			engine.getKeyCallback().release();
		} finally {
			// Terminate GLFW and release the GLFWErrorCallback
			glfwTerminate();
			engine.getErrorCallback().release();
		}
	}

	public static void main(String[] args) {
		Engine_lwjgl engine = new Engine_lwjgl();
		engine.loadMap("maps/esto2.rvsbm");
		engine.run();
	}

}
