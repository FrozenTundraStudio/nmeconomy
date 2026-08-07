package com.nick.nmeconomy.render.screen.atm;

import com.nick.nmeconomy.item.ModItems;
import com.nick.nmeconomy.item.components.CashComponent;
import com.nick.nmeconomy.item.components.ModComponents;
import com.nick.nmeconomy.render.widgets.ATMBackgroundWidget;
import com.nick.nmeconomy.render.widgets.DepositTextFieldWidget;
import com.nick.nmeconomy.render.widgets.WithdrawTextFieldWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WithdrawScreen extends Screen {
    public Screen parent;
    public WithdrawScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(this.parent);
    }

    @Override
    protected void init() {
        ATMBackgroundWidget atmBackgroundWidget = new ATMBackgroundWidget(this.width/2 - 128, this.height/2 - 128, 256, 256);

        WithdrawTextFieldWidget withdrawTextFieldWidget = new WithdrawTextFieldWidget(font, this.width/2 - 75, this.height/2 - 35, 150, 25, Component.empty());

        Button withdrawWidget = Button.builder(Component.literal("Withdraw"), (btn) -> {
            Player player = this.minecraft.player;
            Item cash = ModItems.CASH;
            ItemStack heldItem = this.minecraft.player.getMainHandItem();
            Item cashItem = heldItem.getItem();
            if (cashItem == ModItems.CASH) {
                CashComponent heldCash = heldItem.get(ModComponents.CASH_COMPONENT);
                int cashAmount = heldCash.amount();
                int depositAmount = Integer.parseInt(withdrawTextFieldWidget.getValue());
                int newCashAmount = cashAmount + depositAmount;
                CashComponent cashComponent = new CashComponent(newCashAmount);
                this.minecraft.player.getMainHandItem().set(ModComponents.CASH_COMPONENT, cashComponent);

            }
        }).bounds(this.width/2 - 35, this.height/2 + 30, 70, 20).build();

        Button mainMenuWidget = Button.builder(Component.literal("Main Menu"), (btn) -> {
            this.onClose();
        }).bounds(this.width/2 - 35, this.height/2 + 70, 70, 20).build();

        this.addRenderableWidget(atmBackgroundWidget);
        this.addRenderableWidget(withdrawTextFieldWidget);
        this.addRenderableWidget(withdrawWidget);
        this.addRenderableWidget(mainMenuWidget);

    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);

    }
}
