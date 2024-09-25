package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.trollblox.dontbreakyourtools.DontBreakYourTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public class FishingRodSaver {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void preventBreakage(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
        ItemStack heldItem = user.getStackInHand(hand);
        int amount = 0;
        if (user.fishHook != null) {
            amount = user.fishHook.use(heldItem);
        }
        if (DontBreakYourTools.preventUsage(heldItem, amount)) {
            info.setReturnValue(TypedActionResult.pass(heldItem));
            info.cancel();
        }
    }
}
