package cc.simplicated.mira.client;

import cc.simplicated.mira.Features.FeatureManager;
import cc.simplicated.mira.Input.InputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class miraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register((client) -> {
            if (!InputHandler.initialized)
                InputHandler.init(client.getWindow().handle());
        });

        FeatureManager.init();
    }
}
