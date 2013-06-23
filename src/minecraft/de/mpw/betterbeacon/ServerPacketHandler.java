package de.mpw.betterbeacon;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.Slot;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import de.mpw.betterbeacon.gui.ContainerBetterBeacon;

public class ServerPacketHandler implements IPacketHandler {

	public ServerPacketHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		EntityPlayer sender = (EntityPlayer) player;
		try {
			if (packet.channel.equals(BetterBeacon.guiChannel)) {
				handleGui(sender, data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}

	private void handleGui(EntityPlayer sender, DataInputStream data) throws IOException {
		if (sender.openContainer instanceof ContainerBetterBeacon) {
			ContainerBetterBeacon containerb = (ContainerBetterBeacon) sender.openContainer;
			TileEntityBetterBeacon b = containerb.getBetterBeacon();
			Slot s = containerb.getSlot(0);
			if (s.getHasStack()) {
				int effectId = data.readInt();
				int effectStrength = data.readInt();
				Boolean playereffect = data.readBoolean();
				Boolean animaleffect = data.readBoolean();
				Boolean mobeffect = data.readBoolean();
				s.decrStackSize(1);
				b.setEffect(effectId);
				b.setEffectstrength(effectStrength);
				b.setPlayereffect(playereffect);
				b.setAnimaleffect(animaleffect);
				b.setMobeffect(mobeffect);
				b.onInventoryChanged();
			}
		}

	}

}
