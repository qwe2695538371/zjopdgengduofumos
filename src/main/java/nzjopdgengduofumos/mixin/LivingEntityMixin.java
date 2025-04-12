package nzjopdgengduofumos.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.ActionResult;
import nzjopdgengduofumos.events.ApplyPotionEffectCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(
            method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onAddStatusEffect(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        ActionResult result = ApplyPotionEffectCallback.EVENT.invoker()
                .onApplyPotionEffect((LivingEntity) (Object) this, effect);

        if (result == ActionResult.FAIL) {
            cir.setReturnValue(false);
        }
    }
}