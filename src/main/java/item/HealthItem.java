package io.phanisment.bhc.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.util.Rarity;
import net.minecraft.text.Text;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;

public class HealthItem extends Item {
	public HealthItem() {
		super(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.UNCOMMON));
	}
	
	@Override
	public boolean hasGlint(ItemStack item) {
		return true;
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack item = user.getStackInHand(hand);
		if (!world.isClient && user instanceof ServerPlayerEntity) {
			EntityAttributeInstance attr = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
			if (attr != null) {
				double current = attr.getBaseValue();
				if (current < 20.0) {
					item.decrement(1);
					attr.setBaseValue(current + 2.0);
					user.sendMessage(Text.translatable("ph_bhc.add_health"));
					world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0f, 1.2f);
				} else {
					user.sendMessage(Text.translatable("ph_bhc.max_health"));
				}
			}
		}
		return TypedActionResult.success(item, world.isClient());
	}
}