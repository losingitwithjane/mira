package cc.simplicated.mira.Input;

import cc.simplicated.mira.GUI.Screens.ClickGUIScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

public class InputHandler {
    public static boolean initialized = false;

    private static int beforeScale = 2;
    private static Screen beforeScreen = null;
    public static void init(long window) {
        GLFWKeyCallbackI keyCallback = glfwSetKeyCallback(window, null);
        GLFWMouseButtonCallbackI mouseCallback = glfwSetMouseButtonCallback(window, null);

        glfwSetKeyCallback(window, (w, key, scancode, action, mods) -> {
//            InputHandler.setKeyState(key, action != GLFW.GLFW_RELEASE);

            if (action == GLFW.GLFW_PRESS) {
                passToKeybinds(key, true);
                if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
                    Minecraft client = Minecraft.getInstance();
                    if (ClickGUIScreen.opened) {
                        client.setScreen(beforeScreen);
                        ClickGUIScreen.opened = false;
                        client.options.guiScale().set(beforeScale);
                        client.getWindow().setGuiScale(client.options.guiScale().get());
                    } else {
                        beforeScreen = client.screen;
                        ClickGUIScreen.opened = true;
                        client.setScreen(new ClickGUIScreen(beforeScreen));
                        beforeScale = client.options.guiScale().get();
                        client.options.guiScale().set(2);
                        client.getWindow().setGuiScale(client.options.guiScale().get());
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