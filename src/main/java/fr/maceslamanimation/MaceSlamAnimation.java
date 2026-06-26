package fr.maceslamanimation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class MaceSlamAnimation implements ModInitializer {
	public static final String MOD_ID = "maceslamanimation";

	@Override
	public void onInitialize() {
		// Enregistrement obligatoire du paquet réseau
		PayloadTypeRegistry.playC2S().register(MaceSlamPayload.TYPE, MaceSlamPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(MaceSlamPayload.TYPE, MaceSlamPayload.CODEC);
	}
}