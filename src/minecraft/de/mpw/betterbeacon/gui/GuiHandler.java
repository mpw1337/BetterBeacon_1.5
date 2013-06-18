package de.mpw.betterbeacon.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import de.mpw.betterbeacon.TileEntityBetterBeacon;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		if (tile_entity instanceof TileEntityBetterBeacon) {
			return new ContainerBetterBeacon((TileEntityBetterBeacon) tile_entity, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		if (tile_entity instanceof TileEntityBetterBeacon) {
			return new GuiBetterBeacon(player.inventory, (TileEntityBetterBeacon) tile_entity);
		}
		return null;
	}

}
