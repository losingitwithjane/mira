package cc.simplicated.mira.input;

import cc.simplicated.mira.features.Feature;

public class Keybind {
    Feature feature;

    public Keybind(Feature feature) {
        this.feature = feature;
    }

    public boolean keyboard = false;
    public int key = -1; // Aka uninitialized :)

    public void onClick() {
        feature.toggle();
        feature.onClick();
    }
}