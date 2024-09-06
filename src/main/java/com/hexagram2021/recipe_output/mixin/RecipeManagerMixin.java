package com.hexagram2021.recipe_output.mixin;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
	@Redirect(method = "fromJson(Lnet/minecraft/resources/ResourceLocation;Lcom/google/gson/JsonObject;Lnet/minecraftforge/common/crafting/conditions/ICondition$IContext;)Lnet/minecraft/world/item/crafting/Recipe;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/RecipeSerializer;fromJson(Lnet/minecraft/resources/ResourceLocation;Lcom/google/gson/JsonObject;Lnet/minecraftforge/common/crafting/conditions/ICondition$IContext;)Lnet/minecraft/world/item/crafting/Recipe;"), remap = false)
	private static <T extends Container> Recipe<T> recipe_output$output(RecipeSerializer<Recipe<T>> instance, ResourceLocation resourceLocation, JsonObject jsonObject, ICondition.IContext iContext) {
		Recipe<T> r;
		try {
			r = instance.fromJson(resourceLocation, jsonObject, iContext);
		} catch (IllegalStateException e) {
			throw new RuntimeException("Error when parsing " + resourceLocation, e);
		}
		return r;
	}
}
