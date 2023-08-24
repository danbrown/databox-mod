package com.example.examplemod.content.carver;

import java.util.function.Function;

import org.apache.commons.lang3.mutable.MutableBoolean;

import com.example.examplemod.init.DataboxBlocks;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.material.Fluids;

public class CustomCaveWorldCarver extends CaveWorldCarver {
    public CustomCaveWorldCarver(Codec<CaveCarverConfiguration> configCodec) {
        super(configCodec);
        liquids = ImmutableSet.of(Fluids.WATER);
    }

    @Override
    protected float getThickness(RandomSource random) {
        return super.getThickness(random) * 3;
    }

    @Override
    public boolean carveEllipsoid(
            CarvingContext context,
            CaveCarverConfiguration config,
            ChunkAccess chunk,
            Function<BlockPos, Holder<Biome>> biomeAccessor,
            Aquifer aquifer,
            double x,
            double y,
            double z,
            double horizontalRadius,
            double verticalRadius,
            CarvingMask carvingMask,
            CarveSkipChecker skipChecker) {
        ChunkPos chunkPos = chunk.getPos();
        double middleX = chunkPos.getMiddleBlockX() + 0.5;
        double middleZ = chunkPos.getMiddleBlockZ() + 0.5;
        double d2 = 16.0 + horizontalRadius * 2.0;

        if (!(Math.abs(x - middleX) > d2) && !(Math.abs(z - middleZ) > d2)) {
            int minX = chunkPos.getMinBlockX();
            int minZ = chunkPos.getMinBlockZ();
            int k = Math.max((Mth.floor(x - horizontalRadius) - minX - 1), 0);
            int l = Math.min((Mth.floor(x + horizontalRadius) - minX), 15);
            int i1 = Math.max((Mth.floor(y - verticalRadius) - 1), (context.getMinGenY() + 1));
            int j1 = chunk.isUpgrading() ? 0 : 7;
            int k1 = Math.min((Mth.floor(y + verticalRadius) + 1),
                    (context.getMinGenY() + context.getGenDepth() - 1 - j1));
            int l1 = Math.max((Mth.floor(z - horizontalRadius) - minZ - 1), 0);
            int i2 = Math.min((Mth.floor(z + horizontalRadius) - minZ), 15);

            if (hasDisallowedLiquid(chunk, k, l, i1, k1, l1, i2)) {
                return false;
            } else {
                boolean flag = false;
                MutableBlockPos mutableBlockPos = new MutableBlockPos();
                MutableBlockPos mutableBlockPos1 = new MutableBlockPos();

                for (int j2 = k; j2 <= l; j2++) {
                    int k2 = chunkPos.getBlockX(j2);
                    double d3 = (k2 + 0.5 - x) / horizontalRadius;

                    for (int l2 = l1; l2 <= i2; l2++) {
                        int i3 = chunkPos.getBlockZ(l2);
                        double d4 = (i3 + 0.5 - z) / horizontalRadius;

                        if (!(d3 * d3 + d4 * d4 >= 1.0)) {
                            MutableBoolean mutableBoolean = new MutableBoolean(false);

                            for (int j3 = k1; j3 >= i1 + 1; j3--) {
                                double d5 = (j3 - 0.5 - y) / verticalRadius;

                                if (!skipChecker.shouldSkip(context, d3, d5, d4, j3)
                                        && !carvingMask.get(j2, j3, l2)) {

                                    carvingMask.set(j2, j3, l2);

                                    mutableBlockPos.set(k2, j3, i3);

                                    flag |= carveBlock(
                                            context,
                                            config,
                                            chunk,
                                            biomeAccessor,
                                            carvingMask,
                                            mutableBlockPos,
                                            mutableBlockPos1,
                                            aquifer,
                                            mutableBoolean);
                                }
                            }
                        }
                    }
                }
                return flag;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean carveBlock(
            CarvingContext context,
            CaveCarverConfiguration config,
            ChunkAccess chunk,
            Function<BlockPos, Holder<Biome>> biomeAccessor,
            CarvingMask carvingMask,
            MutableBlockPos pos,
            MutableBlockPos checkPos,
            Aquifer aquifer,
            MutableBoolean reachedSurface) {
        BlockState chunkState = chunk.getBlockState(pos);

        // TODO: Replace with Tuff variants
        if (chunkState.is(DataboxBlocks.ADAMANTIUM_DEBRIS.get()) ||
                chunkState.is(DataboxBlocks.ADAMANTIUM_DEBRIS.get()) ||
                chunkState.is(DataboxBlocks.ADAMANTIUM_DEBRIS.get())) {
            reachedSurface.setTrue();
        }

        if (!canReplaceBlock(config, chunkState)) {
            return false;
        } else {
            BlockState carveState = getCarveState(context, config, pos);

            if (carveState == null) {
                return false;
            } else {
                chunk.setBlockState(pos, carveState, false);

                if (aquifer.shouldScheduleFluidUpdate() && !carveState.getFluidState().isEmpty()) {
                    chunk.markPosForPostprocessing(pos);
                }

                if (reachedSurface.isTrue()) {
                    checkPos.setWithOffset(pos, Direction.DOWN);

                    // TODO: Replace with soil variants
                    if (chunk.getBlockState(checkPos).is(Blocks.DIRT)) {
                        context.topMaterial(biomeAccessor, chunk, checkPos, !carveState.getFluidState().isEmpty())
                                .ifPresent(state -> {
                                    chunk.setBlockState(checkPos, state, false);

                                    if (!state.getFluidState().isEmpty()) {
                                        chunk.markPosForPostprocessing(checkPos);
                                    }
                                });
                    }
                }

                return true;
            }
        }
    }

    private BlockState getCarveState(CarvingContext context, CaveCarverConfiguration config, BlockPos pos) {
        if (pos.getY() <= config.lavaLevel.resolveY(context)) {
            // TODO: Replace with World Water
            return Fluids.WATER.defaultFluidState().createLegacyBlock();
        } else {
            return CAVE_AIR;
        }
    }

    protected boolean hasDisallowedLiquid(ChunkAccess chunk, int minX, int maxX, int minY, int maxY, int minZ,
            int maxZ) {
        ChunkPos chunkPos = chunk.getPos();
        int minBlockX = chunkPos.getMinBlockX();
        int minBlockZ = chunkPos.getMinBlockZ();
        MutableBlockPos mutablePos = new MutableBlockPos();

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                int y = minY - 1;
                while (y <= maxY + 1) {
                    mutablePos.set(minBlockX + x, y, minBlockZ + z);
                    if (liquids.contains(chunk.getFluidState(mutablePos).getType())) {
                        return true;
                    }
                    if (y != maxY + 1 && !isEdge(x, z, minX, maxX, minZ, maxZ)) {
                        y = maxY;
                    }
                    y++;
                }
            }
        }
        return false;
    }

    private static boolean isEdge(int x, int z, int minX, int maxX, int minZ, int maxZ) {
        return x == minX || x == maxX || z == minZ || z == maxZ;
    }
}
