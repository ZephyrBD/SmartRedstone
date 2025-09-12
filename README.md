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
      enabled: true
    TRAPDOORS:
      enabled: true
    BUTTONS:
      enabled: true
    FENCE_GATES:
      enabled: true

blocks:
  flipping:
    REPEATER:
      enabled: true
    COMPARATOR:
      enabled: true
    OBSERVER:
      enabled: true
    PISTON:
      enabled: true
    STICKY_PISTON:
      enabled: true
    HOPPER:
      enabled: true
    DISPENSER:
      enabled: true
    DROPPER:
      enabled: true

tools:
  # WARRING: Disabling the tool will result in the inability to flip comparators and repeaters.
  use_bamboo: true

translation:
  reload: "§a[SmartRedstone] Configuration is reloaded!"
  message: "&aThe block has been rotated!"
```

