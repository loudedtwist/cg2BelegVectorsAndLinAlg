package lwjgl;

import framentLoops.FragmentLoop;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
public class BasisWindow {

    // The window handle
    private long window;

    private final String title ;
    FragmentLoop fragmentLoop;
    private int width = 300;
    private int height = 300;


    public BasisWindow(int width, int height, String title, FragmentLoop fragmentLoop) {
        this.height = height;
        this.width = width;
        this.fragmentLoop = fragmentLoop;
        this.title = title;
    }
    public BasisWindow(int width, int height) {
        this(width, height, "title", null);
    }
    public BasisWindow() {
        this(null);
    }
    public BasisWindow(FragmentLoop fragmentLoop) {
        this(300,300,"title", fragmentLoop);
    }

    public void setFragmentLoop(FragmentLoop drawer) {
        this.fragmentLoop = drawer;
    }

    // We need to strongly reference callback instances.

    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;

    public void run() {
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
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GLFW_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GLFW_TRUE); // We will detect this in our rendering loop
            }
        });
        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2
        );
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(window);
    }
    private void loop() {
        GL.createCapabilities();
        // Set the clear color
        glClearColor(0.3f, 0.3f, 0.3f, 0.3f);
        // Run the rendering loop until the user has attempted to close
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity(); // Resets any previous projection matrices
            glOrtho(0, width, height, 0, 1, -1);
            glMatrixMode(GL_MODELVIEW);
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(window) == GLFW_FALSE ) {
            //glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT);

            if(fragmentLoop !=null)
                fragmentLoop.loop();

            glfwSwapBuffers(window); // swap the color buffers
            glfwPollEvents();
        }
    }
    public static void main(String[] args) {
        new BasisWindow().run();
    }
} // source code from https://www.lwjgl.org/guide