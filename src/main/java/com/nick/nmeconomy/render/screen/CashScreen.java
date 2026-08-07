package com.nick.nmeconomy.render.screen;

import com.nick.nmeconomy.render.screen.atm.DepositScreen;
import com.nick.nmeconomy.render.widgets.ATMBackgroundWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CashScreen extends Screen {
    protected CashScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        ATMBackgroundWidget atmBackgroundWidget = new ATMBackgroundWidget(this.width/2 - 128, this.height/2 - 128, 256, 256);

        Button depositWidget = Button.builder(Component.literal("Deposit"), (btn) -> {
            Screen currentScreen = Minecraft.getInstance().gui.screen();
            Minecraft.getInstance().setScreenAndShow(
                    new DepositScreen(Component.empty(), currentScreen)
            );
        }).bounds(this.width/2 - 40, this.height/2 - 50, 80, 20).build();

        Button withdrawWidget = Button.builder(Component.literal("Withdraw"), (btn) -> {

        }).bounds(this.width/2 - 40, this.height/2 - 10, 80, 20).build();

        Button closeWidget = Button.builder(Component.literal("Close"), (btn) -> {
            Minecraft.getInstance().setScreenAndShow(null);
        }).bounds(this.width/2 - 40, this.height/2 + 30, 80, 20).build();

        this.addRenderableWidget(atmBackgroundWidget);
        this.addRenderableWidget(depositWidget);
        this.addRenderableWidget(withdrawWidget);
        this.addRenderableWidget(closeWidget);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);

    }
}
