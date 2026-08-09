package com.nick.nmeconomy.block.entity.custom;

import com.nick.nmeconomy.block.entity.ModBlockEntities;
import com.nick.nmeconomy.container.ImplementedContainer;
import com.nick.nmeconomy.render.menu.WalletMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class WalletBlockEntity extends BlockEntity implements ImplementedContainer, MenuProvider {
    public static final int CONTAINER_SIZE = 3 * 3;
    private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public WalletBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WALLET_BLOCK_ENTITY, pos, state);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.items);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.items);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.nm-economy.wallet_block");
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new WalletMenu(containerId, inventory, this);
    }
}
