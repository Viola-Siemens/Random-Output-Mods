package com.hexagram2021.entity_renderer_output.mixin;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRendererManager.class)
public class EntityRendererManagerMixin {
	@SuppressWarnings("ConstantValue")
	@Redirect(method = "shouldRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRendererManager;getRenderer(Lnet/minecraft/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;"))
	private <E extends Entity> EntityRenderer<? super E> entity_renderer_output$output(EntityRendererManager instance, E entity) {
		EntityRenderer<? super E> ret = instance.getRenderer(entity);
		if(ret == null) {
			throw new NullPointerException("Null entity renderer from entity type: " + entity.getType().getRegistryName());
		}
		return ret;
	}
}
