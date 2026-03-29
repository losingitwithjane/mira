package cc.simplicated.mira.renderer;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;

import com.mojang.blaze3d.shaders.UniformType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.resources.Identifier;

public class MiraRenderPipelines {
    public static final RenderPipeline.Snippet GUI_SNIPPET = RenderPipeline.builder()
            .withVertexShader(Identifier.fromNamespaceAndPath("mira", "gui"))
            .withUniform("Projection", UniformType.UNIFORM_BUFFER)
            .withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .withDepthWrite(false)
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .withCull(false)
            .withBlend(BlendFunction.TRANSLUCENT)
            .buildSnippet();

    public static final RenderPipeline PANEL_BG = RenderPipeline.builder(GUI_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("mira", "pipeline/panel_bg"))
            .withFragmentShader(Identifier.fromNamespaceAndPath("mira", "panelbackground"))
            .build();

    public static final RenderPipeline INNER_PANEL = RenderPipeline.builder(GUI_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("mira", "pipeline/inner_panel"))
            .withFragmentShader(Identifier.fromNamespaceAndPath("mira", "innerpanel"))
            .build();
}