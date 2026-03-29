package cc.simplicated.mira.renderer;

import cc.simplicated.mira.Utilities.math.Vec2i;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.buffers.Std140SizeCalculator;
import net.minecraft.client.renderer.DynamicUniformStorage;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import java.nio.ByteBuffer;

public class SomethingUniforms {
    public static final int SIZE = new Std140SizeCalculator()
            .putVec2()
            .get();

    private static final Data DATA = new Data();

    private static final DynamicUniformStorage<Data> STORAGE = new DynamicUniformStorage<>("Mira - ui UBO", SIZE, 16);

    public static void flipFrame() {
        STORAGE.endFrame();
    }

    public static GpuBufferSlice write(Vec2i size) {
        DATA.size = size;
        return STORAGE.writeUniform(DATA);
    }

    private static final class Data implements DynamicUniformStorage.DynamicUniform {
        private Vec2i size;

        @Override
        public void write(ByteBuffer buffer) {
            Std140Builder.intoBuffer(buffer)
                    .putVec2(new Vector2f(size.x, size.y));
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }
    }
}