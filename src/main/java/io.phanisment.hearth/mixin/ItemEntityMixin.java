package io.phanisment.hearth.mixin;

import net.minecraft.util.math.random.Random;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.phanisment.hearth.item.ItemRegis;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		ItemEntity item = (ItemEntity)(Object)this;
		if (item.getStack().isOf(ItemRegis.DARK_HEALTH) || item.getStack().isOf(ItemRegis.HEALTH)) {
			cir.setReturnValue(false);
		}/*
		if (item.getStack().isOf(Items.TOTEM_OF_UNDYING) && source.isOf(DamageTypes.FALLING_ANVIL)) {
			Random random = item.getWorld().random;
			item.dropStack(new ItemStack(ItemRegis.TOTEM_FRAGMENT, random.nextBetween(2, 4)));
		}*/
	}
}