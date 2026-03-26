package cc.simplicated.mira.GUI.Widgets;

import cc.simplicated.mira.GUI.Colors;
import cc.simplicated.mira.Utilities.Math.Vec2d;
import cc.simplicated.mira.Utilities.Math.Vec2i;
import cc.simplicated.mira.client.miraClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseWidget extends AbstractWidget {
    public static final int baseHeight = 15;
    public static final int baseWidth  = 87;

    public static final Vec2i baseSize = new Vec2i(baseWidth, baseHeight);

    public Screen top;

    public BaseWidget(Vec2i pos, Vec2i size, String name, Screen top, BaseWidget parent) {
        super(pos.x, pos.y, size.x, size.y, Component.literal(name));

        this.parent = parent;
        this.top = top;
    }

    protected BaseWidget parent;
    protected List<BaseWidget> children = new ArrayList<>();

    private final Vec2d accumulatedPos = new Vec2d(0, 0);
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
    public boolean mouseClicked(MouseButtonEvent e, boolean left) {
        if (!this.isActive()) return false;

        boolean mouseOver = this.isMouseOver(e.x(), e.y());
        if (e.buttonInfo().button() == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            if (mouseOver) {
                this.playDownSound(Minecraft.getInstance().getSoundManager());
                this.onClick(e, true);

                return true;
            }
        } else if (e.buttonInfo().button() == GLFW.GLFW_MOUSE_BUTTON_RIGHT && mouseOver) {

            this.onClick(e, false);
        }

        return false;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        // Screw narration we HATE accessibility
        return;
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        // SCREW THIS
    }

    @Override
    public void onClick(MouseButtonEvent e, boolean left) {
        for (int i = children.size() - 1; i >= 0; i--) {
            BaseWidget widget = children.get(i);
            if (widget.isMouseOver(e.x(), e.y())) {
                widget.onClick(e, left);
                return;
            }
        }

        handleClick(e, left);
    }

    protected void handleClick(MouseButtonEvent e, boolean left) {

    }

    // Run before outlines thanks
    public void renderBackground(GuiGraphics g) {
        g.fill(getX() + 1, getY() + 1, getRight() - 1, getBottom() - 1, 0xFF0F0D0C);

        int rawX = getRight() - getX() - 2;
        int rawY = getBottom() - 1 - getY();

        for (int x = 0; x < rawX; x += 3) {
            for (int y = 0; y < rawY; y += 3) {
                int posX = x + getX();
                int posY = y + getY();

                posX += 1;
                posY += 1;
                if ((x % 2 == 1) ^ (y % 2 == 1)) {
                    g.fill(posX , posY, posX + 1, posY + 1, 0xFF222120);
                }
            }
        }
    }

    public void renderOutline(GuiGraphics g) {
        g.fill(getX(), getY(), getX() + 1, getBottom(), 0x9B8C64E2);
        g.fill(getX() + 1, getY(), getRight() - 1, getY() + 1, 0x9B8C64E2);

        g.fill(getX() + 1, getBottom() - 1, getRight() - 1, getBottom(), 0x9B8C64E2);
        g.fill(getRight() - 1, getY(), getRight(), getBottom(), 0x9B8C64E2);
    }

    public void renderOutline(GuiGraphics g, boolean notches) {
        if (!notches) {
            renderOutline(g);
            return;
        }

        int color = Colors.OUTLINE;
        g.fill(getX(), getY() + 1, getX() + 1, getBottom() - 1, color);
        g.fill(getX() + 1, getY(), getRight() - 1, getY() + 1, color);
        g.fill(getX() + 1, getBottom(), getRight() - 1, getBottom() - 1, color);
        g.fill(getRight(), getY() + 1, getRight() - 1, getBottom() - 1, color);
    }

    public void renderOutline(GuiGraphics g, boolean notches, int inset) {
        if (!notches) {
            renderOutline(g);
            return;
        }

        int color = Colors.OUTLINE;

        int left = getX() + inset;
        int right = getRight() - inset;
        int top = getY() + inset;
        int bottom = getBottom() - inset;

        g.fill(left, top + 1, left + 1, bottom - 1, color);
        g.fill(left + 1, top, right - 1, top + 1, color);
        g.fill(left + 1, bottom - 1, right - 1, bottom, color);
        g.fill(right - 1, top + 1, right, bottom - 1, color);
    }

    public void renderSeparator(GuiGraphics g, int y) {
        g.fill(getX() + 1, y, getRight() - 1, y + 1, 0xFF8C64E2);
        g.fill(getX() + 1, y + 1, getRight() - 1, y + 2, 0xFF694DA7);
    }


    Set<String> stringHistory = new HashSet<>();
    int previousHistorySize = 0;
    public void renderString(Text text, Vec2i pos, GuiGraphics g) {
        stringHistory.add(text.getText());
        g.drawString(getFont(), text.component, pos.x, pos.y, 0xFFFFFFFF);

        if (previousHistorySize != stringHistory.size()) {
            previousHistorySize = stringHistory.size();
            autoSize();
        }
    }

    public void autoSize() {
        int biggestEncounteredWidth = getWidth();
        for (BaseWidget widget : children) {
            widget.autoSize();
            biggestEncounteredWidth = Math.max(biggestEncounteredWidth, widget.getWidth());
        }

        for (String text : stringHistory) {
            if (getFont().width(text) > biggestEncounteredWidth)
                biggestEncounteredWidth = getFont().width(text) + 5;
        }

        setWidth(biggestEncounteredWidth);

        if (parent != null)
            parent.setWidth(Math.max(getWidth(), parent.getWidth()));
    }



    public static class Text {
        private final String text;
        private final Component component;

        public String getText() {
            return text;
        }

        public Component getComponent() {
            return component;
        }

        Text(Builder builder) {
            this.text = builder.text;
            this.component = builder.component;
        }

        public static class Builder {
            private String text;
            private boolean bold = false;
            private boolean shadow = false;
            private int color = 0xFFFFFFFF;
            private Component component;

            public Builder text(String text) {
                this.text = text;
                return this;
            }

            public Builder bold(boolean should) {
                this.bold = should;
                return this;
            }

            public Builder color(int color) {
                this.color = color;
                return this;
            }

            public Builder shadow(boolean should) {
                this.shadow = should;
                return this;
            }

            public Text build() {
                if (text == null) {
                    throw new RuntimeException("Text is null");
                }

                this.component = Component.literal(text).withStyle(style -> style
                        .withColor(this.color)
                        .withShadowColor(shadow ? 0x6E000000 : 0x00000000)
                );

                return new Text(this);
            }
        }
    }

    public static Component getText(String text) {
        return Component.literal(text.toLowerCase())
                .withStyle(style -> style.withFont(miraClient.font))
                ;
    }

    public static Component getText(String text, boolean bold) {
        if (!bold) return getText(text);
        return Component.literal(text)
                .withStyle(style -> style.withFont(miraClient.fontBold))
                ;
    }

    public static Font getFont() {
        return Minecraft.getInstance().font;
    }
}