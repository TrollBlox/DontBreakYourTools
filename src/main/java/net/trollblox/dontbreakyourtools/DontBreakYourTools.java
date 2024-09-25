package net.trollblox.dontbreakyourtools;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DontBreakYourTools implements ModInitializer {
	public static final String MOD_ID = "dont-break-your-tools";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Preparing to save your tools...");
	}

	public static boolean preventAttack(ItemStack stack) {
		int amount = 0;
		if (stack.getItem() instanceof ToolItem && !(stack.getItem() instanceof SwordItem)) amount++;
		return preventUsage(stack, amount);
	}

	public static boolean preventUsage(ItemStack stack) {
		return (stack.isDamageable() && isDurablilityOne(stack));
	}

	public static boolean preventUsage(ItemStack stack, int damagePerUse) {
		return (stack.isDamageable() && isDurabilityOne(stack, damagePerUse));
	}

	public static boolean isDurablilityOne(ItemStack stack) {
		return stack.isDamageable() && (stack.getDamage() >= stack.getMaxDamage() - 1);
	}

	public static boolean isDurabilityOne(ItemStack stack, int damagePerUse) {
		return stack.isDamageable() && (stack.getDamage() >= stack.getMaxDamage() - (damagePerUse + 1));
	}
}