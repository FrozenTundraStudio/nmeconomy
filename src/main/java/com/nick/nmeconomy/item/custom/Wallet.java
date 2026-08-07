package com.nick.nmeconomy.item.custom;

import com.nick.nmeconomy.render.menu.ImplementedContainer;
import com.nick.nmeconomy.render.menu.WalletMenu;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class Wallet extends Item implements ImplementedContainer, MenuProvider {
    public Wallet(Properties properties) {
        super(properties);
    }
    public static final int CONTAINER_SIZE = 3 * 3;
    private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    @NonNull
    public Component getDisplayName() {
        return Component.translatable("item.nm-economy.wallet");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new WalletMenu(containerId, inventory, this);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide() && player.getMainHandItem().getItem() instanceof Wallet wallet) {
            player.openMenu(wallet);
        }
        return InteractionResult.SUCCESS;
    }

}
