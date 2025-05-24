package io.phanisment.bhc.mixin;

import net.minecraft.block.CactusBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.ItemEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.phanisment.bhc.item.ItemRegis;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {
	@Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		if (entity instanceof ItemEntity item) {
			if (item.getStack().isOf(ItemRegis.DARK_HEALTH) || item.getStack().isOf(ItemRegis.HEALTH)) ci.cancel();
		}
	}
}