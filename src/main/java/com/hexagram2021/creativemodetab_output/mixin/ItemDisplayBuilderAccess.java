package com.hexagram2021.creativemodetab_output.mixin;

import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.world.item.CreativeModeTab$ItemDisplayBuilder")
public interface ItemDisplayBuilderAccess {
	@Accessor(value = "tab")
	CreativeModeTab creativemodetab_output$getTab();
}
