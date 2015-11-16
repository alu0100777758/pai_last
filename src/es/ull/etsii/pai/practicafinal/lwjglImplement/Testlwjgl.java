package es.ull.etsii.pai.practicafinal.lwjglImplement;

import java.util.ArrayList;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import es.ull.etsii.pai.practicafinal.graphics.Drawable;
import es.ull.etsii.pai.practicafinal.lwjglImplement.graphics.GLRectangle;
import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
 
public class Testlwjgl {
	int WIDTH = 1200;
	int HEIGHT = 1000;
 
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
 
    // The window handle
    private long window;
 
    public void run() {
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
        try {
            init();
            loop();
 
            // Release window and window callbacks
            glfwDestroyWindow(window);
            keyCallback.release();
        } finally {
            // Terminate GLFW and release the GLFWErrorCallback
            glfwTerminate();
            errorCallback.release();
        }
    }
 
    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
    	errorCallback = Callbacks.errorCallbackPrint(System.err);
        glfwSetErrorCallback(errorCallback);
        
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
 
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
 
    
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);

        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
 
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
            }
        });
 
        // Get the resolution of the primary monitor
        GLFWvidmode vidmode = new GLFWvidmode(glfwGetVideoMode(glfwGetPrimaryMonitor()));
        // Center our window
        glfwSetWindowPos(
            window,
            (vidmode.getWidth() - WIDTH) / 2,
            (vidmode.getHeight() - HEIGHT) / 2
        );
 
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
 
        // Make the window visible
        glfwShowWindow(window);
        
    }
 
    private void loop() {
    	
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, WIDTH, HEIGHT, 0, -1, 1); //2D projection matrix
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        // Set the clear color
        ArrayList<GLRectangle> rects = new ArrayList<>();
//        rects.add(new GLRectangle(0, 0, 200, 200, texture.bufferedImageToTexture(ResourceManager.getInstance().getBufferedImage("textures/blue.png"))));
        rects.add(new GLRectangle(200, 0, 200, 200, texture.bufferedImageToTexture(ResourceManager.getInstance().getBufferedImage("textures/red.png"))));
//        rects.add(new GLRectangle(0, 200, 200, 200, texture.bufferedImageToTexture(ResourceManager.getInstance().getBufferedImage("textures/pistol.png"))));
//        rects.add(new GLRectangle(200, 200, 200, 200, texture.bufferedImageToTexture(ResourceManager.getInstance().getBufferedImage("textures/rocket.png"))));

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
        	glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
//            textureTest(textureID);
            for(GLRectangle draw : rects){
            	draw.draw();
            }
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            glfwSwapBuffers(window);
            
        }
    }
    private void textureTest(int textureID){
 glClear(GL_COLOR_BUFFER_BIT);
         
         //Enable blending so the green background can be seen through the texture
         glEnable(GL_BLEND);
         glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
         
         glPushMatrix();
         glTranslatef(100, 100, 0);
         glBindTexture(GL_TEXTURE_2D, textureID);
         glBegin(GL_QUADS);
         {
            glTexCoord2f(0, 0);
            glVertex2f(0, 0);
            
            glTexCoord2f(1, 0);
            glVertex2f(128, 0);
            
            glTexCoord2f(1, 1);
            glVertex2f(128, 128);
            
            glTexCoord2f(0, 1);
            glVertex2f(0, 128);
         }
         glEnd();
         glPopMatrix();
         glfwSwapBuffers(window);
    }
 
    public static void main(String[] args) {
        new Testlwjgl().run();
    }
 
}
