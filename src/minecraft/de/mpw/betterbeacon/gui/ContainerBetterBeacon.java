package de.mpw.betterbeacon.gui;

import de.mpw.betterbeacon.TileEntityBetterBeacon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBetterBeacon extends Container {

	TileEntityBetterBeacon theBetterBeacon;

	protected final SlotBetterBeacon beaconSlot;

	public TileEntityBetterBeacon getBetterBeacon() {
		return theBetterBeacon;
	}

	public ContainerBetterBeacon(TileEntityBetterBeacon tile_entity, InventoryPlayer inventory) {
		this.theBetterBeacon = tile_entity;
		addSlotToContainer(this.beaconSlot = new SlotBetterBeacon(this, tile_entity, 0, 136, 110));
		bindPlayerInventory(inventory);
	}

	/**
	 * Adds the player inventory to the beacon container
	 * 
	 * @param inventory
	 *            of the player
	 */
	protected void bindPlayerInventory(InventoryPlayer inventory) {
		short short1 = 173;
		byte b0 = 48;
		int i;
		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, b0 + j * 18, short1 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventory, i, b0 + i * 18, 58 + short1));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @return if it can interact with player
	 * 
	 * @see
	 * net.minecraft.inventory.Container#canInteractWith(net.minecraft.entity
	 * .player.EntityPlayer)
	 */
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return theBetterBeacon.isUseableByPlayer(entityplayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 0) {
				if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(itemstack1) && itemstack1.stackSize == 1) {
				if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
					return null;
				}
			} else if (par2 >= 1 && par2 < 28) {
				if (!this.mergeItemStack(itemstack1, 28, 37, false)) {
					return null;
				}
			} else if (par2 >= 28 && par2 < 37) {
				if (!this.mergeItemStack(itemstack1, 1, 28, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 1, 37, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

}
