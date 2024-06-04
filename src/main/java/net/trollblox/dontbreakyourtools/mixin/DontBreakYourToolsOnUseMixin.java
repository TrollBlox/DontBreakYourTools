package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
// broken, so excluded
@Mixin(Item.class)
public abstract class DontBreakYourToolsOnUseMixin {
    @Shadow
    public abstract int getDamage();
    @Shadow
    public abstract int getMaxDamage();
    @Shadow
    public abstract boolean isDamageable();

    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void overrideUse(CallbackInfoReturnable<ActionResult> info) {
        if (getDamage() >= getMaxDamage() - 1 && isDamageable()) info.setReturnValue(ActionResult.FAIL);

    }
}
