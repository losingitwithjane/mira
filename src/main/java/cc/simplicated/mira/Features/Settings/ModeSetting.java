package cc.simplicated.mira.Features.Settings;

import cc.simplicated.mira.Features.Feature;

public class ModeSetting extends Setting<String> {
    private final String[] modes;

    public ModeSetting(String name, Feature parent, String def, String... modes) {
        super(name, parent, def);
        this.modes = modes;
    }

    public String[] getModes() { return modes; }
}