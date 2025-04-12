package nzjopdgengduofumos.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.ActionResult;

public interface ApplyPotionEffectCallback {
    Event<ApplyPotionEffectCallback> EVENT = EventFactory.createArrayBacked(ApplyPotionEffectCallback.class,
            (listeners) -> (entity, effect) -> {
                for (ApplyPotionEffectCallback listener : listeners) {
                    ActionResult result = listener.onApplyPotionEffect(entity, effect);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult onApplyPotionEffect(LivingEntity entity, StatusEffectInstance effect);
}