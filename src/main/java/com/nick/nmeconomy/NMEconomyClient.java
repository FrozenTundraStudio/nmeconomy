package com.nick.nmeconomy;

import com.nick.nmeconomy.render.menu.ModMenuTypes;
import com.nick.nmeconomy.render.screen.WalletScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class NMEconomyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.WALLET, WalletScreen::new);


    }
}
