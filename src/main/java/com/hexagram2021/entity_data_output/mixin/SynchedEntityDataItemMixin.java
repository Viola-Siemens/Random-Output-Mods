package com.hexagram2021.entity_data_output.mixin;

import net.minecraft.network.syncher.SynchedEntityData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SynchedEntityData.DataItem.class)
public class SynchedEntityDataItemMixin<T> {
	@Shadow
	T value;

	@SuppressWarnings("ConstantValue")
	@Inject(method = {"<init>", "setValue"}, at = @At(value = "TAIL"))
	private void entity_data_output$checkValue(CallbackInfo ci) {
		if(this.value == null) {
			throw new IllegalArgumentException("Entity data value is set to null!");
		}
	}
}
