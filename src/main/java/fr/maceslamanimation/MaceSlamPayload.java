package fr.maceslamanimation;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MaceSlamPayload() implements CustomPacketPayload {
    public static final Type<MaceSlamPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("maceslamanimation", "slam_packet"));
    public static final StreamCodec<FriendlyByteBuf, MaceSlamPayload> CODEC = StreamCodec.unit(new MaceSlamPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}