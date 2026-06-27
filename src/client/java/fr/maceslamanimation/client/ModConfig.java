package fr.maceslamanimation.client;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

// Si MaceSlamAnimationClient est rouge plus bas, on va l'importer avec Alt+Entrée

public class ModConfig {
    public static Screen createConfigScreen(Screen parent) {
        // 1. On crée le constructeur du menu (c'est ce qui te manquait)
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("Paramètres Mace Slam"));

        // 2. On crée la catégorie "Général"
        ConfigCategory general = builder.getOrCreateCategory(Component.literal("Général"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // 3. On ajoute ton bouton On/Off
        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.literal("Activer l'animation"),
                        MaceSlamAnimationClient.isAnimationEnabled
                )
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> MaceSlamAnimationClient.isAnimationEnabled = newValue)
                .build());

        // 4. On construit et on renvoie l'écran
        return builder.build();
    }
}