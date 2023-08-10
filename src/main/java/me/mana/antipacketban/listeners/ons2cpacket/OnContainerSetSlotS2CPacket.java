package me.mana.antipacketban.listeners.ons2cpacket;


import me.mana.antipacketban.listeners.OnPacket;
import me.mana.antipacketban.utils.ItemStackUtils;
import me.mana.antipacketban.utils.PacketUtils;
import net.minecraft.nbt.*;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//AKA ScreenHandlerSlotUpdateS2CPacket, ordinal 0x14
public class OnContainerSetSlotS2CPacket extends OnPacket {

    @Override
    public void Callback(PacketEvent event)
    {
        //2mb


        if (PacketUtils.getSize(event.packet) < 0x200000)
            return;
        ClientboundContainerSetSlotPacket packet = (ClientboundContainerSetSlotPacket)event.packet;
        //plugin.getLogger().log(Level.WARNING, "Oversized slot packet sent to " + event.getPlayer().getName());

        ItemStack stack = packet.getItem();

        CompoundTag oldTag = stack.getTag();
        CompoundTag displayTag = new CompoundTag();
        if (oldTag.contains("display"))
            displayTag = oldTag.getCompound("display");

        ListTag loreTag = new ListTag();
        loreTag.add(StringTag.valueOf("{\"text\":\"Oversized Item!\"}"));
        displayTag.put("Lore", loreTag);
        CompoundTag newTag = new CompoundTag();
        newTag.put("display", displayTag);
        //clear nbt
        stack.setTag(newTag);


    }






}
