package cc.simplicated.mira.Features;

import org.reflections.Reflections;

import java.util.*;

public class FeatureManager {
    private static final Map<Class<? extends Feature>, Feature> features = new HashMap<>();

    public static void init() {
        Reflections reflections = new Reflections("cc.simplicated.mira.Features"); // How can I not hardcode this >:| @Crosby24
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(FeatureInfo.class); // Alternatively: Reflections#getSubTypesOf(Feature.class)
        for (Class<?> klass : classes) {
            try {
                if (Feature.class.isAssignableFrom(klass)) {
                    klass.getDeclaredConstructor().newInstance(); // newInstance aka constructor gets called which calls RegisterFeature
                    // Could also have the feature not add itself via registerFeatures by adding it to features using the last function to get the instance idk && idc
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerFeature(Feature feature) {
        features.put(feature.getClass(), feature);
        System.out.println("Registered feature " + feature.getName() + ".");
    }

    public static <T extends Feature> T getFeature(Class<T> klass) {
        return klass.cast(features.get(klass));
    }

    public static List<Feature> getAllFeatures() {
        return new ArrayList<>(features.values());
    }
}
