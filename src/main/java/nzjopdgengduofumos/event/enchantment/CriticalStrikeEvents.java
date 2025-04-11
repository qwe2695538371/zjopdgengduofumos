package nzjopdgengduofumos.event.enchantment;

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
                int level = EnchantmentHelper.getLevel(ModEnchantments.CRITICAL_STRIKE,
                        player.getMainHandStack());

                if (level > 0 && CriticalStrikeEnchantment.shouldTriggerCritical(level)) {
                    // 在原有伤害基础上额外造成一次伤害
                    entity.damage(world.getDamageSources().playerAttack(player),
                            player.getAttackDamage());
                }
            }
            return ActionResult.PASS;
        });
    }
}