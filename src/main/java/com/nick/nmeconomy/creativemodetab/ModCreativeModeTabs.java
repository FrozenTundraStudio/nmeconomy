package com.nick.nmeconomy.creativemodetab;

import com.nick.nmeconomy.NMEconomy;
import com.nick.nmeconomy.block.ModBlocks;
import com.nick.nmeconomy.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab NMEconomyMod = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, "nm-economy"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BANK_NOTE))
                    .title(Component.translatable("creativemodetab.nm-economy.nm-economy"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BANK_NOTE);
                        output.accept(ModBlocks.ATM_BLOCK);

                    }).build());



    public static void registerModCreativeModeTabs() {
        NMEconomy.LOGGER.info("Registering Creative Mode Tabs for " + NMEconomy.MOD_ID);
    }
}
