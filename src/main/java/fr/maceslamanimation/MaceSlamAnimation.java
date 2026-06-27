package fr.maceslamanimation;

import net.fabricmc.api.ModInitializer;

public class MaceSlamAnimation implements ModInitializer {
	public static final String MOD_ID = "maceslamanimation";

	@Override
	public void onInitialize() {
		// Le mod fonctionne désormais à 100% côté client pour être compatible avec tous les serveurs.
		// On n'a plus besoin d'enregistrer de paquets réseau ici !
	}
}