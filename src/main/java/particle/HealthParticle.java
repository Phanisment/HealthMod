package io.phanisment.bhc.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class HealthParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;
	private int lifeCounter = 0;

	public HealthParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
		this.spriteProvider = spriteProvider;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
		this.scale = 0.1f;
		this.maxAge = 40 + world.random.nextInt(20);
		this.setSpriteForAge(spriteProvider);
	}

	@Override
	public void tick() {
		super.tick();
		lifeCounter++;
		this.setSpriteForAge(spriteProvider);
		if (lifeCounter < 5) {
			velocityY += 0.025;
		} else {
			velocityY *= 0.1;
		}
		if (age > maxAge - 10) {
			setAlpha((maxAge - age) / 10.0f);
		}
	}
	
	@Override
	public int getBrightness(float tint) {
		return 240;
	}
	
	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	public static class Factory implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider spriteProvider;
		
		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			HealthParticle particle = new HealthParticle(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider);
			particle.setAlpha(1.0f);
			return particle;
		}
	}
}