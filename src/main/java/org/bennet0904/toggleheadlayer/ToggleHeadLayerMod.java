package org.bennet0904.toggleheadlayer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;

public class ToggleHeadLayerMod implements ModInitializer {
  private static KeyBinding toggleHeadLayerKey;
  
  @Override
  public void onInitialize() {
    toggleHeadLayerKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
      "Toggle Head Layer",
      InputUtil.UNKNOWN_KEY.getCode(),
      "Toggle Head Layer"
    ));
    
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      while (toggleHeadLayerKey.wasPressed()) {
        toggleHeadLayer();
      }
    });
  }
  
  private void toggleHeadLayer() {
    MinecraftClient client = MinecraftClient.getInstance();
    boolean isEnabled = client.options.isPlayerModelPartEnabled(PlayerModelPart.HAT);
    client.options.togglePlayerModelPart(PlayerModelPart.HAT, !isEnabled);
  }
}