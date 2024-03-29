package de.mpw.betterbeacon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.World;

public class BlockBetterBeacon extends BlockContainer {

	/**
	 * @param par1
	 *            Block id
	 */
	public BlockBetterBeacon(int par1) {
		super(par1, Material.glass);
		this.setHardness(3.0F);
		setCreativeTab(CreativeTabs.tabMisc);
		setLightValue(1.0F);
		setUnlocalizedName("betterBeacon");

	}

	/**
	 * Creates the Tile Entity for the Block
	 */
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityBetterBeacon();
	}

	/**
	 * Open the Gui of the Better Beacon
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		TileEntityBetterBeacon debug = (TileEntityBetterBeacon) tile_entity;
		System.out.println("Level"+debug.getLevels()+"Effect:"+debug.getEffect()+"Remote:"+world.isRemote);
		if (tile_entity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(BetterBeacon.instance, BetterBeacon.BetterBeaconGuiId, world, x, y, z);
		world.notifyBlockChange(x, y, z, blockID);
		return true;
		/*
		 * return super.onBlockActivated(world, x, y, z, player, par6, par7,
		 * par8, par9);
		 */
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;

	}

	public int getRenderType() {
		// return 34;
		return -1;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

}
