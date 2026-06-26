package fr.maceslamanimation.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import fr.maceslamanimation.MaceSlamPayload;

public class MaceSlamAnimationClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Dès qu'on reçoit le paquet du serveur, on lance l'animation et le son
		ClientPlayNetworking.registerGlobalReceiver(MaceSlamPayload.TYPE, (payload, context) -> {
			context.client().execute(VideoRenderHandler::startVideo);
		});

		// On affiche les images directement sur l'écran du joueur
		HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) -> {
			VideoRenderHandler.render(guiGraphics);
		});
	}
}