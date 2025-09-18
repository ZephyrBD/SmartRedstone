package zbd.smartRedstone.listeners;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import zbd.smartRedstone.SmartRedstone;
import zbd.smartRedstone.util.Flippers;

import java.util.Objects;

public class BlockKicked implements Listener {
    SmartRedstone plugin;
    private final FileConfiguration config;

    public BlockKicked(@NotNull SmartRedstone plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    private @NotNull String getToolConfig() {
        String value = plugin.getConfig().getString("tools");
        return value != null ? value : "HAND";
    }
    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent event) {
        // 必须潜行并且左键点击方块
        if (!event.getPlayer().isSneaking()) return;
        if (event.getAction() != Action.LEFT_CLICK_BLOCK) return;

        String ConfigTool = getToolConfig();

        // 当配置不是"HAND"时才进行工具检查
        if (!Objects.equals(ConfigTool, "HAND")) {
            try {
                // 转换材质并处理可能的无效值
                Material configuredMaterial = Material.valueOf(ConfigTool);
                // 检查玩家主手物品
                var mainHandItem = event.getPlayer().getInventory().getItemInMainHand();
                if (mainHandItem.getType() != configuredMaterial) {
                    return;
                }
            } catch (IllegalArgumentException e) {
                // 处理配置中材质名称无效的情况
                event.getPlayer().sendMessage(Objects.requireNonNull(config.getString("translation.incorrect_tool","Tool is incorrect!")));
                plugin.getLogger().warning(config.getString("translation.incorrect_tool","Tool is incorrect!"));
                return;
            }
        }


        Block block = event.getClickedBlock();
        // 4-方向方块Tag
        if (Tag.DOORS.isTagged(block.getType()) ||
                Tag.TRAPDOORS.isTagged(block.getType()) ||
                Tag.BUTTONS.isTagged(block.getType()) ||
                Tag.FENCE_GATES.isTagged(block.getType())) {

            if (Tag.DOORS.isTagged(block.getType()) && !config.getBoolean("tags.flipping.DOORS")) return;
            if (Tag.TRAPDOORS.isTagged(block.getType()) && !config.getBoolean("tags.flipping.TRAPDOORS")) return;
            if (Tag.BUTTONS.isTagged(block.getType()) && !config.getBoolean("tags.flipping.BUTTONS")) return;
            if (Tag.FENCE_GATES.isTagged(block.getType()) && !config.getBoolean("tags.flipping.FENCE_GATES")) return;

            Flippers.flipFourFace(block,event.getPlayer(),plugin);
            event.setCancelled(true);
            return;
        }

        // 4,5,6-方向方块
        switch (block.getType()) {
            case REPEATER,COMPARATOR -> {
                String configKey = "blocks.flipping." + block.getType().name();
                if (!config.getBoolean(configKey)) return;
                Flippers.flipFourFace(block,event.getPlayer(),plugin);
                event.setCancelled(true);
            }
            case HOPPER-> {
                if (!config.getBoolean("blocks.flipping.HOPPER")) return;
                Flippers.flipFiveFace(block,event.getPlayer(),plugin);
                event.setCancelled(true);
            }
            case OBSERVER, PISTON, STICKY_PISTON, DISPENSER, DROPPER -> {
                String configKey = "blocks.flipping." + block.getType().name();
                if (!config.getBoolean(configKey)) return;
                Flippers.flipSixFace(block,event.getPlayer(),plugin);
                event.setCancelled(true);
            }
            default -> {
            }
        }

    }
}
