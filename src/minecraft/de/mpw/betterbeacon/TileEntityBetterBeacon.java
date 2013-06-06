package de.mpw.betterbeacon;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBetterBeacon extends TileEntity implements IInventory {
	public static final Potion[][] effectsList = new Potion[][] { { Potion.moveSpeed, Potion.jump, Potion.digSpeed }, { Potion.damageBoost },
			{ Potion.regeneration, Potion.resistance }, { Potion.nightVision, Potion.fireResistance, Potion.invisibility } };
	public static final int potionTime = 180;

	private boolean isBeaconActive = false;
	protected int levels = 1;
	protected int effect;
	protected int effectstrength = 2;
	protected ItemStack payment;
	// which mobs should be affected
	protected boolean playereffect = true;
	protected boolean mobeffect = true;
	protected boolean animaleffect = true;

	public TileEntityBetterBeacon() {
		this.isBeaconActive = true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.getTotalWorldTime() % 80L == 0L) {
			this.updateState();
			addEffects();
		}
		this.isBeaconActive = true;
	}

	// @Override
	public int getLevels() {
		return this.levels;
	}

	// @Override
	@SideOnly(Side.CLIENT)
	public void setLevels(int level) {
		this.levels = level;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcom = new NBTTagCompound();
		this.writeToNBT(nbttagcom);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 3, nbttagcom);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		this.levels = par1nbtTagCompound.getInteger("Levels");
		this.effect = par1nbtTagCompound.getInteger("Effect");
		this.effectstrength = par1nbtTagCompound.getInteger("EffectStrength");
		this.playereffect = par1nbtTagCompound.getBoolean("PlayerEffect");
		this.mobeffect = par1nbtTagCompound.getBoolean("MobEffect");
		this.animaleffect = par1nbtTagCompound.getBoolean("AnimalEffect");
		if(payment == null){
			this.payment = new ItemStack(0,1,0);
		}
		this.payment.readFromNBT(par1nbtTagCompound.getCompoundTag("payment"));
		if(this.payment.itemID == 0){
			this.payment = null;
		}
		/*this.payment = new ItemStack(par1nbtTagCompound.getInteger("Payment"),1,0);
		if(this.payment.itemID<0){payment.itemID = 1;}*/
		//this.setInventorySlotContents(0, new ItemStack(256, 1, 0));
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("Effect", this.effect);
		par1nbtTagCompound.setInteger("Levels", this.levels);
		par1nbtTagCompound.setInteger("EffectStrength", this.effectstrength);
		par1nbtTagCompound.setBoolean("PlayerEffect", this.playereffect);
		par1nbtTagCompound.setBoolean("MobEffect", this.mobeffect);
		par1nbtTagCompound.setBoolean("AnimalEffect", this.animaleffect);
		if(this.payment != null){
			par1nbtTagCompound.setInteger("Payment", this.payment.itemID);	
			par1nbtTagCompound.setCompoundTag("payment", payment.writeToNBT(new NBTTagCompound()));
		}else{
			//par1nbtTagCompound.setInteger("Payment", 0);
		}

	}

	@SideOnly(Side.CLIENT)
	public final double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	protected void updateState() {
		if (!this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) {
			this.isBeaconActive = false;
			this.levels = 0;
		} else {
			this.isBeaconActive = true;
			this.levels = 0;
			for (int i = 1; i <= 4; this.levels = i++) {
				int j = this.yCoord - i;
				if (j <= 0) {
					break;
				}
				boolean flag = true;
				for (int k = this.xCoord - i; k <= this.xCoord + i && flag; ++k) {
					for (int l = this.zCoord - i; l <= this.zCoord + i; ++l) {
						int blockId = this.worldObj.getBlockId(k, j, l);
						Block block = Block.blocksList[blockId];
						if (block == null || !block.isBeaconBase(worldObj, k, j, l, xCoord, yCoord, zCoord)) {
							flag = false;
							break;
						}
					}
				}
				if (!flag) {
					break;
				}
			}
			if (this.levels == 0) {
				this.isBeaconActive = false;
			}
		}
	}

	protected void addEffects() {
		if (this.isBeaconActive && this.levels > 0 && !this.worldObj.isRemote && this.effect > 0) {
			double distance = (double) (this.levels * 10 + 10);
			AxisAlignedBB axisalignedbb = AxisAlignedBB
					.getAABBPool()
					.getAABB((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1),
							(double) (this.zCoord + 1)).expand(distance, distance, distance);
			axisalignedbb.maxY = (double) this.worldObj.getHeight();
			if (playereffect) {
				this.addEffectsToPlayers(axisalignedbb);
			}
			if (mobeffect) {
				this.addEffectsToHostile(axisalignedbb);
			}
			if (animaleffect) {
				this.addEffectsToAnimal(axisalignedbb);
			}
		}
	}

	protected void addEffectsToPlayers(AxisAlignedBB axisalignedbb) {
		List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
		Iterator iterator = list.iterator();
		EntityPlayer entityplayer;
		while (iterator.hasNext()) {
			entityplayer = (EntityPlayer) iterator.next();
			entityplayer.addPotionEffect(new PotionEffect(this.effect, potionTime, effectstrength, true));
		}
	}

	protected void addEffectsToHostile(AxisAlignedBB axisalignedbb) {
		List list = this.worldObj.getEntitiesWithinAABB(EntityMob.class, axisalignedbb);
		Iterator iterator = list.iterator();
		EntityMob entitymob;
		while (iterator.hasNext()) {
			entitymob = (EntityMob) iterator.next();
			entitymob.addPotionEffect(new PotionEffect(this.effect, potionTime, effectstrength, true));
		}
	}

	protected void addEffectsToAnimal(AxisAlignedBB axisalignedbb) {
		List list = this.worldObj.getEntitiesWithinAABB(EntityAnimal.class, axisalignedbb);
		Iterator iterator = list.iterator();
		EntityAnimal entityanimal;
		while (iterator.hasNext()) {
			entityanimal = (EntityAnimal) iterator.next();
			entityanimal.addPotionEffect(new PotionEffect(this.effect, potionTime, effectstrength, true));
		}
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return i == 0 ? this.payment : null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (i == 0 && this.payment != null) {
			if (j >= this.payment.stackSize) {
				ItemStack itemstack = this.payment;
				this.payment = null;
				return itemstack;
			} else {
				this.payment.stackSize -= j;
				return new ItemStack(this.payment.itemID, j, this.payment.getItemDamage());
			}
		} else {
			return null;
		}

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (i == 0 && this.payment != null) {
			ItemStack itemstack = this.payment;
			this.payment = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i == 0) {
			this.payment = itemstack;
		}

	}

	@Override
	public String getInvName() {
		return "Better Beacon";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public final boolean isStackValidForSlot(int i, ItemStack itemstack) {
		int id = itemstack.itemID;
		return id == Item.emerald.itemID || id == Item.diamond.itemID || id == Item.ingotIron.itemID || id == Item.ingotGold.itemID;
	}

}