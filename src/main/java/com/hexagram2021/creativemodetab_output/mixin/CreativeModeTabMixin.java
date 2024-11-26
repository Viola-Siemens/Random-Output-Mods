package com.hexagram2021.creativemodetab_output.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(value = ForgeHooks.class, remap = false)
public class CreativeModeTabMixin {
	@Redirect(method = "onCreativeModeTabBuildContents", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CreativeModeTab$Output;accept(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V", remap = true), remap = false)
	private static void creativemodetab_output$outputIfFailed(CreativeModeTab.Output instance, ItemStack itemStack, CreativeModeTab.TabVisibility tabVisibility) {
		try {
			instance.accept(itemStack, tabVisibility);
		} catch (IllegalArgumentException e) {
			ResourceLocation tab = new ResourceLocation("unknown");
			if(instance instanceof BuildCreativeModeTabContentsEvent event) {
				tab = Objects.requireNonNullElse(BuiltInRegistries.CREATIVE_MODE_TAB.getKey(event.getTab()), tab);
			} else if(instance instanceof ItemDisplayBuilderAccess builder) {
				tab = Objects.requireNonNullElse(BuiltInRegistries.CREATIVE_MODE_TAB.getKey(builder.creativemodetab_output$getTab()), tab);
			}
			throw new RuntimeException("Error when adding itemStack %s to %s".formatted(itemStack, tab), e);
		}
	}
}
