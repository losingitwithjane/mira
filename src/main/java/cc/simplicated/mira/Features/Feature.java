package cc.simplicated.mira.Features;

import cc.simplicated.mira.Features.Settings.Setting;
import cc.simplicated.mira.Input.Keybind;

import java.util.ArrayList;
import java.util.List;

public abstract class Feature {
    protected Keybind keybind;
    protected boolean enabled = false; // Should this be a `Setting`?

    protected String featureName;
    protected String categoryName;

    private final List<Setting<?>> settings = new ArrayList<>();

    public Feature() {
        FeatureInfo fi = getClass().getAnnotation(FeatureInfo.class);
        if (fi == null) return;

        featureName = fi.name();
        categoryName = fi.category();
        keybind = new Keybind(this);
        FeatureManager.registerFeature(this);
    }

    public void toggle() { enabled = !enabled; }
    public void enable() { enabled = true; }
    public void disable() { enabled = false; }
    public boolean isEnabled() { return enabled; }

    public String getName() { return featureName; }
    public String getCategory() { return categoryName; }

    public Keybind getKeybind() { return keybind; }

    public void addSetting(Setting<?> setting) { settings.add(setting); }
    public List<Setting<?>> getSettings() { return settings; }

    public void onClick() {
        // You can @Override this idk I didn't think it'd be worth making this abstract
        // as you won't be needing this often if really ever at all

        // Someone delete this atp
        // idk,,,, https://open.spotify.com/track/2o4kFMrOGhXwdpKd2ZUaKs?si=30fdb201497546fe
    }
}
