package com.nick.nmeconomy.render.screen.atm;

import com.nick.nmeconomy.data.EconomyPlayer;
import com.nick.nmeconomy.network.ServerboundDepositPayload;
import com.nick.nmeconomy.render.widgets.ATMBackgroundWidget;
import com.nick.nmeconomy.render.widgets.DepositTextFieldWidget;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class DepositScreen extends Screen {
    public Screen parent;
    public DepositScreen(Component title, Screen parent) {
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

        DepositTextFieldWidget depositTextFieldWidget = new DepositTextFieldWidget(font, this.width/2 - 75, this.height/2 - 35, 150, 25, Component.empty());

        Button depositWidget = Button.builder(Component.literal("Deposit"), (btn) -> {
            int amount = Integer.parseInt(depositTextFieldWidget.getValue());
            ServerboundDepositPayload payload = new ServerboundDepositPayload(amount);
            ClientPlayNetworking.send(payload);
        }).bounds(this.width/2 - 35, this.height/2 + 30, 70, 20).build();

        Button mainMenuWidget = Button.builder(Component.literal("Main Menu"), (btn) -> {
            this.onClose();
        }).bounds(this.width/2 - 35, this.height/2 + 70, 70, 20).build();

        this.addRenderableWidget(atmBackgroundWidget);
        this.addRenderableWidget(depositTextFieldWidget);
        this.addRenderableWidget(depositWidget);
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
