package nzjopdgengduofumos.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class PotionProhibitionEnchantment extends Enchantment {
    public PotionProhibitionEnchantment() {
        super(Rarity.RARE,                  // 附魔稀有度
                EnchantmentTarget.ARMOR_HEAD,  // 只能附魔在头盔上
                new EquipmentSlot[]{EquipmentSlot.HEAD}); // 装备槽位为头部
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem armorItem
                && armorItem.getSlotType() == EquipmentSlot.HEAD;
    }
}