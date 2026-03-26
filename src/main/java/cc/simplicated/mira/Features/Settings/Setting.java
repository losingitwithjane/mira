package cc.simplicated.mira.Features.Settings;

import cc.simplicated.mira.Features.Feature;

public abstract class Setting<T> {
    private final String name;
    private final String category;
    private final Feature parent;
    private T value;

    public Setting(String name, String category, Feature parent, T defaultValue) {
        this.name = name;
        this.category = category;
        this.parent = parent;
        this.value = defaultValue;

        parent.addSetting(this);
    }

    public String getName() { return name; }
    public String getCategory() { return category; }

    public Feature getParent() { return parent; }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }
}