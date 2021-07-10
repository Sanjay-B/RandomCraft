package com.turnereison.wirelessredstone.blocks;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegisterer {
    private static final String NAMESPACE = "wirelessredstone";

    public static final Transmitter TRANSMITTER = new Transmitter(FabricBlockSettings.of(Material.METAL).hardness(2.0f));

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "transmitter"), TRANSMITTER);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "transmitter"), new BlockItem(TRANSMITTER, new FabricItemSettings().group(ItemGroup.REDSTONE)));
    }
}
