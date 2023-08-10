package me.mana.antipacketban;
//
//import com.comphenix.protocol.PacketType;
//import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.tinyprotocol.Reflection;
import com.comphenix.tinyprotocol.Reflection.FieldAccessor;
import com.comphenix.tinyprotocol.TinyProtocol;
import io.netty.channel.Channel;
//
//import me.mana.antipacketban.listeners.onc2spacket.OnBookEditC2Sacket;
//import me.mana.antipacketban.listeners.onc2spacket.OnItemNameC2SPacket;
//import me.mana.antipacketban.listeners.ons2cpacket.OnSetSlotS2CPacket;
//import me.mana.antipacketban.listeners.ons2cpacket.OnWindowItemsS2CPacket;

import me.mana.antipacketban.listeners.TinyProtocolListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
public final class AntiPacketBan extends JavaPlugin
{
    private Class<?> customPayload = ServerboundCustomPayloadPacket.class;
    //private FieldAccessor<FriendlyByteBuf> CustomPayloadC2SDataSerializer = Reflection.getField(customPayload, FriendlyByteBuf.class, 0);
    public static AntiPacketBan plugin;

    public static TinyProtocol protocol;
    @Override
    public void onEnable() {
        plugin = this;
        this.getLogger().info("starting to conbstruct");
        protocol = new TinyProtocolListener(this);


    }





    @Override
    public void onDisable()
    {
    }
}
