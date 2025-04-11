package nzjopdgengduofumos.event.enchantment;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import nzjopdgengduofumos.registry.ModEnchantments;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import nzjopdgengduofumos.enchantment.CriticalStrikeEnchantment;
import org.jetbrains.annotations.Nullable;

/**
 * 会心一击附魔的事件处理类
 */
public class CriticalStrikeEvents {
    /**
     * 注册会心一击附魔相关的所有事件监听器
     */
    public static void register() {
        registerAttackEvent();
    }

    /**
     * 注册攻击事件监听器
     */
    private static void registerAttackEvent() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (hand == Hand.MAIN_HAND && !world.isClient) {
                ItemStack weapon = player.getMainHandStack();
                int level = EnchantmentHelper.getLevel(ModEnchantments.CRITICAL_STRIKE, weapon);

                if (level > 0 && CriticalStrikeEnchantment.shouldTriggerCritical(level)) {
                    // 获取基础攻击伤害
                    double baseAttackDamage = player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    // 获取附魔加成伤害
                    float enchantmentDamage = EnchantmentHelper.getAttackDamage(weapon, EntityGroup.DEFAULT);
                    // 计算总伤害
                    float totalDamage = (float)baseAttackDamage + enchantmentDamage;
                    // 造成双倍伤害
                    entity.damage(world.getDamageSources().playerAttack(player), totalDamage * 2);
                    // 取消原有的伤害事件
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}