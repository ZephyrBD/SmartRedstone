package zbd.smartRedstone.listeners;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import zbd.smartRedstone.SmartRedstone;
import zbd.smartRedstone.util.Flippers;

public class BlockKicked implements Listener {
    SmartRedstone plugin;
    private final FileConfiguration config;

    public BlockKicked(SmartRedstone plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // 必须潜行并且右键点击方块
        if (!event.getPlayer().isSneaking()) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (config.getBoolean("tools.use_bamboo") && event.getPlayer().getInventory().getItemInMainHand().getType() != Material.BAMBOO) return;
        event.setCancelled(true);
        Block block = event.getClickedBlock();
        if (block == null) return;
        // 4-方向方块Tag
        if (Tag.DOORS.isTagged(block.getType()) ||
                Tag.TRAPDOORS.isTagged(block.getType()) ||
                Tag.BUTTONS.isTagged(block.getType()) ||
                Tag.FENCE_GATES.isTagged(block.getType())) {

            if (Tag.DOORS.isTagged(block.getType()) && !config.getBoolean("tags.flipping.DOORS.enabled")) return;
            if (Tag.TRAPDOORS.isTagged(block.getType()) && !config.getBoolean("tags.flipping.TRAPDOORS.enabled")) return;
            if (Tag.BUTTONS.isTagged(block.getType()) && !config.getBoolean("tags.flipping.BUTTONS.enabled")) return;
            if (Tag.FENCE_GATES.isTagged(block.getType()) && !config.getBoolean("tags.flipping.FENCE_GATES.enabled")) return;

            Flippers.flipFourFace(block,event.getPlayer(),plugin);
            return;
        }

        // 4,5,6-方向方块
        switch (block.getType()) {
            case REPEATER,COMPARATOR -> {
                String configKey = "blocks.flipping." + block.getType().name();
                if (!config.getBoolean(configKey + ".enabled") || !config.getBoolean("tools.use_bamboo")) return;
                Flippers.flipFourFace(block,event.getPlayer(),plugin);
            }
            case HOPPER-> {
                if (!config.getBoolean("blocks.flipping.HOPPER.enabled")) return;
                Flippers.flipFiveFace(block,event.getPlayer(),plugin);
            }
            case OBSERVER, PISTON, STICKY_PISTON, DISPENSER, DROPPER -> {
                String configKey = "blocks.flipping." + block.getType().name();
                if (!config.getBoolean(configKey + ".enabled")) return;
                Flippers.flipSixFace(block,event.getPlayer(),plugin);
            }
            default -> {
            }
        }
    }
}
