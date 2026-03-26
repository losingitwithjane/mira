package cc.simplicated.mira.GUI.Widgets;

import cc.simplicated.mira.Features.FeatureManager;
import cc.simplicated.mira.Features.Settings.Setting;
import cc.simplicated.mira.GUI.Colors;
import cc.simplicated.mira.GUI.Screens.BaseScreen;
import cc.simplicated.mira.GUI.Screens.SettingScreen;
import cc.simplicated.mira.Utilities.Math.Vec2i;
import cc.simplicated.mira.client.miraClient;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

import java.util.Set;
import java.util.stream.Collectors;

public class SettingPanelWidget extends BaseWidget {
    private final FeatureWidget featureWidget;
    public static final Vec2i size = new Vec2i(55, 10);
    public SettingPanelWidget(FeatureWidget featureWidget, Vec2i pos, Screen top, BaseWidget parent) {
        super(pos, size, "what", top, parent);
        this.featureWidget = featureWidget;

        Set<String> categories = featureWidget.getFeature().getSettings()
                .stream()
                .map(setting -> setting.getCategory())
                .collect(Collectors.toSet());

        int currentY = getY() + 3;
        for (String category : categories) {
            LabelWidget label = new LabelWidget(new Vec2i(getX() + 2, currentY), new Text.Builder()
                    .text(category)
                    .bold(false)
                    .color(0xFFFFFFFF)
                    .shadow(true)
                    .build(), top, this);
            children.add(label);
            currentY += 13;

            for (Setting<?> setting : featureWidget.getFeature().getSettings()) {
                if (!setting.getCategory().equals(category)) continue;

            }
        }
    }

    @Override
    protected void renderWidget(GuiGraphics g, int i, int j, float f) {
        super.renderWidget(g, i, j, f);
        super.renderOutline(g);


    }
}
