package fr.maceslamanimation.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.SimpleSoundInstance; // Le bon import nettoyé
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import fr.maceslamanimation.MaceSlamAnimation;

public class VideoRenderHandler {

    private static boolean isPlaying = false;
    private static int currentFrame = 0;
    private static long lastFrameTime = 0;

    private static final int TOTAL_FRAMES = 147;
    private static final long MS_PER_FRAME = 33;

    public static void startVideo() {
        isPlaying = true;
        currentFrame = 0;
        lastFrameTime = System.currentTimeMillis();

        try {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                ResourceLocation soundLocation = ResourceLocation.fromNamespaceAndPath("maceslamanimation", "mace_impact_sound");
                SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(soundLocation);

                // Utilisation de .forUI(sound, pitch) qui est la méthode officielle stable
                mc.getSoundManager().play(SimpleSoundInstance.forUI(soundEvent, 1.0F));
            }
        } catch (Exception e) {
            System.out.println("[MaceSlam] Impossible de lire le fichier audio : " + e.getMessage());
        }
    }

    public static void render(GuiGraphics context) {
        if (!isPlaying) return;

        long now = System.currentTimeMillis();

        // Gestion du défilement des images toutes les 33ms (~30 FPS)
        if (now - lastFrameTime >= MS_PER_FRAME) {
            currentFrame++;
            lastFrameTime = now;

            if (currentFrame >= TOTAL_FRAMES) {
                isPlaying = false;
                return;
            }
        }

        // AFFICHAGE DES IMAGES
        try {
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                    MaceSlamAnimation.MOD_ID,
                    "textures/video/frame_" + currentFrame + ".png"
            );

            int width = context.guiWidth();
            int height = context.guiHeight();

            // Dessine l'image sur tout l'écran
            context.blit(RenderType::guiTextured, texture, 0, 0, 0, 0, width, height, width, height);
        } catch (Exception e) {
            isPlaying = false;
        }
    }
}