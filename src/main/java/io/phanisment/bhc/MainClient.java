package io.phanisment.bhc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

import io.phanisment.bhc.particle.HealthParticle;

public class MainClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(Main.ADD_HEALTH_PARTICLE, HealthParticle.Factory::new);
	}
}