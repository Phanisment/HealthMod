package io.phanisment.hearth;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.phanisment.hearth.item.ItemRegis;

public class Main implements ModInitializer {
	public static final String id = "ph_hearth";
	public static final Logger logger = LoggerFactory.getLogger(id);
	
	@Override
	public void onInitialize() {
		new ItemRegis();
	}
}