package cc.simplicated.mira.features;


import cc.simplicated.mira.features.Settings.BooleanSetting;

@FeatureInfo(name = "Test Feature", category = "Test category")
public class TestFeature extends Feature {
    BooleanSetting aggressiveMode = new BooleanSetting(
            "aggressive mode",
            "bs",
            this,
            false
    );

    public TestFeature() {
        addSetting(aggressiveMode);
    }
}
