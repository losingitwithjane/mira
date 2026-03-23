package cc.simplicated.mira.Input;

import cc.simplicated.mira.GUI.ClickGUIScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

public class InputHandler {
    public static boolean initialized = false;
    public static void init(long window) {
        GLFWKeyCallbackI keyCallback = glfwSetKeyCallback(window, null);
        GLFWMouseButtonCallbackI mouseCallback = glfwSetMouseButtonCallback(window, null);

        glfwSetKeyCallback(window, (w, key, scancode, action, mods) -> {
//            InputHandler.setKeyState(key, action != GLFW.GLFW_RELEASE);

            if (action == GLFW.GLFW_PRESS) {
                passToKeybinds(key, true);
                if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
                    Minecraft client = Minecraft.getInstance();
                    if (client.screen instanceof ClickGUIScreen) {
                        client.setScreen(null);
                    } else {
                        Minecraft.getInstance().setScreen(new ClickGUIScreen());
                    }
                }
            }

            if (keyCallback != null)
                keyCallback.invoke(w, key, scancode, action, mods);
        });

        glfwSetMouseButtonCallback(window, (w, button, action, mods) -> {
//            InputHandler.setButtonState(button, action != GLFW.GLFW_RELEASE);
            if (action == GLFW.GLFW_PRESS) {
                passToKeybinds(button, false);
            }

            if (mouseCallback != null)
                mouseCallback.invoke(w, button, action, mods);
        });

        initialized = true;
    }

    private static void passToKeybinds(int key, boolean keyboard) {

    }
}