package io.phanisment.hearth.mixin;

import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.phanisment.hearth.item.ItemRegis;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
	@Unique 
	private double newMaxHealth = 20.0D;

	@Inject(method = "onDeath", at = @At("TAIL"))
	private void onDeath(DamageSource source, CallbackInfo ci) {
		ServerPlayerEntity self = (ServerPlayerEntity)(Object)this;
		EntityAttributeInstance attr = self.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
		double current = attr.getBaseValue();
		if (current >= 2.0D) {
			this.newMaxHealth = current - 2.0D;
		}
		self.dropItem(new ItemStack(ItemRegis.DARK_HEALTH), false);
	}

	@Inject(method = "copyFrom", at = @At("TAIL"))
	private void copyFrom(ServerPlayerEntity old, boolean alive, CallbackInfo ci) {
		if ((Object) old instanceof ServerPlayerEntityMixin oldMixin) {
			ServerPlayerEntity self = (ServerPlayerEntity)(Object)this;
			EntityAttributeInstance attr = self.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
			if (oldMixin.newMaxHealth >= 2.0D) {
				this.newMaxHealth = oldMixin.newMaxHealth;
				attr.setBaseValue(this.newMaxHealth);
			} else {
				self.networkHandler.sendPacket(new TitleS2CPacket(Text.translatable("ph_bhc.deathprem")));
				self.interactionManager.changeGameMode(GameMode.SPECTATOR);
			}
		}
	}

	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
		if (this.newMaxHealth > 0) nbt.putDouble("ReducedMaxHealth", this.newMaxHealth);
	}

	@Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
	private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
		ServerPlayerEntity self = (ServerPlayerEntity)(Object)this;
		if (nbt.contains("ReducedMaxHealth")) {
			this.newMaxHealth = nbt.getDouble("ReducedMaxHealth");
			EntityAttributeInstance attr = self.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
			attr.setBaseValue(this.newMaxHealth);
		}
	}
}