package nzjopdgengduofumos.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nzjopdgengduofumos.enchantment.CriticalStrikeEnchantment;
import nzjopdgengduofumos.Zjopdgengduofumos;
import nzjopdgengduofumos.enchantment.PotionProhibitionEnchantment;

public class ModEnchantments {
    // 现有的会心一击附魔
    public static final Enchantment CRITICAL_STRIKE = new CriticalStrikeEnchantment();
    // 新增的药水禁止附魔
    public static final Enchantment POTION_PROHIBITION = new PotionProhibitionEnchantment();


    public static void registerEnchantments() {
        // 注册会心一击附魔
        Registry.register(Registries.ENCHANTMENT,
                new Identifier(Zjopdgengduofumos.MOD_ID, "critical_strike"),
                CRITICAL_STRIKE);

        // 注册药水禁止附魔
        Registry.register(Registries.ENCHANTMENT,
                new Identifier(Zjopdgengduofumos.MOD_ID, "potion_prohibition"),
                POTION_PROHIBITION);
    }
}