package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.trollblox.dontbreakyourtools.DontBreakYourTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossbowSaver {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void preventBreakage(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        ItemStack heldItem = user.getStackInHand(hand);
        if (DontBreakYourTools.preventUsage(heldItem)) {
            info.setReturnValue(ActionResult.FAIL);
            info.cancel();
        }
    }
}
