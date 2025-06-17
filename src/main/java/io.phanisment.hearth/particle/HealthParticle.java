package io.phanisment.hearth.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class HealthParticle extends SpriteBillboardParticle {
	public HealthParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
		this.scale = 0.1f;
		this.maxAge = 20;
	}
	
	@Override
	public int getBrightness(float tint) {
		return 240;
	}
	
	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	public static class Factory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider sprite;
		
		public Factory(SpriteProvider sprite) {
			this.sprite = sprite;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			HealthParticle p = new HealthParticle(world, x, y, z, velocityX, velocityY, velocityZ);
			p.setAlpha(1.0f);
			p.setSpriteForAge(sprite);
			return p;
		}
	}
}
