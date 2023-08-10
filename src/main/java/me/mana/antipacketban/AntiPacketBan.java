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


    private TinyProtocol protocol;
    @Override
    public void onEnable() {
        this.getLogger().info("starting to conbstruct");
        protocol = new TinyProtocolListener(this);


        //new OnSetSlotS2CPacket(this, ListenerPriority.HIGHEST).Init();
        //new OnWindowItemsS2CPacket(this, ListenerPriority.HIGHEST).Init();
        //new OnBookEditC2Sacket(this, ListenerPriority.HIGHEST).Init();
        //new OnItemNameC2SPacket(this, ListenerPriority.HIGHEST).Init();

    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }



    @Override
    public void onDisable()
    {
    }
}
