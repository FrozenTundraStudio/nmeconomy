package com.nick.nmeconomy;

import com.nick.nmeconomy.block.ModBlocks;
import com.nick.nmeconomy.creativemodetab.ModCreativeModeTabs;
import com.nick.nmeconomy.data.EconomyPlayer;
import com.nick.nmeconomy.item.ModItems;
import com.nick.nmeconomy.item.components.CashComponent;
import com.nick.nmeconomy.item.components.ModComponents;
import com.nick.nmeconomy.network.ServerboundDepositPayload;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.Commands;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NMEconomy implements ModInitializer {
	public static final String MOD_ID = "nm-economy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		EconomyPlayer.registerEconomyPlayer();
		PayloadTypeRegistry.serverboundPlay().register(ServerboundDepositPayload.TYPE, ServerboundDepositPayload.CODEC);
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModComponents.registerModComponents();
		ItemComponentTooltipProviderRegistry.addAfter(DataComponents.DAMAGE, ModComponents.CASH_COMPONENT);

		ServerPlayNetworking.registerGlobalReceiver(ServerboundDepositPayload.TYPE, (payload, context) -> {
			ServerPlayer player = context.player();
			ItemStack heldItem = player.getMainHandItem();
			Item cashItem = heldItem.getItem();
			if (cashItem == ModItems.CASH) {
				CashComponent heldCash = heldItem.get(ModComponents.CASH_COMPONENT);
				int cashAmount = heldCash.amount();
				int depositAmount = payload.amount();
				int newCashAmount = cashAmount - depositAmount;
				int balance = EconomyPlayer.get(player).getBalance();
				int newBalance = balance + depositAmount;
				EconomyPlayer.get(player).setBalance(newBalance);
				CashComponent cashComponent = new CashComponent(newCashAmount);
				player.getMainHandItem().set(ModComponents.CASH_COMPONENT, cashComponent);
			}
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("adddollar").executes(context -> {
				ServerPlayer player = context.getSource().getPlayer();
				int balance = EconomyPlayer.get(player).getBalance();
				int newBalance = balance + 1;
				EconomyPlayer.get(player).setBalance(newBalance);
				context.getSource().sendSuccess(() -> Component.literal("Added. New balance is now: " + newBalance), false);
				NMEconomy.LOGGER.info(player.getPlainTextName() + " Used /adddollar");
				return 1;
			}));
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("balance").executes(context -> {

				ServerPlayer player = context.getSource().getPlayer();
				int balance = EconomyPlayer.get(player).getBalance();
				context.getSource().sendSuccess(() -> Component.literal("Balance: " + balance), false);
				NMEconomy.LOGGER.info(player.getPlainTextName() + " Used /balance");
				return 1;
			}));
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
