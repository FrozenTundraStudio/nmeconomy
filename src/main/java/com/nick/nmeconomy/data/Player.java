package com.nick.nmeconomy.data;

import com.mojang.serialization.Codec;
import com.nick.nmeconomy.NMEconomy;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class Player {
//    public Player(ByteBuf buffer) {}
//
//    public void encode(ByteBuf buffer){}
//
//    public static StreamCodec<ByteBuf, Player> STREAM_CODEC =
//            StreamCodec.ofMember(Player::encode, Player::new);

    public static final AttachmentType<Double> BALANCE = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(NMEconomy.MOD_ID, "balance"),
            builder -> builder
                    .initializer(() -> new Double(0.00))
                    .syncWith(ByteBufCodecs.DOUBLE, AttachmentSyncPredicate.targetOnly())
                    .persistent(Codec.DOUBLE)
                    .copyOnDeath()
    );

    public static PlayerData get(AttachmentTarget target) {
        return new PlayerData(target);
    }

    public record PlayerData(AttachmentTarget target) {
        public double getBalance() {
            return this.target.getAttachedOrElse(BALANCE, 0.00);
        }

        public void setBalance(double balance) {
            this.target.setAttached(BALANCE, balance);
        }
    }
}
