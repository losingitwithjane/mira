package cc.simplicated.mira.features.Settings;

import cc.simplicated.mira.features.Feature;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, String category, Feature parent, boolean defaultValue) {
        super(name, category, parent, defaultValue);
    }

    public void toggle() {
        setValue(!getValue());
    }
}