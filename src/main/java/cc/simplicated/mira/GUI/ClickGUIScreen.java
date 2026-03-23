package cc.simplicated.mira.GUI;

import cc.simplicated.mira.Features.FeatureManager;
import cc.simplicated.mira.GUI.Widgets.CategoryWidget;
import cc.simplicated.mira.Utilities.Math.Vec2i;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Set;
import java.util.stream.Collectors;

public class ClickGUIScreen extends Screen {
    public ClickGUIScreen() {
        super(Component.literal("ClickGUI"));
    }

    @Override
    protected void init() {
        // We have to initialize things in a kinda gross way due to minecraft's Screen system I'm sorry :(
        Set<String> uniqueCategories = FeatureManager.getAllFeatures()
                .stream()
                .map(feature -> feature.getCategory())
                .collect(Collectors.toSet());

        for (String category : uniqueCategories) {
            addRenderableWidget(new CategoryWidget(new Vec2i(20, 20), category));
            System.out.println("Added category: " + category);
        }
        System.out.println("Done initializing the GUI");
    }
}