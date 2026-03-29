package cc.simplicated.mira.gui.Screens;

import cc.simplicated.mira.gui.Widgets.FeatureWidget;
import cc.simplicated.mira.gui.Widgets.PanelWidget;
import cc.simplicated.mira.Utilities.math.Vec2i;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class SettingScreen extends BaseScreen {
    public final FeatureWidget featureWidget;
    public SettingScreen(Screen previousScreen, FeatureWidget featureWidget) {
        super("SettingScreen", previousScreen);
        this.featureWidget = featureWidget;
    }

    @Override
    protected void init() {
        Minecraft client = Minecraft.getInstance();
        Vec2i windowSize = new Vec2i(client.getWindow().getWidth() / client.options.guiScale().get(), client.getWindow().getHeight() / client.options.guiScale().get());

        Vec2i size = new Vec2i(
                (int)(windowSize.x / 1.5),
                (int)(windowSize.y / 1.5)
        );

        Vec2i pos = new Vec2i(
                (windowSize.x / 2) - (size.x / 2),
                (windowSize.y / 2) - (size.y / 2)
        );
        System.out.println(pos);

        PanelWidget widget = new PanelWidget(pos, size, this, null);

        addRenderableWidget(widget);
        addChildren(widget);
    }
}
