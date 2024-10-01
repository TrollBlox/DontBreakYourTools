package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.trollblox.dontbreakyourtools.DontBreakYourTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntity.class)
public class ShearsEntitySaver {
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    private void preventEntityUsage(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack item = player.getStackInHand(hand);
        if (DontBreakYourTools.isDurablilityOne(item)) {
            cir.setReturnValue(ActionResult.FAIL);
            cir.cancel();
        }
    }
}
