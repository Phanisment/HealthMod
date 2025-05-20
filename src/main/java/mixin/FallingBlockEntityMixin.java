package io.phanisment.bhc.mixin;

import net.minecraft.util.math.random.Random;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.item.Items;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.phanisment.bhc.item.ItemRegis;

import java.util.List;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityMixin {
	@Inject(method = "tick", at = @At("TAIL"))
	public void onTick(CallbackInfo ci) {
		FallingBlockEntity falling = (FallingBlockEntity)(Object)this;
		if (falling.getBlockState().isOf(Blocks.ANVIL)) {
			if (falling.isOnGround()) {
				World world = falling.getWorld();
				Box box = falling.getBoundingBox().expand(0.1);
				List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, e -> e.isAlive());
				if (!items.isEmpty()) {
					Random random = world.random;
					for (ItemEntity item : items) {
						if (item.getStack().isOf(Items.TOTEM_OF_UNDYING)) {
							item.discard();
							ItemEntity fragment = new ItemEntity(world, item.getX(), item.getY(), item.getZ(), new ItemStack(ItemRegis.TOTEM_FRAGMENT, random.nextBetween(2, 4)));
							world.spawnEntity(fragment);
						}
					}
				}
			}
		}
	}
}