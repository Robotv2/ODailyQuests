package com.ordwen.odailyquests.quests.types;


import com.ordwen.odailyquests.api.quests.IQuest;
import com.ordwen.odailyquests.quests.player.progression.PlayerProgressor;
import com.ordwen.odailyquests.quests.types.shared.BasicQuest;
import com.ordwen.odailyquests.rewards.Reward;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class AbstractQuest extends PlayerProgressor implements IQuest {

    final int questIndex;
    final String questName;
    final String categoryName;
    final List<String> questDesc;
    final String questType;
    final ItemStack menuItem;
    final ItemStack achievedItem;
    final int amountRequired;
    final Reward reward;
    final List<String> requiredWorlds;
    final boolean isUsingPlaceholders;
    final String resetService;

    /**
     * Quest constructor.
     *
     * @param questName      name of the quest.
     * @param questDesc      description of the quest.
     * @param questType      type of the quest.
     * @param amountRequired required amount of the item.
     * @param reward         reward of the quest.
     */
    protected AbstractQuest(int questIndex, String questName, String categoryName, List<String> questDesc, String questType, ItemStack menuItem, ItemStack achievedItem, int amountRequired, Reward reward, List<String> requiredWorlds, boolean isUsingPlaceholders, String resetService) {
        this.questIndex = questIndex;
        this.questName = questName;
        this.categoryName = categoryName;
        this.questDesc = questDesc;
        this.questType = questType;
        this.menuItem = menuItem;
        this.achievedItem = achievedItem;
        this.amountRequired = amountRequired;
        this.reward = reward;
        this.requiredWorlds = requiredWorlds;
        this.isUsingPlaceholders = isUsingPlaceholders;
        this.resetService = resetService;
    }

    /**
     * Quest constructor.
     *
     * @param basicQuest quest base.
     */
    protected AbstractQuest(BasicQuest basicQuest) {
        this.questIndex = basicQuest.getQuestIndex();
        this.questName = basicQuest.getQuestName();
        this.categoryName = basicQuest.getCategoryName();
        this.questDesc = basicQuest.getQuestDesc();
        this.questType = basicQuest.getQuestType();
        this.menuItem = basicQuest.getMenuItem();
        this.achievedItem = basicQuest.getAchievedItem();
        this.amountRequired = basicQuest.getAmountRequired();
        this.reward = basicQuest.getReward();
        this.requiredWorlds = basicQuest.getRequiredWorlds();
        this.isUsingPlaceholders = basicQuest.isUsingPlaceholders();
        this.resetService = basicQuest.getResetService();
    }

    /**
     * Get index of quest.
     *
     * @return quest index.
     */
    public int getQuestIndex() {
        return this.questIndex;
    }

    /**
     * Get the type of quest.
     *
     * @return the type of the quest.
     */
    public String getQuestType() {
        return this.questType;
    }

    /**
     * Get the name of the quest.
     *
     * @return quest name.
     */
    public String getQuestName() {
        return this.questName;
    }

    /**
     * Get the name of the category.
     *
     * @return category name.
     */
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * Get menu item.
     *
     * @return menu item.
     */
    public ItemStack getMenuItem() {
        return this.menuItem;
    }

    /**
     * Get achieved item.
     *
     * @return achieved item.
     */
    public ItemStack getAchievedItem() {
        return this.achievedItem;
    }

    /**
     * Get the description of the quest.
     *
     * @return quest description.
     */
    public List<String> getQuestDesc() {
        return this.questDesc;
    }

    /**
     * Get the amount required by the quest.
     *
     * @return quest amount-required.
     */
    public int getAmountRequired() {
        return this.amountRequired;
    }

    /**
     * Get the reward of the quest.
     *
     * @return quest reward.
     */
    public Reward getReward() {
        return this.reward;
    }

    /**
     * Get the required worlds of the quest.
     *
     * @return quest required worlds.
     */
    public List<String> getRequiredWorlds() {
        return this.requiredWorlds;
    }

    /**
     * Get whether the quest is using placeholders.
     *
     * @return quest isUsingPlaceholders.
     */
    public boolean isUsingPlaceholders() {
        return this.isUsingPlaceholders;
    }

    /**
     * Get the name of the reset service attached to this quest
     *
     * @return quest reset service
     */
    public String getResetService() {
        return this.resetService;
    }
}
