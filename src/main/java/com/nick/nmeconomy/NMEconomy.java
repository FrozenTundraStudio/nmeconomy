package com.nick.nmeconomy;

import com.nick.nmeconomy.block.ModBlocks;
import com.nick.nmeconomy.creativemodetab.ModCreativeModeTabs;
import com.nick.nmeconomy.data.Player;
import com.nick.nmeconomy.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NMEconomy implements ModInitializer {
	public static final String MOD_ID = "nm-economy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("adddollar").executes(context -> {

				ServerPlayer player = context.getSource().getPlayer();

				double balance = Player.get(player).getBalance();
				double newBalance = balance + 1;
				Player.get(player).setBalance(newBalance);

				context.getSource().sendSuccess(() -> Component.literal("Added. New balance is now: " + balance), false);
				return 1;
			}));
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
