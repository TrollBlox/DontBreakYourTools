package net.trollblox.dontbreakyourtools;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
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
		if (stack.getItem().components().has(DataComponents.TOOL)) amount++;
		return preventUsage(stack, amount);
	}

	public static boolean preventUsage(ItemStack stack) {
		return (stack.isDamageableItem() && isDurabilityOne(stack));
	}

	public static boolean preventUsage(ItemStack stack, int damagePerUse) {
		return (stack.isDamageableItem() && isDurabilityOne(stack, damagePerUse));
	}

	public static boolean isDurabilityOne(ItemStack stack) {
		return stack.isDamageableItem() && (stack.getDamageValue() >= stack.getMaxDamage() - 1);
	}

	public static boolean isDurabilityOne(ItemStack stack, int damagePerUse) {
		return stack.isDamageableItem() && (stack.getDamageValue() >= stack.getMaxDamage() - (damagePerUse + 1));
	}
}