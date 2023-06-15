package baf.katana

import net.fabricmc.api.ModInitializer
import net.minecraft.item.Item
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Identifier
import net.minecraft.registry.Registry
import net.minecraft.item.ToolMaterials
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries

class Bafmod1 : ModInitializer {
	override fun onInitialize() {

			FabricLoader.getInstance().getModContainer("bafmod1").ifPresent { modContainer ->
				ResourceManagerHelper.registerBuiltinResourcePack(
					Identifier("baf-katana-3d"),
					modContainer,
					ResourcePackActivationType.NORMAL
				)
			}

		// Create a new ToolMaterial for the katana using iron as the base material
		val katanaMaterial: ToolMaterial = object : ToolMaterial {
			override fun getDurability() = ToolMaterials.IRON.durability
			override fun getMiningSpeedMultiplier() = ToolMaterials.IRON.miningSpeedMultiplier
			override fun getAttackDamage() = ToolMaterials.IRON.attackDamage
			override fun getMiningLevel() = ToolMaterials.IRON.miningLevel
			override fun getEnchantability() = ToolMaterials.IRON.enchantability
			override fun getRepairIngredient() = ToolMaterials.IRON.repairIngredient
		}

		// Create a new Item instance for the katana
		val katana: Item = SwordItem(katanaMaterial, 3, -1.5f, Item.Settings())

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { entries -> entries.add(katana) }


		// Register the katana item with a unique identifier
		Registry.register(Registries.ITEM, Identifier("bafmod1", "katana"), katana)
	}
}
