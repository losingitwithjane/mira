package cc.simplicated.mira.GUI.Widgets;

import cc.simplicated.mira.Features.Feature;
import cc.simplicated.mira.GUI.Screens.SettingScreen;
import cc.simplicated.mira.Utilities.Math.Vec2i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class FeatureWidget extends BaseWidget {
    private Feature feature;
    public static final int height = 17;

    public FeatureWidget(Vec2i position, Vec2i size, BaseWidget parent, Feature feature, Screen top) {
        super(position, size, feature.getName(), top, parent);
        this.feature = feature;
    }

    public Component getTitle() {
        return Component.empty();
    }

    public Feature getFeature() {
        return feature;
    }

    @Override
    public void onClick(MouseButtonEvent e, boolean left) {
        super.onClick(e, left);
    }

    @Override
    protected void handleClick(MouseButtonEvent e, boolean left) {
        if (left) {
            feature.toggle();
        } else {
            Minecraft.getInstance().setScreen(new SettingScreen(Minecraft.getInstance().screen, this));
        }
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        super.playDownSound(soundManager);
    }

    @Override
    protected void renderWidget(GuiGraphics g, int i, int j, float f) {
        //g.fill(getX(), getY(), getRight(), getBottom(), 0xFFFFFFFF);
        renderOutline(g, true, 1);

        int inset = 1;

        g.fillGradient(getX() + 2, getY() + inset + 1, getRight() - 2, getBottom() - inset - 1, 0xFF110F0E, 0xFF262626);
        if (feature.isEnabled()) {

        }
//        g.fill(getX() + 2, getY() + inset, getRight() - 2, getBottom() - inset, feature.isEnabled() ? 0x78a8a8a8 : 0x782e2e2e);
        g.drawString(getFont(), new Text.Builder()
                        .text(feature.getName())
                        .color(feature.isEnabled() ? 0xFFBDA0FA : 0x07b3b3b3)
                        .shadow(feature.isEnabled())
                        .bold(feature.isEnabled())
                        .build().getComponent(),
                getX() + 3, getY() + 5, 0xFFFFFFFF);
    }
}
