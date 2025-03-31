package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.trollblox.dontbreakyourtools.DontBreakYourTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinPlayerInteractionManager {

    @Inject(method = "attackBlock", at = @At("HEAD"), cancellable = true)
    private void attackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (DontBreakYourTools.preventUsage(MinecraftClient.getInstance().player.getMainHandStack())) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
    private void attackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
        if (DontBreakYourTools.preventAttack(player.getMainHandStack())) {
            ci.cancel();
        }
    }

    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    private void interactBlock(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (DontBreakYourTools.preventUsage(player.getStackInHand(hand))) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
    private void interactItem(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (DontBreakYourTools.preventUsage(player.getStackInHand(hand))) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }
}