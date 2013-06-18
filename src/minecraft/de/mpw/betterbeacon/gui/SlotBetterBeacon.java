package de.mpw.betterbeacon.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotBetterBeacon extends Slot {
	final ContainerBetterBeacon beaconContainer;

	public SlotBetterBeacon(ContainerBetterBeacon Container, IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.beaconContainer = Container;

	}

	public boolean isItemValid(ItemStack itemstack) {
		return beaconContainer.theBetterBeacon.isStackValidForSlot(0, itemstack);
	}

}
