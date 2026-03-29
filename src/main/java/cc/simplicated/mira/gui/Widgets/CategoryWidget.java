package cc.simplicated.mira.gui.Widgets;

import cc.simplicated.mira.features.Feature;
import cc.simplicated.mira.Utilities.math.Vec2i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;

public class CategoryWidget extends BaseWidget {
    private static final int height = 12;

    public CategoryWidget(Vec2i pos, String name, Screen top, BaseWidget parent) {
        super(pos, new Vec2i(BaseWidget.baseWidth, BaseWidget.baseHeight + 5), name, top, parent);
    }

    @Override
    protected void renderWidget(GuiGraphics g, int i, int j, float f) {
        renderBackground(g);
        renderOutline(g, true);

        //g.fill(getX() + 1, getY() + 1, getRight() - 1, getBottom() - 1, 0x148C64E2);

        // Separator
        renderSeparator(g, getY() + 15);

        g.drawString(
                Minecraft.getInstance().font,
                getText(message.getString().toLowerCase(), true),
                getX() + 3,
                getY() + 1 + (BaseWidget.baseHeight / 2) - (Minecraft.getInstance().font.lineHeight / 2),
                0xFFFFFFFF);
    }

    int featuresInCategory = 0;
    public FeatureWidget addFeatureWidget(Feature feature, Screen top) {
        featuresInCategory++;
        FeatureWidget fw = new FeatureWidget(
                new Vec2i(getX() + 1, getY() + 1 + (FeatureWidget.height * featuresInCategory)),
                new Vec2i(getWidth() - 2, FeatureWidget.height),
                this,
                feature,
                top);

        setHeight(getHeight() + (FeatureWidget.height));

        addChild(fw);
        return fw;
    }

    @Override
    public void onClick(MouseButtonEvent mouseButtonEvent, boolean bl) {
        super.onClick(mouseButtonEvent, bl);
    }
}
