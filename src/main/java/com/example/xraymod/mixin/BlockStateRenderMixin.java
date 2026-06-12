package com.example.xraymod.mixin;

import com.example.xraymod.XRayMod;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModelRenderer.class)
public class BlockStateRenderMixin {
    
    @Inject(
            method = "render",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onBlockRender(BlockState state, BlockPos pos, 
                              CallbackInfoReturnable<Boolean> cir) {
        if (XRayMod.isXRayEnabled()) {
            // Скрываем каменные блоки для лучшего отображения руды
            if (isStoneBlock(state)) {
                cir.setReturnValue(false);
            }
        }
    }
    
    private boolean isStoneBlock(BlockState state) {
        String blockName = state.getBlock().getTranslationKey();
        return blockName.contains("stone") ||
               blockName.contains("granite") ||
               blockName.contains("diorite") ||
               blockName.contains("andesite") ||
               blockName.contains("deepslate");
    }
}