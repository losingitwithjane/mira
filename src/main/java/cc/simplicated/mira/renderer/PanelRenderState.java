package cc.simplicated.mira.renderer;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.gui.render.state.GuiElementRenderState;
import org.joml.Matrix3x2fc;
import org.jspecify.annotations.Nullable;

public record PanelRenderState(RenderPipeline pipeline,
                               TextureSetup textureSetup,
                               Matrix3x2fc pose,
                               int x0,
                               int y0,
                               int x1,
                               int y1,
                               int col1,
                               int col2,
                               @Nullable ScreenRectangle scissorArea,
                               @Nullable ScreenRectangle bounds) implements GuiElementRenderState {
    public PanelRenderState(
            RenderPipeline renderPipeline,
            TextureSetup textureSetup,
            Matrix3x2fc matrix3x2fc,
            int i,
            int j,
            int k,
            int l,
            int m,
            int n,
            @Nullable ScreenRectangle screenRectangle
    ) {
        this(renderPipeline, textureSetup, matrix3x2fc, i, j, k, l, m, n, screenRectangle, getBounds(i, j, k, l, matrix3x2fc, screenRectangle));
    }

    @Override
    public void buildVertices(VertexConsumer vertexConsumer) {
        float w = this.x1() - this.x0();
        float h = this.y1() - this.y0();
        vertexConsumer.addVertexWith2DPose(this.pose(), this.x0(), this.y0()).setUv(0f, 0f).setColor(this.col1());
        vertexConsumer.addVertexWith2DPose(this.pose(), this.x0(), this.y1()).setUv(0f, h).setColor(this.col2());
        vertexConsumer.addVertexWith2DPose(this.pose(), this.x1(), this.y1()).setUv(w, h).setColor(this.col2());
        vertexConsumer.addVertexWith2DPose(this.pose(), this.x1(), this.y0()).setUv(w, 0f).setColor(this.col1());
    }

    @Nullable
    private static ScreenRectangle getBounds(int i, int j, int k, int l, Matrix3x2fc matrix3x2fc, @Nullable ScreenRectangle screenRectangle) {
        ScreenRectangle screenRectangle2 = new ScreenRectangle(i, j, k - i, l - j).transformMaxBounds(matrix3x2fc);
        return screenRectangle != null ? screenRectangle.intersection(screenRectangle2) : screenRectangle2;
    }
}
