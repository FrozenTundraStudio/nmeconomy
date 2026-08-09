package com.nick.nmeconomy.render.menu;

import com.nick.nmeconomy.NMEconomy;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<WalletMenu> WALLET_BLOCK = register("wallet_block", WalletMenu::new);

    public static <T extends AbstractContainerMenu> MenuType<T> register(
            String name,
            MenuType.MenuSupplier<T> constructor
    ) {
        return Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, name), new MenuType<>(constructor, FeatureFlagSet.of()));
    }

    public static void initialize() {
    }
}
