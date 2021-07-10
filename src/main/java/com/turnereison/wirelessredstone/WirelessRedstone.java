package com.turnereison.wirelessredstone;

import com.turnereison.wirelessredstone.blocks.BlockRegisterer;
import net.fabricmc.api.ModInitializer;

public class WirelessRedstone implements ModInitializer {
    @Override
    public void onInitialize() {
        BlockRegisterer.register();
    }
}
