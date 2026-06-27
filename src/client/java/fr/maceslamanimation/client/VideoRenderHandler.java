package fr.maceslamanimation.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import fr.maceslamanimation.MaceSlamAnimation;

public class VideoRenderHandler {

    private static boolean isPlaying = false;
    private static int currentFrame = 1;
    private static long lastFrameTime = 0;

    private static final int TOTAL_FRAMES = 147;
    private static final long MS_PER_FRAME = 33;

    public static void startVideo() {
        isPlaying = true;
        currentFrame = 1;
        lastFrameTime = System.currentTimeMillis();

        try {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                ResourceLocation soundLocation = ResourceLocation.fromNamespaceAndPath("maceslamanimation", "mace_impact_sound");
                SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(soundLocation);

                // Code audio 1.21 officiel (sans erreur rouge)
                mc.getSoundManager().play(SimpleSoundInstance.forUI(soundEvent, 1.0F));
            }
        } catch (Exception e) {
            System.out.println("[MaceSlam] Impossible de lire le fichier audio : " + e.getMessage());
        }
    }

    public static void render(GuiGraphics context) {
        if (!isPlaying) return;

        long now = System.currentTimeMillis();

        if (now - lastFrameTime >= MS_PER_FRAME) {
            currentFrame++;
            lastFrameTime = now;

            if (currentFrame > TOTAL_FRAMES) {
                isPlaying = false;
                return;
            }
        }

        try {
            // Génère automatiquement : ezgif-frame-001.png, ezgif-frame-012.png, etc.
            String frameName = String.format("textures/video/ezgif-frame-%03d.png", currentFrame);

            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                    MaceSlamAnimation.MOD_ID,
                    frameName
            );

            int width = context.guiWidth();
            int height = context.guiHeight();

            context.blit(RenderType::guiTextured, texture, 0, 0, 0, 0, width, height, width, height);
        } catch (Exception e) {
            isPlaying = false;
        }
    }
}