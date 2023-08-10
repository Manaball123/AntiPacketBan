package me.mana.antipacketban.listeners.ons2cpacket;
//
//import com.comphenix.protocol.PacketType;
//import com.comphenix.protocol.events.*;
//import com.comphenix.protocol.reflect.StructureModifier;
//import me.mana.antipacketban.listeners.PacketAdapterBase;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.plugin.Plugin;
//
//
//import java.util.List;
//
////AKA InventoryS2C, ordinal 0x12
//public class OnWindowItemsS2CPacket extends PacketAdapterBase {
//    public OnWindowItemsS2CPacket(Plugin plugin, ListenerPriority listenerPriority) {
//        super(plugin, listenerPriority, PacketType.Play.Server.WINDOW_ITEMS);
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
//        event.setCancelled(true);
//
//        //plugin.getLogger().log(Level.WARNING, "Oversized window packet sent to " + event.getPlayer().getName());
//
//        StructureModifier<List<ItemStack>> itemStackListModifier = packet.getItemListModifier();
//        List<ItemStack> items = itemStackListModifier.read(0);
//        int length = items.toArray().length;
//
//
//        //Consider sending a new packet with empty windows
//        //ItemStack[] emptyArray = new ItemStack[length];
//        //Arrays.fill(emptyArray, new ItemStack(Material.AIR));
//        //itemStackListModifier.write(0, Arrays.asList(emptyArray));
//
//
//        //maybe swap these 2
//        //int 0: window id
//        int windowID = packet.getIntegers().read(0);
//        //int 1: state id
//        int stateID = packet.getIntegers().read(1);
//
//        //init all to air, and send items individually
//
//        //I hope this is indexed the same way as setslot...
//        //slot id is always 45 idk why
//        //Iterate over orig contents, send individually
//        for(int i = 0; i < length; i++) {
//            PacketContainer itemPacket = new PacketContainer(PacketType.Play.Server.SET_SLOT);
//            //window id
//            itemPacket.getIntegers().write(0, windowID);
//            //state id, maybe wont work
//            itemPacket.getIntegers().write(1, stateID);
//            //slot id, short
//            itemPacket.getIntegers().write(2, i);
//            //actual slot info
//            itemPacket.getItemModifier().write(0, items.get(i));
//            itemPacket.sendServerPacket(event.getPlayer());
//        }
//
//
//    }
//
//
//
//}
