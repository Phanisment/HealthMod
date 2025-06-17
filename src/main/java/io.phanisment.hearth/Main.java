package io.phanisment.hearth;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.phanisment.hearth.item.ItemRegis;

public class Main implements ModInitializer {
	public static final String id = "ph_hearth";
	public static final Logger logger = LoggerFactory.getLogger(id);
	public static final SimpleParticleType ADD_HEALTH_PARTICLE = FabricParticleTypes.simple();
	
	@Override
	public void onInitialize() {
		
		new ItemRegis();
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(id, "add_health"), ADD_HEALTH_PARTICLE);
	}
}