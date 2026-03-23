package cc.simplicated.mira.Features.Settings;

import cc.simplicated.mira.Features.Feature;

public abstract class Setting<T> {
    private final String name;
    private final Feature parent;
    private T value;

    public Setting(String name, Feature parent, T defaultValue) {
        this.name = name;
        this.parent = parent;
        this.value = defaultValue;

        parent.addSetting(this);
    }

    public String getName() { return name; }
    public Feature getParent() { return parent; }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }
}