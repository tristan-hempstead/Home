
package net.mcreator.infecticraft.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.util.Rotation;
import net.minecraft.util.Direction;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.infecticraft.InfecticraftModElements;

import java.util.List;
import java.util.Collections;

@InfecticraftModElements.ModElement.Tag
public class WitherBoneBlockBlock extends InfecticraftModElements.ModElement {
	@ObjectHolder("infecticraft:wither_bone_block")
	public static final Block block = null;
	public WitherBoneBlockBlock(InfecticraftModElements instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public static final DirectionProperty FACING = DirectionalBlock.FACING;
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.WOOD).hardnessAndResistance(2f, 2f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.PICKAXE));
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH));
			setRegistryName("wither_bone_block");
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING);
		}

		@Override
		public BlockState rotate(BlockState state, Rotation rot) {
			if (rot == Rotation.CLOCKWISE_90 || rot == Rotation.COUNTERCLOCKWISE_90) {
				if ((Direction) state.get(FACING) == Direction.WEST || (Direction) state.get(FACING) == Direction.EAST) {
					return state.with(FACING, Direction.UP);
				} else if ((Direction) state.get(FACING) == Direction.UP || (Direction) state.get(FACING) == Direction.DOWN) {
					return state.with(FACING, Direction.WEST);
				}
			}
			return state;
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			Direction facing = context.getFace();
			if (facing == Direction.WEST || facing == Direction.EAST)
				facing = Direction.UP;
			else if (facing == Direction.NORTH || facing == Direction.SOUTH)
				facing = Direction.EAST;
			else
				facing = Direction.SOUTH;
			return this.getDefaultState().with(FACING, facing);
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
