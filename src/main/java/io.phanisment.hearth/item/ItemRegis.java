package io.phanisment.hearth.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

import io.phanisment.hearth.Main;

public class ItemRegis {
	public static final Item DARK_HEALTH = i("dark_health", new Item(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.UNCOMMON)));
	public static final Item HEALTH = i("health", new HealthItem());
	public static final Item TOTEM_FRAGMENT = i("totem_fragment", new Item(new Item.Settings().maxCount(4).rarity(Rarity.UNCOMMON)));
	public static final ItemGroup HEALTH_GROUP = Registry.register(Registries.ITEM_GROUP,
		Identifier.of(Main.id, "health_group"), 
		FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ph_hearth")).icon(() -> new ItemStack(DARK_HEALTH)).entries((displayContext, item) -> {
			item.add(DARK_HEALTH);
			item.add(HEALTH);
			item.add(TOTEM_FRAGMENT);
		}).build()
	);
	private static Item i(String name, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(Main.id, name), item);
	}
	
	/*
	private static ItemStack lore(Item item, Text... lore) {
		ItemStack stack = new ItemStack(item);
		NbtCompound display = stack.getOrCreateSubNbt("display");
		NbtList loreList = new NbtList();
		for (Text line : lines) {
			loreList.add(NbtString.of(line.toJson(text)));
		}

		display.put("Lore", loreList);
		return stack;
	}*/
}