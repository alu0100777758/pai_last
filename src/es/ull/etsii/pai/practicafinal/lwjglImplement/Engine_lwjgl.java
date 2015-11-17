package es.ull.etsii.pai.practicafinal.lwjglImplement;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.Sys;
import org.lwjgl.egl.EGLSetBlobFuncANDROID;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL45;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.lwjglImplement.graphics.GLRectangle;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.RvsB_World;

public class Engine_lwjgl {
	private static final String WINDOW_TITLE = "Red VS Blue";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	private long window;
	private GLFWvidmode vidmode;
	private RvsB_World map;
	
	public void run() {
		System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
		try {
			init();
			loop();

			// Release window and window callbacks
			glfwDestroyWindow(getWindow());
			getKeyCallback().release();
		} finally {
			// Terminate GLFW and release the GLFWErrorCallback
			glfwTerminate();
			getErrorCallback().release();
		}
	}
	public void init() {

		setErrorCallback(Callbacks.errorCallbackPrint(System.err));
		glfwSetErrorCallback(getErrorCallback());

		if (glfwInit() != GL11.GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

		setWindow(glfwCreateWindow(WIDTH, HEIGHT, WINDOW_TITLE,
				NULL, NULL));

		if (getWindow() == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		glfwSetKeyCallback(getWindow(), keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action,
					int mods) {
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
					glfwSetWindowShouldClose(window, GL_TRUE);
			}
		});
		setVidmode(new GLFWvidmode(glfwGetVideoMode(glfwGetPrimaryMonitor())));
		glfwSetWindowPos(getWindow(), (getVidmode().getWidth() - WIDTH) / 2,
				(getVidmode().getHeight() - HEIGHT) / 2);

		glfwMakeContextCurrent(getWindow());
		glfwSwapInterval(1);
		glfwShowWindow(getWindow());
	}

	public void loop() {
		GL.createCapabilities();
		glMatrixMode(GL_PROJECTION);
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		ArrayList<GLRectangle> rects = new ArrayList<>();
		// rects.add(new GLRectangle(0, 0, 200, 200,
		// texture.bufferedImageToTexture(ResourceManager.getInstance().getBufferedImage("textures/blue.png"))));
		rects.add(new GLRectangle(200, 0, 200, 200, texture
				.bufferedImageToTexture(ResourceManager.getInstance()
						.getBufferedImage("textures/red.png"))));
		// rects.add(new GLRectangle(0, 200, 200, 200,
		// texture.bufferedImageToTexture(ResourceManager.getInstance().getBufferedImage("textures/pistol.png"))));
		// rects.add(new GLRectangle(200, 200, 200, 200,
		// texture.bufferedImageToTexture(ResourceManager.getInstance().getBufferedImage("textures/rocket.png"))));

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (glfwWindowShouldClose(window) == GL_FALSE) {
			glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the
																// framebuffer
			// textureTest(textureID);
			for (GLRectangle draw : rects) {
				draw.draw();
			}
			for(Entity enti : getMap().getStaticMap()){
				RectangularShape rs = enti.getShape();
				GLRectangle rect = new GLRectangle((int)rs.getBounds().getMinX(), (int)rs.getBounds().getMinY(), (int)rs.getWidth(), (int)rs.getHeight());
				rect.draw();
			}
			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
			glfwSwapBuffers(window);

		}
	}
	public void loadMap(String mapPath){
		try {
			setMap(new RvsB_World(BvsR_Map.load(mapPath)));
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (ClassNotFoundException e) {
			System.out.println("class not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ****************************** Getters & Setters
	// *********************************

	public GLFWErrorCallback getErrorCallback() {
		return errorCallback;
	}

	public void setErrorCallback(GLFWErrorCallback errorCallback) {
		this.errorCallback = errorCallback;
	}

	public GLFWKeyCallback getKeyCallback() {
		return keyCallback;
	}

	public void setKeyCallback(GLFWKeyCallback keyCallback) {
		this.keyCallback = keyCallback;
	}

	public long getWindow() {
		return window;
	}

	public void setWindow(long windows) {
		this.window = windows;
	}

	public GLFWvidmode getVidmode() {
		return vidmode;
	}

	public void setVidmode(GLFWvidmode vidmode) {
		this.vidmode = vidmode;
	}

	public RvsB_World getMap() {
		return map;
	}

	public void setMap(RvsB_World map) {
		this.map = map;
	}
	
}
