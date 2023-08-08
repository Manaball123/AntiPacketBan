package me.mana.antipacketban;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import me.mana.antipacketban.utils.ItemStackUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;

public final class AntiPacketBan extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(this, ListenerPriority.HIGHEST, listenedPackets) {
                    // Note that this is executed asynchronously
                    @Override
                    public void onPacketReceiving(PacketEvent event)
                    {
                        //2mb threshold, no action needed before this point(maybe nbt parsers get fucked but will do later)
                        if (event.getPacket().getSize() >= 0x200000)
                        {
                            //check packet instance

                        }
                    }
                    @Override
                    public void onPacketSending(PacketEvent event)
                    {
                        //2mb
                        PacketContainer packet = event.getPacket();
                        PacketType packetType = packet.getType();

                        if (packet.getSize() < 0x200000)
                            return;
                            //plugin.getLogger().log(Level.INFO, "Event setcancelled");
                            //event.setCancelled(true);

                        LinkedBlockingQueue<PacketContainer> packetQueue = new LinkedBlockingQueue<PacketContainer>();
                        if(packetType == PacketType.Play.Server.SET_SLOT)
                        {
                            plugin.getLogger().log(Level.INFO, "Oversized slot packet!");
                            StructureModifier<ItemStack> itemStackStructureModifier = packet.getItemModifier();
                            //send dummy items
                            for(int i = 0; i < itemStackStructureModifier.size(); i++) {
                                ItemStack newStack = ItemStackUtils.removeData(itemStackStructureModifier.read(i));
                                itemStackStructureModifier.write(i, newStack);
                            }
                            return;
                        }
                        //event.setCancelled(true);
                        if(packetType == PacketType.Play.Server.WINDOW_ITEMS)
                        {
                            plugin.getLogger().log(Level.INFO, "Oversized window packet!");
                            event.setCancelled(true);
                            StructureModifier<List<ItemStack>> itemStackListModifier = packet.getItemListModifier();
                            List<ItemStack> items = itemStackListModifier.read(0);
                            int length = items.toArray().length;


                            //Consider sending a new packet with empty windows
                            //ItemStack[] emptyArray = new ItemStack[length];
                            //Arrays.fill(emptyArray, new ItemStack(Material.AIR));
                            //itemStackListModifier.write(0, Arrays.asList(emptyArray));


                            //maybe swap these 2
                            //int 0: window id
                            int windowID = packet.getIntegers().read(0);
                            //int 1: state id
                            int stateID = packet.getIntegers().read(1);

                            //init all to air, and send items individually

                            //I hope this is indexed the same way as setslot...
                            //slot id is always 45 idk why

                            for(int i = 0; i < length; i++) {
                                PacketContainer itemPacket = new PacketContainer(PacketType.Play.Server.SET_SLOT);
                                //window id
                                itemPacket.getIntegers().write(0, windowID);
                                //state id, maybe wont work
                                itemPacket.getIntegers().write(1, stateID);
                                //slot id, short
                                itemPacket.getIntegers().write(2, i);
                                //actual slot info
                                itemPacket.getItemModifier().write(0, items.get(i));

                                itemPacket.sendServerPacket(event.getPlayer());
                            }
                        }

                    }





                });
    }

    public List<PacketType> listenedPackets = Arrays.asList(
            PacketType.Play.Client.HELD_ITEM_SLOT,
            PacketType.Play.Server.WINDOW_ITEMS,
            PacketType.Play.Server.SET_SLOT
            );



    @Override
    public void onDisable()
    {
    }
}
