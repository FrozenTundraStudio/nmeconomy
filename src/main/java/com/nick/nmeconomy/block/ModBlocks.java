package com.nick.nmeconomy.block;

import com.nick.nmeconomy.NMEconomy;
import com.nick.nmeconomy.block.custom.ATMBlock;
import com.nick.nmeconomy.block.custom.WalletBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    public static final Block ATM_BLOCK = registerBlock("atm",
            properties -> new ATMBlock(properties.strength(3f)));

    public static final Block WALLET_BLOCK = registerBlock("wallet_block",
            properties -> new WalletBlock(properties));

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, name)))));
    }
    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, name), toRegister);
    }

    public static void registerModBlocks() {
        NMEconomy.LOGGER.info("Registering Mod Blocks for " + NMEconomy.MOD_ID);
    }
}
