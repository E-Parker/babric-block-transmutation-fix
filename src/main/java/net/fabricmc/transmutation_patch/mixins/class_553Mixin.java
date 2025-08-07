package net.fabricmc.transmutation_patch.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.class_553;

@Mixin(class_553.class)
public class class_553Mixin  {
    // Patch of class_553 (likely slab item) to fix the out-of-bounds exception that happens when hovering it in inventory.
    //
    //

    // Create a variable to shadow the original field_2323, marking it as mutable to get around the static final definition.
    @Shadow
    @Mutable
    private static String[] field_2323;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void extendSubtypeArray(CallbackInfo ci) {

        // the new array should be 16 items long since that is the highest damage value an item can have.
        String[] extended = new String[16];
        int length = class_553.field_2323.length;

        System.arraycopy(class_553.field_2323, 0, extended, 0, length);

        // Override the new indices with a custom name.
        for (int i = length; i < extended.length; i++) {
            extended[i] = class_553.field_2323[0] + ".transmuted";
        }

        // Override the original field.
        field_2323 = extended;

    }
}
