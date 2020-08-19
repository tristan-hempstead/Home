
package net.mcreator.infecticraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.infecticraft.InfecticraftModElements;

@InfecticraftModElements.ModElement.Tag
public class FleshItem extends InfecticraftModElements.ModElement {
	@ObjectHolder("infecticraft:flesh")
	public static final Item block = null;
	public FleshItem(InfecticraftModElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).food((new Food.Builder()).hunger(4).saturation(1.8f).meat().build()));
			setRegistryName("flesh");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
