package fr.maceslamanimation.client;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // Cela dit à Mod Menu : "Quand on clique sur Config, lance la méthode createConfigScreen dans ModConfig"
        return ModConfig::createConfigScreen;
    }
}