package cc.simplicated.mira.GUI.Widgets;

import cc.simplicated.mira.GUI.Colors;
import cc.simplicated.mira.Utilities.Math.Vec2d;
import cc.simplicated.mira.Utilities.Math.Vec2i;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWidget extends AbstractWidget {
    public static final int baseHeight = 18;
    public static final int baseWidth  = 70;

    public static final Vec2i baseSize = new Vec2i(baseWidth, baseHeight);

    public BaseWidget(Vec2i pos, Vec2i size, String name) {
        super(pos.x, pos.y, size.x, size.y, Component.literal(name));
    }

    protected BaseWidget parent;
    protected List<BaseWidget> children = new ArrayList<>();

    private Vec2d accumulatedPos = new Vec2d(0, 0);
    @Override
    public void onDrag(MouseButtonEvent e, double x, double y) {
        if (parent != null) return;

        accumulatedPos.add(new Vec2d(x, y));
        this.setPosition(this.getX() + (int)accumulatedPos.x, this.getY() + (int)accumulatedPos.y);

        for (BaseWidget child : children) {
            child.handleDrag((int)accumulatedPos.x, (int)accumulatedPos.y);
        }

        accumulatedPos.sub(new Vec2d((int)accumulatedPos.x, (int)accumulatedPos.y));
    }

    public void handleDrag(int draggedX, int draggedY) {
        this.setPosition(this.getX() + draggedX, this.getY() + draggedY);
        for (BaseWidget child : children) {
            child.handleDrag(draggedX, draggedY);
        }
    }

    public List<BaseWidget> getChildren() {
        return children;
    }

    @Override
    protected void renderWidget(GuiGraphics g, int i, int j, float f) {
        // Placeholder, all widgets should implement/override renderWidget.
        //g.fill(this.getX(), this.getY(), this.getX() + 10, this.getY() + 10, 0x7d7d7dFF);

        renderBackground(g);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        // Screw narration we HATE accessibility
        return;
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        // I'll do dis sum time myself but long story short, default sound sounds shit
        return;
    }

    // Helper rendering?
    public void renderBackground(GuiGraphics g) {
        g.fill(getX(), getY(), getRight(), getBottom(), Colors.BACKGROUND);
    }
}