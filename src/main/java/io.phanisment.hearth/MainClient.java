package io.phanisment.hearth;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

import io.phanisment.hearth.particle.HealthParticle;

public class MainClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(Main.ADD_HEALTH_PARTICLE, HealthParticle.Factory::new);
	}
}