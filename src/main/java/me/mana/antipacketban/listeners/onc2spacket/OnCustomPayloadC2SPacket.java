package me.mana.antipacketban.listeners.onc2spacket;
//
//import com.comphenix.protocol.PacketType;
//import com.comphenix.protocol.events.ListenerPriority;
//import com.comphenix.protocol.events.PacketContainer;
//import com.comphenix.protocol.events.PacketEvent;
//import me.mana.antipacketban.listeners.PacketAdapterBase;
//import org.bukkit.plugin.Plugin;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.io.ObjectInputStream;
//import java.util.logging.Level;
//
//public class OnCustomPayloadC2SPacket extends PacketAdapterBase {
//    public OnCustomPayloadC2SPacket(Plugin plugin, ListenerPriority listenerPriority) {
//            super(plugin, listenerPriority, PacketType.Play.Client.CUSTOM_PAYLOAD);
//        }
//    @Override
//    public void onPacketReceiving(PacketEvent event)
//    {
//        plugin.getLogger().log(Level.INFO, "Custom Payload");
//        PacketContainer packet = event.getPacket();
//        //List<String> stringList = (List<String>)packet.getLists().read(0);
//        //String payload = stringList.get(0);
//
//
//        byte[] payload = packet.getByteArrays().read(0);
//
//        InputStream strStream = new ByteArrayInputStream(payload);
//        try
//        {
//            ObjectInputStream objStream = new ObjectInputStream(strStream);
//            Object kek = objStream.readObject();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//
//
//    }
//}
