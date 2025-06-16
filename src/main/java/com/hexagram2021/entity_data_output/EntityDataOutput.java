package com.hexagram2021.entity_data_output;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(EntityDataOutput.MODID)
public class EntityDataOutput {
	public static final String MODID = "entity_data_output";

	public EntityDataOutput() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
