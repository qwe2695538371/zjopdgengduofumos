package nzjopdgengduofumos.registry;

import nzjopdgengduofumos.Zjopdgengduofumos;
import nzjopdgengduofumos.event.enchantment.CriticalStrikeEvents;
import nzjopdgengduofumos.event.enchantment.PotionProhibitionEvents;

/**
 * 统一事件注册类
 * 用于注册所有模组相关的事件监听器
 */
public class ModEvents {
    /**
     * 注册所有事件监听器
     */
    public static void registerEvents() {
        // 注册附魔相关事件
        registerEnchantmentEvents();

        Zjopdgengduofumos.LOGGER.info("所有事件监听器注册完成");
    }

    /**
     * 注册所有附魔相关的事件监听器
     */
    private static void registerEnchantmentEvents() {
        // 注册会心一击事件
        CriticalStrikeEvents.register();
        // 注册药水禁止事件
        PotionProhibitionEvents.register();

        Zjopdgengduofumos.LOGGER.info("附魔相关事件监听器注册完成");
    }
}
