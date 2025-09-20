package com.zbd.SmartRedStone.util;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import com.zbd.SmartRedStone.SmartRedstone;

public class Flippers {

    // 翻转四个方向的方块（NORTH, EAST, SOUTH, WEST）
    public static void flipFourFace(Block block, Player player, SmartRedstone plugin) {
        if (!(block.getBlockData() instanceof Directional directional)) return;

        BlockFace currentFace = directional.getFacing();
        BlockFace newFace = switch (currentFace) {
            case NORTH -> BlockFace.EAST;
            case EAST -> BlockFace.SOUTH;
            case SOUTH -> BlockFace.WEST;
            case WEST -> BlockFace.NORTH;
            default -> currentFace; // UP/DOWN 保持不变
        };
        directional.setFacing(newFace);
        block.setBlockData(directional);

        // 给玩家提示
        String msg = plugin.getConfig().getString("translation.message", "&aThe block has been rotated!");
        player.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize(msg));
    }

    public static void flipFiveFace(Block block, Player player, SmartRedstone plugin) {
        if (!(block.getBlockData() instanceof Directional directional)) return;

        BlockFace currentFace = directional.getFacing();
        BlockFace newFace = switch (currentFace) {
            case NORTH -> BlockFace.EAST;
            case EAST -> BlockFace.SOUTH;
            case SOUTH -> BlockFace.WEST;
            case WEST -> BlockFace.DOWN;
            case DOWN -> BlockFace.NORTH;
            default -> currentFace;
        };

        directional.setFacing(newFace);
        block.setBlockData(directional);

        // 给玩家提示
        String msg = plugin.getConfig().getString("translation.message", "&aThe block has been rotated!");
        player.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize(msg));
    }

    // 翻转六个方向的方块（NORTH, EAST, SOUTH, WEST, UP, DOWN）
    public static void flipSixFace(Block block, Player player, SmartRedstone plugin) {
        if (!(block.getBlockData() instanceof Directional directional)) return;

        BlockFace currentFace = directional.getFacing();
        BlockFace newFace = switch (currentFace) {
            case NORTH -> BlockFace.EAST;
            case EAST -> BlockFace.SOUTH;
            case SOUTH -> BlockFace.WEST;
            case WEST -> BlockFace.UP;
            case UP -> BlockFace.DOWN;
            case DOWN -> BlockFace.NORTH;
            default -> currentFace;
        };

        directional.setFacing(newFace);
        block.setBlockData(directional);

        // 给玩家提示
        String msg = plugin.getConfig().getString("translation.message", "&aThe block has been rotated!");
        player.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize(msg));
    }
}
