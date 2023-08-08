package me.mana.antipacketban.utils;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackUtils {
    public static ItemStack removeData(final ItemStack itemStack)
    {
        ItemStack clearedItemStack = new ItemStack(itemStack.getType(), itemStack.getAmount());
        ItemMeta meta = clearedItemStack.getItemMeta();
        meta.setDisplayName(itemStack.getItemMeta().getDisplayName());
        clearedItemStack.setItemMeta(meta);
        return clearedItemStack;

        //TODO: fix bundles that no one uses anyway seriously who tf uses this shit
        /*
        if (itemStackCopy.getType() == Material.BUNDLE) {
            // Bundles change their texture based on their fullness.
            org.bukkit.inventory.meta.BundleMeta bundleMeta = (org.bukkit.inventory.meta.BundleMeta) itemStackCopy.getItemMeta();
            int sizeUsed = 0;
            for (org.bukkit.inventory.ItemStack item : bundleMeta.getItems()) {
                int scale = 64 / item.getMaxStackSize();
                sizeUsed += scale * item.getAmount();
            }
            // Now we add a single fake item that uses the same amount of slots as all other items.
            ListTag items = new ListTag();
            items.add(new ItemStack(Items.PAPER, sizeUsed).save(new CompoundTag()));
            tag.put("Items", items);
        }
        */

    };

}
    /*
    //Credits to papermc
    public static ItemStack sanitizeItemStack(final ItemStack itemStack, final boolean copyItemStack) {
        if (itemStack.getAmount() == 0
                //TODO: find alternative(should work regardless tho)
                //|| !itemStack.hasTag()
        ) {
            return itemStack;
        }

        final ItemStack copy = copyItemStack ? itemStack.clone() : itemStack;
        final CompoundTag tag = copy.getTag();
        if (copy.getType() == Material.BUNDLE && tag.get("Items") instanceof ListTag oldItems && !oldItems.isEmpty()) {
            // Bundles change their texture based on their fullness.
            org.bukkit.inventory.meta.BundleMeta bundleMeta = (org.bukkit.inventory.meta.BundleMeta) copy.asBukkitMirror().getItemMeta();
            int sizeUsed = 0;
            for (org.bukkit.inventory.ItemStack item : bundleMeta.getItems()) {
                int scale = 64 / item.getMaxStackSize();
                sizeUsed += scale * item.getAmount();
            }
            // Now we add a single fake item that uses the same amount of slots as all other items.
            ListTag items = new ListTag();
            items.add(new ItemStack(Items.PAPER, sizeUsed).save(new CompoundTag()));
            tag.put("Items", items);
        }
        if (tag.get("BlockEntityTag") instanceof CompoundTag blockEntityTag) {
            blockEntityTag.remove("Items");
        }
        return copy;
    }
            // Paper end

     */
