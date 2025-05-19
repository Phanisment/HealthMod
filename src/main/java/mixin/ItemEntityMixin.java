package io.phanisment.bhc.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.phanisment.bhc.item.ItemRegis;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		ItemEntity item = (ItemEntity)(Object)this;
		if (item.getStack().isOf(ItemRegis.DARK_HEALTH) || item.getStack().isOf(ItemRegis.HEALTH)) {
			cir.setReturnValue(false);
		}
	}
}