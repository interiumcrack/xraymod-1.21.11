package com.example.xraymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class XRayMod implements ModInitializer {
    private static KeyBinding toggleXRayKey;
    public static boolean xrayEnabled = false;

    @Override
    public void onInitialize() {
        // Регистрируем горячую клавишу для включения/отключения X-Ray
        toggleXRayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.xraymod.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                "category.xraymod"
        ));

        // Слушатель для обработки нажатия клавиши
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleXRayKey.wasPressed()) {
                xrayEnabled = !xrayEnabled;
                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal(
                                    "X-Ray: " + (xrayEnabled ? "§aВключен" : "§cВыключен")
                            ),
                            true
                    );
                }
            }
        });
    }

    public static boolean isXRayEnabled() {
        return xrayEnabled;
    }
}