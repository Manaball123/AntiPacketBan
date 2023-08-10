package me.mana.antipacketban.listeners;

import io.netty.channel.Channel;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.profiling.jfr.event.PacketEvent;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class OnPacket {


    public class PacketEvent
    {

        public Player player;
        public Channel channel;

        public Object packet;
        public boolean cancelled;
        public PacketEvent(Player player, Channel channel, Object packet)
        {
            this.player = player;
            this.channel = channel;
            this.packet = packet;
        }
    }
    protected Class packetType;
    public OnPacket()
    {

    }
    public Object BaseCallback(Player sender, Channel channel, Object packet)
    {
        PacketEvent event = new PacketEvent(sender, channel, packet);
        Callback(event);
        if (event.cancelled)
            return null;
        return event.packet;
    }

    //Override this method
    public void Callback(PacketEvent event)
    {

    }

}
