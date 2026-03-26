package cc.simplicated.mira.Features.Settings;

import cc.simplicated.mira.Features.Feature;

public class ModeSetting extends Setting<String> {
    private final String[] modes;

    public ModeSetting(String name, String category, Feature parent, String def, String... modes) {
        super(name, category, parent, def);
        this.modes = modes;
    }

    public String[] getModes() { return modes; }
}