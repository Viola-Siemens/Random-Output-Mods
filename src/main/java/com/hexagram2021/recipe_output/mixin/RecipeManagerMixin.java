package com.hexagram2021.recipe_output.mixin;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.neoforge.common.conditions.WithConditions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
	@Unique
	@Nullable
	ResourceLocation recipe_output$currentRecipeId = null;

	@WrapOperation(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At(value = "INVOKE", target = "Ljava/util/Map$Entry;getKey()Ljava/lang/Object;"))
	private <K, V> K recipe_output$getLocation(Map.Entry<K, V> instance, Operation<K> original) {
		K ret = original.call(instance);
		this.recipe_output$currentRecipeId = (ResourceLocation)ret;
		return ret;
	}

	@WrapOperation(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Codec;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"))
	private DataResult<JsonElement> recipe_output$output(Codec<WithConditions<Recipe<?>>> instance, DynamicOps<JsonElement> dynamicOps, Object o, Operation<DataResult<JsonElement>> original) {
		DataResult<JsonElement> r;
		try {
			r = original.call(instance, dynamicOps, o);
		} catch (RuntimeException e) {
			throw new RuntimeException("Error when parsing " + this.recipe_output$currentRecipeId, e);
		}
		return r;
	}
}
