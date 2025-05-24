package io.phanisment.bhc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.phanisment.bhc.item.ItemRegis;

public class Main implements ModInitializer {
	public static final String id = "ph_bhc";
	public static final Logger logger = LoggerFactory.getLogger(id);
	public static final DefaultParticleType ADD_HEALTH_PARTICLE = FabricParticleTypes.simple();
	
	@Override
	public void onInitialize() {
		new ItemRegis();
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(id, "add_health"), ADD_HEALTH_PARTICLE);
	}
}