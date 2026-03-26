package cc.simplicated.mira.GUI.Screens;

import cc.simplicated.mira.GUI.Widgets.BaseWidget;
import cc.simplicated.mira.GUI.Widgets.FeatureWidget;
import cc.simplicated.mira.GUI.Widgets.SettingPanelWidget;
import cc.simplicated.mira.Utilities.Math.Vec2i;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class SettingScreen extends BaseScreen {
    private final FeatureWidget featureWidget;
    public SettingScreen(Screen previousScreen, FeatureWidget featureWidget) {
        super("SettingScreen", previousScreen);
        this.featureWidget = featureWidget;
    }

    @Override
    protected void init() {
        Window window = Minecraft.getInstance().getWindow();
        Vec2i pos = new Vec2i((((window.getWidth() / 2) / Minecraft.getInstance().options.guiScale().get()) - (SettingPanelWidget.size.x / 2)), (((window.getHeight() / 2) / Minecraft.getInstance().options.guiScale().get()) - (SettingPanelWidget.size.y / 2)));

        SettingPanelWidget widget = new SettingPanelWidget(featureWidget, pos, this, null);

        addRenderableWidget(widget);
        addChildren(widget);
    }
}
