package nzjopdgengduofumos.event.enchantment;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import nzjopdgengduofumos.events.ApplyPotionEffectCallback;
import nzjopdgengduofumos.registry.ModEnchantments;

public class PotionProhibitionEvents {
    public static void register() {
        // 监听实体装备变化
        ServerEntityEvents.EQUIPMENT_CHANGE.register((entity, slot, previous, current) -> {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                // 如果是头盔槽位发生变化且有药水禁止附魔
                if (slot == EquipmentSlot.HEAD && hasPotionProhibition(livingEntity)) {
                    // 移除所有状态效果
                    livingEntity.getStatusEffects().clear();
                }
            }
        });

        // 监听伤害事件，处理瞬间伤害和瞬间治疗
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (hasPotionProhibition(entity)) {
                // 检查伤害源是否来自药水效果
                if (source.isOf(DamageTypes.MAGIC)) {
                    // 如果是药水造成的伤害，取消伤害
                    return false;
                }
            }
            return true;
        });

        // 注册药水效果应用前的检查
        ApplyPotionEffectCallback.EVENT.register((entity, effect) -> {
            if (hasPotionProhibition(entity)) {
                // 如果有药水禁止附魔，阻止效果应用
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });
    }

    public static boolean hasPotionProhibition(LivingEntity entity) {
        ItemStack helmet = entity.getEquippedStack(EquipmentSlot.HEAD);
        return EnchantmentHelper.getLevel(ModEnchantments.POTION_PROHIBITION, helmet) > 0;
    }
}