package com.example.xraymod.mixin;

import com.example.xraymod.XRayMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder")
public class XRayBlockRenderingMixin {
    
    @Inject(method = "method_3944", at = @At("HEAD"), cancellable = true)
    private static void onBlockRenderCheck(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (!XRayMod.isXRayEnabled()) {
            return;
        }
        
        // Делаем видимыми руду и ценные блоки
        if (isValueableBlock(block)) {
            cir.setReturnValue(false);
        }
    }
    
    private static boolean isValueableBlock(Block block) {
        return block == Blocks.DIAMOND_ORE ||
               block == Blocks.DEEPSLATE_DIAMOND_ORE ||
               block == Blocks.IRON_ORE ||
               block == Blocks.DEEPSLATE_IRON_ORE ||
               block == Blocks.GOLD_ORE ||
               block == Blocks.DEEPSLATE_GOLD_ORE ||
               block == Blocks.COPPER_ORE ||
               block == Blocks.DEEPSLATE_COPPER_ORE ||
               block == Blocks.LAPIS_ORE ||
               block == Blocks.DEEPSLATE_LAPIS_ORE ||
               block == Blocks.REDSTONE_ORE ||
               block == Blocks.DEEPSLATE_REDSTONE_ORE ||
               block == Blocks.EMERALD_ORE ||
               block == Blocks.DEEPSLATE_EMERALD_ORE ||
               block == Blocks.ANCIENT_DEBRIS ||
               block == Blocks.NETHER_GOLD_ORE ||
               block == Blocks.NETHER_QUARTZ_ORE;
    }
}