package nzjopdgengduofumos;

import net.fabricmc.api.ModInitializer;

import nzjopdgengduofumos.registry.ModEnchantments;
import nzjopdgengduofumos.registry.ModEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zjopdgengduofumos implements ModInitializer {
	public static final String MOD_ID = "zjopdgengduofumos";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		// 注册附魔
		ModEnchantments.registerEnchantments();

		// 注册事件监听器
		ModEvents.registerEvents();
		LOGGER.info("所有附魔初始化完成！");
	}
}