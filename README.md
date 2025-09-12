# SmartRedstone
Utilities for the Minecraft redstone engineer.  

![demo](https://github.com/ZephyrBD/SmartRedstone/blob/master/demo/demo1.gif)

## Requirements
Paper 1.21.8 (Tested)

## Features
 - /smartredstone reload

   - permission: smartredstone.admin

## Usage method

- Left - click the block while sneaking and handing bamboo.

## Thanks

- [RedstoneHelper2](https://github.com/Ac1drainn/RedstoneHelper2)
- JetBrains IDEA

## Config

```yaml
tags:
  flipping:
    DOORS:
      enabled: true       # Whether doors can be rotated
    TRAPDOORS:
      enabled: true       # Whether trapdoors can be rotated
    BUTTONS:
      enabled: true       # Whether buttons can be rotated
    FENCE_GATES:
      enabled: true       # Whether fence gates can be rotated

blocks:
  flipping:
    REPEATER:
      enabled: true       # Whether repeaters can be rotated (4 directions)
    COMPARATOR:
      enabled: true       # Whether comparators can be rotated (4 directions)
    OBSERVER:
      enabled: true       # Whether observers can be rotated (6 directions)
    PISTON:
      enabled: true       # Whether pistons can be rotated (6 directions)
    STICKY_PISTON:
      enabled: true       # Whether sticky pistons can be rotated (6 directions)
    HOPPER:
      enabled: true       # Whether hoppers can be rotated (5 directions)
    DISPENSER:
      enabled: true       # Whether dispensers can be rotated (6 directions)
    DROPPER:
      enabled: true       # Whether droppers can be rotated (6 directions)

translation:
  reload: "§a[SmartRedstone] Configuration is reloaded!" # Message shown when using /smartredstone reload
  message: "&aThe block has been rotated!"               # ActionBar message when a block is rotated
```

