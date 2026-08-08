package com.nick.nmeconomy.render.screen.atm;

import com.nick.nmeconomy.data.EconomyPlayer;
import com.nick.nmeconomy.item.ModItems;
import com.nick.nmeconomy.item.components.CashComponent;
import com.nick.nmeconomy.item.components.ModComponents;
import com.nick.nmeconomy.network.ServerboundDepositPayload;
import com.nick.nmeconomy.network.ServerboundWithdrawPayload;
import com.nick.nmeconomy.render.widgets.ATMBackgroundWidget;
import com.nick.nmeconomy.render.widgets.DepositTextFieldWidget;
import com.nick.nmeconomy.render.widgets.WithdrawTextFieldWidget;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
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
            int amount = Integer.parseInt(withdrawTextFieldWidget.getValue());
            ServerboundWithdrawPayload payload = new ServerboundWithdrawPayload(amount);
            ClientPlayNetworking.send(payload);
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
        Player player = this.minecraft.player;
        int balance = EconomyPlayer.get(player).getBalance();
        graphics.text(this.font, "Balance: " + balance, this.width/2 - 35, this.height/2 - this.font.lineHeight - 60, 0xFFFFFFFF, true);
    }
}
