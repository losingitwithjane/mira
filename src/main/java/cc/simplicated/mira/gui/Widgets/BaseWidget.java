package cc.simplicated.mira.gui.Widgets;

import cc.simplicated.mira.client.MiraClient;
import cc.simplicated.mira.gui.Colors;
import cc.simplicated.mira.renderer.MiraRenderPipelines;
import cc.simplicated.mira.Utilities.math.Vec2d;
import cc.simplicated.mira.Utilities.math.Vec2i;
import cc.simplicated.mira.renderer.PanelRenderState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.joml.Matrix3x2f;
import org.jspecify.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWidget extends AbstractWidget {
    public static final int baseHeight = 15;
    public static final int baseWidth  = 87;

    public static final Vec2i baseSize = new Vec2i(baseWidth, baseHeight);

    public Screen top;

    public static final Identifier PANEL_BG = Identifier.fromNamespaceAndPath("mira", "textures/gui/bg.png");

    public BaseWidget(Vec2i pos, Vec2i size, String name, Screen top, BaseWidget parent) {
        super(pos.x, pos.y, size.x, size.y, Component.literal(name));

        this.parent = parent;
        this.top = top;
    }

    private boolean draggable = true;
    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    protected BaseWidget parent;
    private List<BaseWidget> children = new ArrayList<>();

    private final Vec2d accumulatedPos = new Vec2d(0, 0);
    @Override
    public void onDrag(MouseButtonEvent e, double x, double y) {
        if (parent != null || !isDraggable()) return;

        accumulatedPos.add(new Vec2d(x, y));
        this.setPosition(this.getX() + (int)accumulatedPos.x, this.getY() + (int)accumulatedPos.y);

        for (BaseWidget child : children) {
            child.handleDrag((int)accumulatedPos.x, (int)accumulatedPos.y);
        }

        accumulatedPos.sub(new Vec2d((int)accumulatedPos.x, (int)accumulatedPos.y));
    }

    public void handleDrag(int draggedX, int draggedY) {
        this.setPosition(this.getX() + draggedX, this.getY() + draggedY);
        for (BaseWidget widget : children) {
            widget.handleDrag(draggedX, draggedY);
        }
    }

    public List<BaseWidget> getChildren() {
        return children;
    }
    public void addChild(BaseWidget widget) {
        children.add(widget);
        addedChild(widget);
    }

    // Override me
    public void addedChild(BaseWidget widget) {
        // hi
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
        submitPanel(g, MiraRenderPipelines.PANEL_BG, getX() + 2, getY() + 2, getRight() - 2, getBottom() - 2, 0xFF222120, 0);
//        int rawX = getRight() - getX() - 2;
//        int rawY = getBottom() - 1 - getY();
//
//        for (int x = 0; x < rawX; x += 3) {
//            for (int y = 0; y < rawY; y += 3) {
//                int posX = x + getX();
//                int posY = y + getY();
//
//                posX += 1;
//                posY += 1;
//                if ((x % 2 == 1) ^ (y % 2 == 1)) {
//                    g.fill(posX , posY, posX + 1, posY + 1, 0xFF222120);
//                }
//            }
//        }
    }

    public void submitPanel(GuiGraphics g, RenderPipeline renderPipeline, int i, int j, int k, int l, int m, @Nullable Integer integer) {
        g.guiRenderState.submitGuiElement(
                new PanelRenderState(
                        renderPipeline, TextureSetup.noTexture(), g.pose(), i, j, k, l, m, integer != null ? integer : m, g.scissorStack.peek()
                )
        );
    }

    void renderInnerPanel(GuiGraphics g, int x, int y, int w, int h) {
        g.guiRenderState.submitGuiElement(
                new PanelRenderState(
                        MiraRenderPipelines.INNER_PANEL, TextureSetup.noTexture(), g.pose(), x, y, w, h, 0xFFFFFFFF, 0xFFFFFFFF, g.scissorStack.peek()
                )

        );
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

    public void autoSize() {
        Vec2i totalSize = new Vec2i(0, 0);

        for (BaseWidget widget : getChildren()) {
            totalSize.add(widget.getWidth(), widget.getHeight());
        }

        setSize(totalSize.x, totalSize.y);
        System.out.println("New size:\n" + totalSize.toString());

//        int biggestEncounteredWidth = getWidth();
//        for (BaseWidget widget : children) {
//            widget.autoSize();
//            biggestEncounteredWidth = Math.max(biggestEncounteredWidth, widget.getWidth());
//        }
//        setWidth(biggestEncounteredWidth);
//
//        if (parent != null)
//            parent.setWidth(Math.max(getWidth(), parent.getWidth()));
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
                .withStyle(style -> style.withFont(MiraClient.font))
                ;
    }

    public static Component getText(String text, boolean bold) {
        if (!bold) return getText(text);
        return Component.literal(text)
                .withStyle(style -> style.withFont(MiraClient.fontBold))
                ;
    }

    public static Font getFont() {
        return Minecraft.getInstance().font;
    }
}