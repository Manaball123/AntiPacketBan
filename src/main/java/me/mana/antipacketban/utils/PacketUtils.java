package me.mana.antipacketban.utils;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

import java.lang.reflect.Method;

public class PacketUtils {
    public static int getSize(Object packet)
    {
        try {
            //the byte buf is friendly :)
            //FriendlyByteBuf aka PacketDataSerializer
            Method writeToBufferMethod = packet.getClass().getMethod("a", FriendlyByteBuf.class);
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer(256));
            writeToBufferMethod.invoke(packet, buf);
            return buf.writerIndex();

        }catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }

    }

}
