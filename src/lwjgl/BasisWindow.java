package lwjgl;

import renderingLayers.ILoop;
import org.lwjgl.glfw.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.System.exit;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.NULL;

abstract public class BasisWindow {

    final int FRAMES_PER_SECOND = 60;
    final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    private long windowHandle;

    ArrayList<ILoop> layerLoops;

    private final String title;

    private int width = 300;
    private int height = 300;

    int shaderProgramm;
    int vertexShader;
    int fragmentShader;
    String vertexShaderSource = "";
    String fragmentShaderSource = "";

    Calendar calendarFPS;
    long next_game_tick;

    int scale = 0;
    int vz = 1;

    public BasisWindow(int width, int height, String title) {
        this.height = height;
        this.width = width;
        this.title = title;
        layerLoops = new ArrayList<>();
    }

    public BasisWindow(int width, int height) {
        this(width, height, "title");
    }

    public BasisWindow() {
        this(300, 300, "title");
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;
    private GLFWCursorPosCallback cursorPosCallback;
    private GLFWMouseButtonCallback mouseButtonCallback;

    public void run() {
        try {
            init();
            loop();
        } finally {
            terminate();
        }
    }

    private void terminate() {
        releaseEventListeners();
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
        errorCallback.release();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        initWindowHandle();

        setupWindow();

        createCapabilities();

        glClearColor(0.1f, 0.1f, 0.1f, 0.1f);
        glOrtho(0.0, MyWindow.WINDOW_WIDTH, 0.0, MyWindow.WINDOW_HEIGHT, -1.0, 1.0);

        initLoopFPSTimer();
        setEventListeners();
        loadShadersSource();
        initShaders();

        for (ILoop iLoop : layerLoops) {
            iLoop.init();
        }
    }

    private void loop() {

        while (glfwWindowShouldClose(windowHandle) == GLFW_FALSE) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glUseProgram(shaderProgramm);

            insideLoop();

            changeParameterInShader();

            glEnd();
            glUseProgram(0);

            glfwSwapBuffers(windowHandle);
            glfwPollEvents();
            sleepUntilNextGameTick(calendarFPS, next_game_tick);
        }

        destroyProgramAndShaders();
    }

    private void setupWindow() {
        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our windowHandle
        glfwSetWindowPos(windowHandle, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the windowHandle visible
        glfwShowWindow(windowHandle);
    }

    private void initWindowHandle() {
        // Configure our windowHandle
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the windowHandle will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the windowHandle will be resizable
        // Create the windowHandle
        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowHandle == NULL)
            throw new RuntimeException("Failed to create the GLFW windowHandle");
    }

    private void initLoopFPSTimer() {
        calendarFPS = Calendar.getInstance();
        next_game_tick = calendarFPS.getTimeInMillis();
    }

    abstract public void insideLoop();

    private void releaseEventListeners() {
        if (mouseButtonCallback != null) {
            mouseButtonCallback.release();
        }
        if (cursorPosCallback != null) {
            cursorPosCallback.release();
        }
        if (keyCallback != null) {
            keyCallback.release();
        }
    }

    private void changeParameterInShader() {
        int loc = glGetUniformLocation(shaderProgramm, "Scale");
        if (scale > 100) vz = -1;
        if (scale < 1) vz = 1;
        scale += vz;
        if (loc != -1) {
            glUniform1f(loc, scale / 100.f);
        }
    }

    private void destroyProgramAndShaders() {
        glDeleteProgram(shaderProgramm);
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }


    public void loadShadersSource() {
        try {
            vertexShaderSource = readFileAsString("/Users/warik/IdeaProjects/cg2BelegVersion2/shaders/shader.vert");
            fragmentShaderSource = readFileAsString("/Users/warik/IdeaProjects/cg2BelegVersion2/shaders/shader.frag");
        } catch (Exception e) {
            e.printStackTrace();
            exit(0);
        }
    }

    private void initShaders() {

        shaderProgramm = glCreateProgram();
        vertexShader = glCreateShader(GL_VERTEX_SHADER);
        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);

        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException("Error creating vertex shader\n"
                    + glGetShaderInfoLog(vertexShader, glGetShaderi(vertexShader, GL_INFO_LOG_LENGTH)));
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);

        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException("Error creating vertex shader\n"
                    + glGetShaderInfoLog(fragmentShader, glGetShaderi(fragmentShader, GL_INFO_LOG_LENGTH)));

        glAttachShader(shaderProgramm, vertexShader);
        glAttachShader(shaderProgramm, fragmentShader);
        glLinkProgram(shaderProgramm);
        if (glGetProgrami(shaderProgramm, GL_LINK_STATUS) == GL_FALSE)
            throw new RuntimeException("Unable to link shader program:");
        glValidateProgram(shaderProgramm);
    }

    private void setMouseEvents() {
        glfwSetMouseButtonCallback(windowHandle, mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                System.out.println("BUTTON : " + button + "  ACTION " + action + "   MODS " + mods);
            }

        });
        glfwSetCursorPosCallback(windowHandle, cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                int state = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
                if (state == GLFW_PRESS) {
                    //layerLoops.mouseMovingWithButtonPressed(new Vektor2D(xpos,ypos));
                    System.out.println("X: " + xpos + "  Y: " + ypos);
                }
            }
        });
    }

    private void sleepUntilNextGameTick(Calendar calendar, long next_game_tick) {
        long sleep_time = 0;
        next_game_tick += SKIP_TICKS;
        sleep_time = next_game_tick - calendar.getTimeInMillis();
        if (sleep_time >= 0) {
            //System.out.println("going to sleep : "+sleep_time + "  msek");
            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String readFileAsString(String filename) throws Exception {
        StringBuilder source = new StringBuilder();

        FileInputStream in = new FileInputStream(filename);

        Exception exception = null;

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            Exception innerExc = null;
            try {
                String line;
                while ((line = reader.readLine()) != null)
                    source.append(line).append('\n');
            } catch (Exception exc) {
                exception = exc;
            } finally {
                try {
                    reader.close();
                } catch (Exception exc) {
                    if (innerExc == null)
                        innerExc = exc;
                    else
                        exc.printStackTrace();
                }
            }

            if (innerExc != null)
                throw innerExc;
        } catch (Exception exc) {
            exception = exc;
        } finally {
            try {
                in.close();
            } catch (Exception exc) {
                if (exception == null)
                    exception = exc;
                else
                    exc.printStackTrace();
            }

            if (exception != null)
                throw exception;
        }

        return source.toString();
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public void setCursorPosCallback(GLFWCursorPosCallback cursorPosCallback) {
        this.cursorPosCallback = cursorPosCallback;
    }

    private void setEventListeners() {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(windowHandle, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GLFW_TRUE); // We will detect this in our rendering loop
            }
        });
    }

    //euler-integratiion
}