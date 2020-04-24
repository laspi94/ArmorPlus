package gg.steve.mc.ap.armor;

import gg.steve.mc.ap.gui.AbstractGui;
import gg.steve.mc.ap.utils.GuiItemUtil;
import org.bukkit.configuration.ConfigurationSection;

public class SetGui extends AbstractGui {

    /**
     * Constructor the create a new Gui
     */
    public SetGui(ConfigurationSection section, Set set) {
        super(section, section.getString("type"), section.getInt("size"));
        for (String entry : section.getKeys(false)) {
            try {
                Integer.parseInt(entry);
            } catch (Exception e) {
                continue;
            }
            setItemInSlot(section.getInt(entry + ".slot"), GuiItemUtil.createItem(section, entry, set), player -> {
                switch (section.getString(entry + ".action")) {
                    case "none":
                        break;
                    case "close":
                        player.closeInventory();
                        break;
                    default:
                        GuiItemUtil.purchase(section, entry, player, set);
                        break;
                }
            });
        }
    }
}