package nzjopdgengduofumos.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nzjopdgengduofumos.enchantment.CriticalStrikeEnchantment;
import nzjopdgengduofumos.Zjopdgengduofumos;

public class ModEnchantments {
    public static final Enchantment CRITICAL_STRIKE = new CriticalStrikeEnchantment();

    public static void registerEnchantments() {
        Registry.register(
                Registries.ENCHANTMENT,
                new Identifier(Zjopdgengduofumos.MOD_ID, "critical_strike"),
                CRITICAL_STRIKE
        );
    }
}