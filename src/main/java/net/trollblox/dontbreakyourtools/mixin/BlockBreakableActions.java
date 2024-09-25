package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.trollblox.dontbreakyourtools.DontBreakYourTools;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class BlockBreakableActions {
	@Final
	@Shadow
    PlayerInventory inventory;
	@Inject(at = @At("HEAD"), method = "isBlockBreakingRestricted", cancellable = true)
	private void blockBreakableMining(CallbackInfoReturnable<Boolean> info) {
		ItemStack item = inventory.getStack(inventory.selectedSlot);
		if (DontBreakYourTools.preventUsage(item)) info.setReturnValue(true);
	}

	@Inject(at = @At("HEAD"), method = "canUseSlot", cancellable = true)
	private void blockBreakableUsing(CallbackInfoReturnable<Boolean> info) {
		ItemStack item = inventory.getStack(inventory.selectedSlot);
		if (DontBreakYourTools.preventUsage(item)) info.setReturnValue(false);
	}

	@Inject(at = @At("HEAD"), method = "attack", cancellable = true)
	private void blockBreakableAttacking(CallbackInfo info) {
		ItemStack item = inventory.getStack(inventory.selectedSlot);
		if (DontBreakYourTools.preventAttack(item)) info.cancel();
	}

}