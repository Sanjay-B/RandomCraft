package com.turnereison.wirelessredstone.blocks;

import com.turnereison.wirelessredstone.blocks.entities.ReceiverEntity;
import com.turnereison.wirelessredstone.blocks.entities.TransmitterEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegisterer {
    private static final String NAMESPACE = "wirelessredstone";

    // Transmitter
    public static final Transmitter TRANSMITTER = new Transmitter(FabricBlockSettings.of(Material.METAL).hardness(2.0f));
    public static BlockEntityType<TransmitterEntity> TRANSMITTER_ENTITY_TYPE;

    // Receiver
    public static final Receiver RECEIVER = new Receiver(FabricBlockSettings.of(Material.METAL).hardness(2.0f));
    public static BlockEntityType<ReceiverEntity> RECEIVER_ENTITY_TYPE;

    public static void register() {

        // Transmitter
        Registry.register(
                Registry.BLOCK,
                new Identifier(NAMESPACE, "transmitter"),
                TRANSMITTER
        );

        Registry.register(
                Registry.ITEM,
                new Identifier(NAMESPACE, "transmitter"),
                new BlockItem(TRANSMITTER, new FabricItemSettings().group(ItemGroup.REDSTONE))
        );

        TRANSMITTER_ENTITY_TYPE = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NAMESPACE, "transmitter_entity"),
                FabricBlockEntityTypeBuilder
                        .create(TransmitterEntity::new, TRANSMITTER)
                        .build(null)
        );

        // Receiver
        Registry.register(
                Registry.BLOCK,
                new Identifier(NAMESPACE, "receiver"),
                RECEIVER
        );

        Registry.register(
                Registry.ITEM,
                new Identifier(NAMESPACE, "receiver"),
                new BlockItem(RECEIVER, new FabricItemSettings().group(ItemGroup.REDSTONE))
        );

        RECEIVER_ENTITY_TYPE = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NAMESPACE, "receiver_entity"),
                FabricBlockEntityTypeBuilder
                    .create(ReceiverEntity::new, RECEIVER)
                    .build(null)
        );
    }
}
