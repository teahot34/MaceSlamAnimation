package fr.maceslamanimation.mixin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import fr.maceslamanimation.MaceSlamPayload;

@Mixin(Player.class)
public class MaceSlamMixin {

    @Inject(method = "attack", at = @At("HEAD"))
    private void onAttack(Entity target, CallbackInfo ci) {
        // CE MESSAGE VA S'AFFICHER DANS LA CONSOLE D'INTELLIJ
        System.out.println("[MaceSlamMod] ATTACQUE DETECTEE !");

        Player player = (Player) (Object) this;

        if (player.getMainHandItem().is(Items.MACE)) {
            System.out.println("[MaceSlamMod] L'item est bien une Masse !");

            if (player instanceof ServerPlayer serverPlayer) {
                System.out.println("[MaceSlamMod] Envoi du paquet au client...");
                ServerPlayNetworking.send(serverPlayer, new MaceSlamPayload());
            }
        }
    }
}