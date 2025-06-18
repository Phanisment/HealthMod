package io.phanisment.hearth.common;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SoundRegistry {
	public static SoundEvent HEALTH_EMPTY;
	public static SoundEvent USE_HEALTH;
	
	public SoundRegistry() {
		HEALTH_EMPTY = s("ph_hearth:empty_health");
		USE_HEALTH = s("ph_hearth:use_health");
	}
	
	private static SoundEvent s(String id) {
		return Registry.register(Registries.SOUND_EVENT, Identifier.of(id), SoundEvent.of(Identifier.of(id)));
	}
}