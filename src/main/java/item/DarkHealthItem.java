package io.phanisment.bhc.item;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class DarkHealthItem extends Item {
	public DarkHealthItem() {
		super(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.UNCOMMON));
	}
}