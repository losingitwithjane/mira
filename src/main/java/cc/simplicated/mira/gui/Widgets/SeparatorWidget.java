package cc.simplicated.mira.gui.Widgets;

import cc.simplicated.mira.Utilities.math.Vec2i;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class SeparatorWidget extends BaseWidget {
    public SeparatorWidget(int y, Screen top, BaseWidget parent) {
        super(new Vec2i(parent.getX(), y), new Vec2i(parent.getWidth(), 2), "Separator", top, parent);
    }

    @Override
    protected void renderWidget(GuiGraphics g, int i, int j, float f) {
        renderSeparator(g, getY());
    }
}
