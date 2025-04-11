package nzjopdgengduofumos.event.enchantment;

import net.minecraft.particle.DustParticleEffect;
import nzjopdgengduofumos.registry.ModEnchantments;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import nzjopdgengduofumos.enchantment.CriticalStrikeEnchantment;
import org.joml.Vector3f;

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
                int level = EnchantmentHelper.getLevel(ModEnchantments.CRITICAL_STRIKE, player.getMainHandStack());

                if (level > 0 && CriticalStrikeEnchantment.shouldTriggerCritical(level)) {
                    // 根据等级获取固定加成伤害
                    float bonusDamage = switch (level) {
                        case 1 -> 3.0f;  // 触发概率15%，加3点伤害
                        case 2 -> 6.0f;  // 触发概率30%，加6点伤害
                        case 3 -> 9.0f;  // 触发概率60%，加9点伤害
                        default -> 0.0f;
                    };

                    // 计算原始伤害
                    float originalDamage = (float) player.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    // 计算总伤害 = 原始伤害 + 固定加成
                    float totalDamage = originalDamage + bonusDamage;

                    // 造成伤害
                    entity.damage(world.getDamageSources().playerAttack(player), totalDamage);

                    // 生成红色粒子效果
                    spawnCriticalParticles((ServerWorld) world, entity.getPos());

                    // 取消原有的伤害事件
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }

    /**
     * 在目标位置生成红色粒子效果
     */
    private static void spawnCriticalParticles(ServerWorld world, Vec3d pos) {
        // 创建红色粒子效果
        DustParticleEffect redParticle = new DustParticleEffect(
                new Vector3f(1.0f, 0.0f, 0.0f), // 红色 RGB(1, 0, 0)
                1.0f  // 粒子大小
        );

        // 在目标周围生成粒子
        for (int i = 0; i < 20; i++) {  // 生成20个粒子
            double offsetX = (world.random.nextDouble() - 0.5) * 0.5;  // -0.25 到 0.25 的随机偏移
            double offsetY = world.random.nextDouble() * 1.0;          // 0 到 1.0 的随机高度
            double offsetZ = (world.random.nextDouble() - 0.5) * 0.5;  // -0.25 到 0.25 的随机偏移

            world.spawnParticles(
                    redParticle,
                    pos.x + offsetX,    // X 坐标
                    pos.y + offsetY,    // Y 坐标
                    pos.z + offsetZ,    // Z 坐标
                    1,                  // 粒子数量
                    0.0, 0.0, 0.0,     // XYZ 速度
                    0.0                 // 粒子速度
            );
        }
    }
}