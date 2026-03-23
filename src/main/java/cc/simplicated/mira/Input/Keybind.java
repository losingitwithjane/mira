package cc.simplicated.mira.Input;

import cc.simplicated.mira.Features.Feature;

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