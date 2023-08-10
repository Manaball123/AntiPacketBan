package me.mana.antipacketban.listeners;

import com.comphenix.tinyprotocol.TinyProtocol;
import io.netty.channel.Channel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
public class TinyProtocolListener extends TinyProtocol {
    public TinyProtocolListener(Plugin plugin)
    {
        super(plugin);
    }
    @Override
    public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
        // Cancel chat packets

        if (packet instanceof ServerboundCustomPayloadPacket) {
            ServerboundCustomPayloadPacket cplPacketOrig = (ServerboundCustomPayloadPacket) packet;
            try {
                FriendlyByteBuf cplBuf = new FriendlyByteBuf(cplPacketOrig.data.copy());
                if (!cplBuf.isReadable())
                    return super.onPacketInAsync(sender, channel, packet);
                InputStream strStream = new ByteArrayInputStream(cplBuf.readByteArray());
                ObjectInputStream objStream = new ObjectInputStream(strStream);
                //Prevent serializable packets from being passed into the stream
                Object objTest = objStream.readObject();

                return null;
            } catch (Exception e) {
                //Allow packets that are not serializable
                return super.onPacketInAsync(sender, channel, packet);
            }


        }

        return super.onPacketInAsync(sender, channel, packet);
    }
}
