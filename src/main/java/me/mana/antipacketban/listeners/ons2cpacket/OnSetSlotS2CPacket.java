package me.mana.antipacketban.listeners.ons2cpacket;
//
//import com.comphenix.protocol.PacketType;
//import com.comphenix.protocol.ProtocolLibrary;
//import com.comphenix.protocol.events.ListenerPriority;
//import com.comphenix.protocol.events.PacketContainer;
//import com.comphenix.protocol.events.PacketEvent;
//import com.comphenix.protocol.reflect.StructureModifier;
//import me.mana.antipacketban.listeners.PacketAdapterBase;
//import me.mana.antipacketban.utils.ItemStackUtils;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.plugin.Plugin;
//import java.util.Arrays;
//
////AKA ScreenHandlerSlotUpdateS2CPacket, ordinal 0x14
//public class OnSetSlotS2CPacket extends PacketAdapterBase {
//    public OnSetSlotS2CPacket(Plugin plugin, ListenerPriority listenerPriority) {
//        super(plugin, listenerPriority, PacketType.Play.Server.SET_SLOT);
//    }
//    public void Init()
//    {
//        ProtocolLibrary.getProtocolManager().addPacketListener(this);
//    }
//
//    @Override
//    public void onPacketSending(PacketEvent event)
//    {
//        //2mb
//        PacketContainer packet = event.getPacket();
//        PacketType packetType = packet.getType();
//
//        if (packet.getSize() < 0x200000)
//            return;
//
//        //plugin.getLogger().log(Level.WARNING, "Oversized slot packet sent to " + event.getPlayer().getName());
//        StructureModifier<ItemStack> itemStackStructureModifier = packet.getItemModifier();
//        //send dummy item
//        //Prob only 1 but just to be safe
//        for(int i = 0; i < itemStackStructureModifier.size(); i++) {
//            ItemStack newStack = ItemStackUtils.removeData(itemStackStructureModifier.read(i));
//            ItemMeta meta = newStack.getItemMeta();
//            //Note: deprecated function used
//            meta.setLore(Arrays.asList("Oversized Item!"));
//            newStack.setItemMeta(meta);
//            itemStackStructureModifier.write(i, newStack);
//        }
//
//
//
//    }
//
//
//
//
//
//
//}
