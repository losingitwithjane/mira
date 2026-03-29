package cc.simplicated.mira.gui.Widgets;

import cc.simplicated.mira.Utilities.math.Vec2i;
import cc.simplicated.mira.gui.Screens.BaseScreen;
import cc.simplicated.mira.gui.Screens.SettingScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class PanelWidget extends BaseWidget {
    public PanelWidget(Vec2i pos, Vec2i size, Screen top, BaseWidget parent) {
        super(pos, size, "Panel", top, parent);
        setDraggable(false);
    }

    public static Vec2i size = new Vec2i(Minecraft.getInstance().screen.width / 3, Minecraft.getInstance().screen.height / 3);
    public PanelWidget(Vec2i pos, Screen top, BaseWidget parent) {
        super(pos, size, "Panel", top, parent);
    }

    @Override
    protected void renderWidget(GuiGraphics g, int i, int j, float f) {
        super.renderWidget(g, i, j, f);
        super.renderOutline(g);

        renderInnerPanel(g, getX(), getY(), getWidth(), getHeight());
    }
}
