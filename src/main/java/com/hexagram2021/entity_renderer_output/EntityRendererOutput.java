package com.hexagram2021.entity_renderer_output;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(EntityRendererOutput.MODID)
public class EntityRendererOutput {
	public static final String MODID = "entity_renderer_output";

	public EntityRendererOutput() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
