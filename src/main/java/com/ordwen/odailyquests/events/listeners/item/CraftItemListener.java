package com.ordwen.odailyquests.events.listeners.item;

import com.ordwen.odailyquests.quests.QuestType;
import com.ordwen.odailyquests.quests.player.progression.checkers.AbstractItemChecker;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CraftItemListener extends AbstractItemChecker implements Listener {

    @EventHandler
    public void onCraftItemEvent(CraftItemEvent event) {

        ItemStack test;

        if (event.getRecipe() instanceof ComplexRecipe complexRecipe) {
            switch (complexRecipe.getKey().getKey()) {
                case "REPAIR_ITEM":
                case "ARMOR_DYE":
                    return;
            }
            test = new ItemStack(Material.valueOf(complexRecipe.getKey().getKey().toUpperCase()));
        } else test = event.getRecipe().getResult().clone();

        ClickType click = event.getClick();

        int recipeAmount = test.getAmount();

        switch (click) {
            case NUMBER_KEY:
                if (event.getWhoClicked().getInventory().getItem(event.getHotbarButton()) != null)
                    recipeAmount = 0;
                break;
            case DROP:
            case CONTROL_DROP:
                ItemStack cursor = event.getCursor();
                if (!(cursor == null || cursor.getType() == Material.AIR))
                    recipeAmount = 0;
                break;
            case SHIFT_RIGHT:
            case SHIFT_LEFT:
                if (recipeAmount == 0)
                    break;
                int maxCraftable = getMaxCraftAmount(event.getInventory());
                int capacity = fits(test, event.getView().getBottomInventory());

                if (capacity < maxCraftable)
                    maxCraftable = ((capacity + recipeAmount - 1) / recipeAmount) * recipeAmount;

                recipeAmount = maxCraftable;
                break;
        }

        if (recipeAmount == 0)
            return;

        test.setAmount(recipeAmount);
        setPlayerQuestProgression((Player) event.getWhoClicked(), test, test.getAmount(), QuestType.CRAFT);
    }

    /**
     *
     * @param stack
     * @param inv
     * @return
     */
    private int fits(ItemStack stack, Inventory inv) {
        ItemStack[] contents = inv.getContents();
        int result = 0;

        for (ItemStack is : contents)
            if (is == null)
                result += stack.getMaxStackSize();
            else if (is.isSimilar(stack))
                result += Math.max(stack.getMaxStackSize() - is.getAmount(), 0);

        return result;
    }

    /**
     * Returns the maximum amount of items that can be crafted in the given inventory.
     * @param inv the inventory to check.
     * @return the maximum.
     */
    private int getMaxCraftAmount(CraftingInventory inv) {
        if (inv.getResult() == null)
            return 0;

        int resultCount = inv.getResult().getAmount();
        int materialCount = Integer.MAX_VALUE;

        for (ItemStack is : inv.getMatrix())
            if (is != null && is.getAmount() < materialCount)
                materialCount = is.getAmount();

        return resultCount * materialCount;
    }
}
