package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.trollblox.dontbreakyourtools.DontBreakYourTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoeItem.class)
public class HoeSaver {
    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    private void preventBreakage(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
        if (DontBreakYourTools.preventUsage(context.getPlayer().getStackInHand(context.getHand()))) {
            info.setReturnValue(ActionResult.FAIL);
            info.cancel();
        }
    }
}
