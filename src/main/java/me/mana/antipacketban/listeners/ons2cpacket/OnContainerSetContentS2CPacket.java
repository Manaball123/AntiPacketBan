package me.mana.antipacketban.listeners.ons2cpacket;


import me.mana.antipacketban.AntiPacketBan;
import me.mana.antipacketban.listeners.OnPacket;
import me.mana.antipacketban.utils.PacketUtils;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.world.item.ItemStack;


import java.util.List;

//AKA InventoryS2C, ordinal 0x12
public class OnContainerSetContentS2CPacket extends OnPacket {


    @Override
    public void Callback(PacketEvent event)
    {
        //2mb
        ClientboundContainerSetContentPacket packet = (ClientboundContainerSetContentPacket) event.packet;
        if (PacketUtils.getSize(event.packet) < 0x200000)
            return;

        event.cancelled = true;

        //plugin.getLogger().log(Level.WARNING, "Oversized window packet sent to " + event.getPlayer().getName());

        List<ItemStack> itemStacks = packet.getItems();
        ItemStack carriedItem = packet.getCarriedItem();

        int length = itemStacks.size();
        int containerID = packet.getContainerId();

        int stateID = packet.getStateId();

        //Consider sending a new packet with empty windows
        //ItemStack[] emptyArray = new ItemStack[length];
        //Arrays.fill(emptyArray, new ItemStack(Material.AIR));
        //itemStackListModifier.write(0, Arrays.asList(emptyArray));

        //init all to air, and send items individually

        //I hope this is indexed the same way as setslot...
        //slot id is always 45 idk why
        //Iterate over orig contents, send individually
        for(int i = 0; i < length; i++) {

            ClientboundContainerSetSlotPacket slotPacket = new ClientboundContainerSetSlotPacket(containerID, stateID, i, itemStacks.get(i));
            AntiPacketBan.protocol.sendPacket(event.player, slotPacket);

        }
        ClientboundContainerSetSlotPacket slotPacket = new ClientboundContainerSetSlotPacket(containerID, stateID, -1, carriedItem);
        AntiPacketBan.protocol.sendPacket(event.player, slotPacket);


    }



}
