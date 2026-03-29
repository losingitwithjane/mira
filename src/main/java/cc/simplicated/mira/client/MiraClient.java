package cc.simplicated.mira.client;

import cc.simplicated.mira.features.FeatureManager;
import cc.simplicated.mira.input.InputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.resources.Identifier;

public class MiraClient implements ClientModInitializer {
    public static FontDescription.Resource font;
    public static FontDescription.Resource fontBold;

    public static final Identifier fontRef = Identifier.fromNamespaceAndPath("mira", "font");
    public static final Identifier fontboldRef = Identifier.fromNamespaceAndPath("mira", "fontbold");

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register((client) -> {
            if (!InputHandler.initialized)
                InputHandler.init(client.getWindow().handle());
        });
        FeatureManager.init();

        font = new FontDescription.Resource(fontRef);
        fontBold = new FontDescription.Resource(fontboldRef);
    }
}
