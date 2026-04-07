package com.hexagram2021.dependency_sorter_output.mixin;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.util.DependencySorter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

@Mixin(DependencySorter.class)
public class DependencySorterMixin<K, V extends DependencySorter.Entry<K>> {
	@WrapOperation(method = "lambda$isCyclic$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/DependencySorter;isCyclic(Lcom/google/common/collect/Multimap;Ljava/lang/Object;Ljava/lang/Object;)Z"))
	private static <K> boolean dependency_sorter_output$isCyclic(Multimap<K, K> dependencies, K source, K target, Operation<Boolean> original, @Share(value = "dependency_sorter_output$visiting") LocalRef<Set<K>> visiting) {
		if(visiting.get() == null) {
			visiting.set(Sets.newHashSet(source));
		}
		if(!visiting.get().add(target)) {
			throw new IllegalStateException("Cyclic dependency detected: " + visiting.get());
		}
		boolean ret = original.call(dependencies, source, target);
		visiting.get().remove(target);
		return ret;
	}

	@WrapOperation(method = "addDependencyIfNotCyclic", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/DependencySorter;isCyclic(Lcom/google/common/collect/Multimap;Ljava/lang/Object;Ljava/lang/Object;)Z"))
	private static <K> boolean dependency_sorter_output$addDependencyIfNotCyclic(Multimap<K, K> dependencies, K source, K target, Operation<Boolean> original, @Share(value = "dependency_sorter_output$visiting") LocalRef<Set<K>> visiting) {
		visiting.set(Sets.newHashSet(source));
		if(!visiting.get().add(target)) {
			// Self-Dependent
			throw new IllegalStateException("Cyclic dependency detected: " + visiting.get());
		}
		return original.call(dependencies, source, target);
	}
}
