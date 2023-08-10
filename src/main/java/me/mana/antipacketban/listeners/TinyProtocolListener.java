package me.mana.antipacketban.listeners;

import com.comphenix.tinyprotocol.TinyProtocol;
import io.netty.channel.Channel;
import me.mana.antipacketban.listeners.onc2spacket.OnCustomPayloadC2SPacket;
import me.mana.antipacketban.listeners.ons2cpacket.OnContainerSetContentS2CPacket;
import me.mana.antipacketban.listeners.ons2cpacket.OnContainerSetSlotS2CPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
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
    private OnCustomPayloadC2SPacket onCustomPayloadC2SPacket = new OnCustomPayloadC2SPacket();
    private HashMap<Class, OnPacket> s2cPacketListenerMap = new HashMap<>(0);
    private HashMap<Class, OnPacket> c2sPacketListenerMap = new HashMap<>(0);
    public TinyProtocolListener(Plugin plugin)
    {
        super(plugin);
        c2sPacketListenerMap.put(ServerboundCustomPayloadPacket.class, new OnCustomPayloadC2SPacket());
        s2cPacketListenerMap.put(ClientboundContainerSetSlotPacket.class, new OnContainerSetSlotS2CPacket());
        s2cPacketListenerMap.put(ClientboundContainerSetContentPacket.class, new OnContainerSetContentS2CPacket());

    }


    @Override
    public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
        if (c2sPacketListenerMap.containsKey(packet.getClass()))
        {
            return c2sPacketListenerMap.get(packet.getClass()).BaseCallback(sender, channel, packet);
        }

        return super.onPacketInAsync(sender, channel, packet);

    }
    @Override
    public Object onPacketOutAsync(Player sender, Channel channel, Object packet) {
        if (s2cPacketListenerMap.containsKey(packet.getClass()))
        {
            return s2cPacketListenerMap.get(packet.getClass()).BaseCallback(sender, channel, packet);
        }

        return super.onPacketOutAsync(sender, channel, packet);

    }

}
