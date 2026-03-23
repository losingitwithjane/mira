package cc.simplicated.mira.Features.Settings;

import cc.simplicated.mira.Features.Feature;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, Feature parent, boolean defaultValue) {
        super(name, parent, defaultValue);
    }

    public void toggle() {
        setValue(!getValue());
    }
}