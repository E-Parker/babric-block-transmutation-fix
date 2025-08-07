package net.fabricmc.transmutation_patch.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Properties;

@Environment(EnvType.CLIENT)

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {

    @Shadow
    private Properties translations;

    @Inject(method = "method_995", at = @At("HEAD"), cancellable = true)
    private void overrideTranslation(String par1, CallbackInfoReturnable<String> cir) {
        if (par1 == null || par1.endsWith("transmuted")) {
            String clippedName = par1.substring(0, par1.lastIndexOf("."));
            String translatedName = this.translations.getProperty(clippedName + ".name", "Block");
            cir.setReturnValue("Transmuted " + translatedName);
            return;
        }
    }
}
