package nzjopdgengduofumos.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import java.util.Random;

public class CriticalStrikeEnchantment extends Enchantment {
    private static final Random RANDOM = new Random();

    public CriticalStrikeEnchantment() {
        super(Rarity.RARE,
                EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem;
    }

    public static boolean shouldTriggerCritical(int level) {
        float chance = switch (level) {
            case 1 -> 0.15f;
            case 2 -> 0.30f;
            case 3 -> 0.60f;
            default -> 0.0f;
        };
        return RANDOM.nextFloat() < chance;
    }
}