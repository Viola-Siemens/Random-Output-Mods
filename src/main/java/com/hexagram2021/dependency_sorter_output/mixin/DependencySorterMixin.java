package com.hexagram2021.dependency_sorter_output.mixin;

import com.google.common.collect.Multimap;
import com.hexagram2021.dependency_sorter_output.utils.DSOLogger;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.DependencySorter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;
import java.util.stream.Collectors;

@Mixin(DependencySorter.class)
public class DependencySorterMixin {
	@WrapOperation(method = "lambda$isCyclic$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/DependencySorter;isCyclic(Lcom/google/common/collect/Multimap;Ljava/lang/Object;Ljava/lang/Object;)Z"))
	private static <K> boolean dependency_sorter_output$isCyclic(Multimap<K, K> dependencies, K source, K target, Operation<Boolean> original) {
		try {
			return original.call(dependencies, source, target);
		} catch (StackOverflowError e) {
			dependencies.keySet().forEach(key -> DSOLogger.error(
					"  - " + key + ": " + dependencies.get(key).stream()
							.filter(Objects::nonNull)
							.map(Object::toString)
							.collect(Collectors.joining(", "))
			));
			throw new IllegalStateException("Cyclic dependency detected: %s -> %s".formatted(source, target));
		}
	}
}
