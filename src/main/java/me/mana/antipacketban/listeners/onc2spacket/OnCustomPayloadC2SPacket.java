package me.mana.antipacketban.listeners.onc2spacket;


import io.netty.channel.Channel;
import me.mana.antipacketban.AntiPacketBan;
import me.mana.antipacketban.listeners.OnPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;

public class OnCustomPayloadC2SPacket extends OnPacket {

    public OnCustomPayloadC2SPacket()
    {
        packetType = ServerboundCustomPayloadPacket.class;
    }


    @Override
    public void Callback(PacketEvent event)
    {
        //Checks for serialized objects in custompayload and removes them
        ServerboundCustomPayloadPacket packet = (ServerboundCustomPayloadPacket) event.packet;
        try {
            FriendlyByteBuf cplBuf = new FriendlyByteBuf(packet.data.copy());
            if (!cplBuf.isReadable())
                return;

            InputStream strStream = new ByteArrayInputStream(cplBuf.readByteArray());
            ObjectInputStream objStream = new ObjectInputStream(strStream);

            Object objTest = objStream.readObject();
            //Prevent serializable packets from being passed into the stream
            event.cancelled = true;
            return;
        } catch (Exception e) {
            //Allow packets that are not serializable
            return;
        }



    }

}
