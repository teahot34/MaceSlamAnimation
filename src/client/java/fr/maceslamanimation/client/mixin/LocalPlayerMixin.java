package fr.maceslamanimation.client.mixin;

import fr.maceslamanimation.client.MaceSlamAnimationClient; // <-- N'oublie pas d'importer ta classe principale !
import fr.maceslamanimation.client.VideoRenderHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class LocalPlayerMixin {

    @Inject(method = "attack", at = @At("HEAD"))
    private void onMaceSmashClient(Entity target, CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (player.level().isClientSide() && player instanceof net.minecraft.client.player.LocalPlayer localPlayer) {

            // 1. ON VÉRIFIE LA CONFIGURATION MOD MENU ICI
            // Remplace 'MaceSlamAnimationClient.isAnimationEnabled' par la vraie variable de ta config
            if (!MaceSlamAnimationClient.isAnimationEnabled) {
                return; // Si le joueur a mis sur "OFF" dans les options, on arrête le code tout de suite !
            }

            // 2. On vérifie que le joueur est en train de tomber (vitesse vers le bas)
            boolean isFalling = localPlayer.getDeltaMovement().y < 0.0;

            // 3. Si c'est une masse, qu'il n'est pas au sol, et qu'il tombe = SMASH !
            if (localPlayer.getMainHandItem().is(Items.MACE) && !localPlayer.onGround() && isFalling) {
                System.out.println("============= SMASH DETECTE ! =============");
                VideoRenderHandler.startVideo(); // Lance la vidéo
            }
        }
    }
}