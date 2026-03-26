package cc.simplicated.mira.GUI.Widgets;

import cc.simplicated.mira.Utilities.Math.Vec2i;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

public class LabelWidget extends BaseWidget {
    Text text;
    public LabelWidget(Vec2i pos, Text text, Screen top, BaseWidget parent) {
        super(pos, new Vec2i(0, 0), text.getText(), top, parent);
        this.text = text;
    }

    @Override
    protected void renderWidget(GuiGraphics g, int i, int j, float f) {
        g.drawString(getFont(), getText("Penis"), getX(), getY(), 0xFFFFFFFF);
    }
}
