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
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class HealthParticle extends SpriteBillboardParticle {
	private final SpriteProvider sprite;
	
	public HealthParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider sprite) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
		this.sprite = sprite;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
		this.scale = 0.5f;
		this.maxAge = world.random.nextBetween(4, 8);
		this.setSpriteForAge(sprite);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.setSpriteForAge(sprite);
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
			HealthParticle p = new HealthParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite);
			p.setAlpha(1.0f);
			return p;
		}
	}
}
