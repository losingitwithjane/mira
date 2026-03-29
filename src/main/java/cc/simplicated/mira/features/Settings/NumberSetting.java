package cc.simplicated.mira.features.Settings;

import cc.simplicated.mira.features.Feature;

public class NumberSetting extends Setting<Double> {
    private final double min, max, increment;

    public NumberSetting(String name, String category, Feature parent, double def, double min, double max, double inc) {
        super(name, category, parent, def);
        this.min = min;
        this.max = max;
        this.increment = inc;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getIncrement() { return increment; }
}