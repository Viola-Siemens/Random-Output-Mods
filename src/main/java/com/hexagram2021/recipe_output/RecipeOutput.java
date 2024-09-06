package com.hexagram2021.recipe_output;

import com.hexagram2021.recipe_output.utils.ROLogger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

@Mod(RecipeOutput.MODID)
public class RecipeOutput {
	public static final String MODID = "recipe_output";

	public RecipeOutput() {
		ROLogger.logger = LogManager.getLogger(MODID);
		MinecraftForge.EVENT_BUS.register(this);
	}
}
