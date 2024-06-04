package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class DontBreakYourToolsMixin {
	@Shadow
	private PlayerInventory inventory;
	@Inject(at = @At("HEAD"), method = "isBlockBreakingRestricted", cancellable = true)
	private void overrideCanMine(CallbackInfoReturnable<Boolean> info) {
		ItemStack item = inventory.getStack(inventory.selectedSlot);
		if (item.getDamage() >= item.getMaxDamage() - 1 && item.isDamageable()) info.setReturnValue(true);
	}

	@Inject(at = @At("HEAD"), method = "canUseSlot", cancellable = true)
	private void overrideCanUseSlot(CallbackInfoReturnable<Boolean> info) {
		ItemStack item = inventory.getStack(inventory.selectedSlot);
		if (item.getDamage() >= item.getMaxDamage() - 1 && item.isDamageable()) info.setReturnValue(false);
	}

	@Inject(at = @At("HEAD"), method = "attack", cancellable = true)
	private void overrideAttack(CallbackInfo info) {
		ItemStack item = inventory.getStack(inventory.selectedSlot);
		if (item.getDamage() >= item.getMaxDamage() - 1 && item.isDamageable()) info.cancel();
	}

}