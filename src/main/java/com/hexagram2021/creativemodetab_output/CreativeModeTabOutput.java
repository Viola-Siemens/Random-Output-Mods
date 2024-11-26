package com.hexagram2021.creativemodetab_output;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(CreativeModeTabOutput.MODID)
public class CreativeModeTabOutput {
	public static final String MODID = "creativemodetab_output";

	public CreativeModeTabOutput() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
