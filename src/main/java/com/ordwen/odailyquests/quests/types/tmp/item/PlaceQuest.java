package com.ordwen.odailyquests.quests.types.tmp.item;

import com.ordwen.odailyquests.quests.types.shared.BasicQuest;
import com.ordwen.odailyquests.quests.types.shared.ItemQuest;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PlaceQuest extends ItemQuest {

    public PlaceQuest(BasicQuest base) {
        super(base);
    }

    @Override
    public String getType() {
        return "PLACE";
    }

    @Override
    public boolean canProgress(Event provided) {
        if (provided instanceof BlockPlaceEvent event) {
            final Block block = event.getBlock();
            return super.isRequiredItem(new ItemStack(block.getType()));
        }

        return false;
    }
}
