package com.nick.nmeconomy.item.components;

import com.nick.nmeconomy.NMEconomy;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModComponents {

    public static final DataComponentType<CashComponent> CASH_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, "cash"),
            DataComponentType.<CashComponent>builder().persistent(CashComponent.CODEC).build()
    );

    public static void registerModComponents() {
        NMEconomy.LOGGER.info("Registering Mod Components for " + NMEconomy.MOD_ID);
    }
}


