package net.trollblox.dontbreakyourtools;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DontBreakYourTools implements ModInitializer {
	public static final String MOD_ID = "dontbreakyourtool";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Preparing to save your tools...");
	}
}