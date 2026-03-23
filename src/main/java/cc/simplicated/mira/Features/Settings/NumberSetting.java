package cc.simplicated.mira.Features.Settings;

import cc.simplicated.mira.Features.Feature;

public class NumberSetting extends Setting<Double> {
    private final double min, max, increment;

    public NumberSetting(String name, Feature parent, double def, double min, double max, double inc) {
        super(name, parent, def);
        this.min = min;
        this.max = max;
        this.increment = inc;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getIncrement() { return increment; }
}