package cc.simplicated.mira.Features.Settings;

import cc.simplicated.mira.Features.Feature;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, String category, Feature parent, boolean defaultValue) {
        super(name, category, parent, defaultValue);
    }

    public void toggle() {
        setValue(!getValue());
    }
}