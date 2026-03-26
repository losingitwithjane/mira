package cc.simplicated.mira.GUI.Screens;

import cc.simplicated.mira.GUI.Widgets.BaseWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class BaseScreen extends Screen {
    private final Screen previousScreen;
    protected BaseScreen(String name, Screen previousScreen) {
        super(Component.literal(name));
        this.previousScreen = previousScreen;
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(previousScreen);
    }

    protected void addChildren(BaseWidget widget) {
        System.out.println("Name: " + widget.getMessage().getString());
        for (BaseWidget child : widget.getChildren()) {
            addRenderableWidget(child);
            addChildren(child);
        }
    }

    public void addRenderable(BaseWidget widget) {
        addRenderableWidget(widget);
    }
}
