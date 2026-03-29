package cc.simplicated.mira.gui.Screens;

import cc.simplicated.mira.features.Feature;
import cc.simplicated.mira.features.FeatureManager;
import cc.simplicated.mira.gui.Widgets.CategoryWidget;
import cc.simplicated.mira.gui.Widgets.FeatureWidget;
import cc.simplicated.mira.Utilities.math.Vec2i;
import net.minecraft.client.gui.screens.Screen;

import java.util.*;

public class ClickGUIScreen extends BaseScreen {
    public static boolean opened = false;

    public ClickGUIScreen(Screen previousScreen) {
        super("ClickGUI", previousScreen);
    }

    @Override
    protected void init() {
        Map<String, CategoryWidget> categoryWidgets = new HashMap<>();
        for (String category : FeatureManager.getAllCategories()) {
            CategoryWidget cw = new CategoryWidget(new Vec2i(20, 20), category, this, null);
            categoryWidgets.put(category, cw);
            addRenderableWidget(cw);
            System.out.println("Added category: " + category);
        }
        System.out.println("Done initializing categories");

        for (Feature feature : FeatureManager.getAllFeatures()) {
            CategoryWidget categoryWidget = categoryWidgets.get(feature.getCategory());
            if (categoryWidget == null) continue; // Theoretically should never be possible

            FeatureWidget featureWidget = categoryWidget.addFeatureWidget(feature, this);
            addRenderableWidget(featureWidget);

            addChildren(featureWidget);

            System.out.println("Added feature: " + feature.getName());
        }
        System.out.println("Done initializing features");
    }

    @Override
    public void onClose() {
        super.onClose();
        ClickGUIScreen.opened = false;
    }
}