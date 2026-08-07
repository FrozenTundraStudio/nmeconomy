package com.nick.nmeconomy.event;

import net.fabricmc.fabric.mixin.client.gametest.input.MouseHandlerAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;

public class CashClickEvent extends MouseHandler {
    MouseHandlerAccessor accessor = new MouseHandlerAccessor() {
        @Override
        public void invokeOnButton(long window, MouseButtonInfo arg, int action) {
            if(arg.isRight()) {

            }
        }

        @Override
        public void invokeOnScroll(long window, double horizontal, double vertical) {

        }

        @Override
        public void invokeOnMove(long window, double x, double y) {

        }

    };

    public CashClickEvent(Minecraft minecraft) {
        super(minecraft);
    }
}
