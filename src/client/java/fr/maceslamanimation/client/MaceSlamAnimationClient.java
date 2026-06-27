package fr.maceslamanimation.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;



public class MaceSlamAnimationClient implements ClientModInitializer {

	public static boolean isAnimationEnabled = true;

	@Override
	public void onInitializeClient() {
		// On enregistre simplement le rendu de l'animation
		HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) -> {
			VideoRenderHandler.render(guiGraphics);
		});
	}
}