package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.GridManager;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IConverterObj;
import mrhid6.xorbo.interfaces.IGridInterface;
import mrhid6.xorbo.interfaces.IPacketXorHandler;
import mrhid6.xorbo.interfaces.ITriniumObj;
import mrhid6.xorbo.interfaces.IXorGridObj;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;

public class TECableBase extends TileEntity implements IGridInterface,
		IPacketXorHandler {

	protected static int descPacketId;
	public int gridindex = -1;

	public double maxPower = 5.0D;
	public double tempPower = 0.0D;
	public int type = 0;

	public TECableBase() {

	}

	public void breakBlock() {

		if (getGrid() != null) {
			getGrid().removeCable(this);
		}
	}

	public boolean canInteractRender( TileEntity te, int side ) {
		if (te instanceof TETriniumConverter) {

			if (side == 2 || side == 3) {
				return false;
			}

			return true;
		}
		return canInteractWith(te, side);
	}

	public boolean canInteractWith( TileEntity te, int side ) {

		if (te instanceof TETriniumConverter) {
			TETriniumConverter te1 = (TETriniumConverter) te;
			return te1.canConnectSide(side);
		}

		if (te instanceof ITriniumObj) {
			return false;
		}
		if (te instanceof TECableBase) {
			return true;
		}
		if (te instanceof IXorGridObj) {
			return true;
		}
		if (te instanceof IConverterObj) {
			return true;
		}

		return false;
	}

	@Override
	public void findGrid() {
		for (int i = 0; i < 6; i++) {

			if (getGrid() != null) {
				return;
			}

			int x1 = xCoord + Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord + Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord + Config.SIDE_COORD_MOD[i][2];

			/*
			 * GridPower gridCheck = GridManager.getGridAt(x1, y1, z1,
			 * worldObj,i); if(gridCheck!=null) this.gridindex =
			 * gridCheck.gridIndex;
			 */

			TileEntity te = worldObj.getBlockTileEntity(x1, y1, z1);

			if (te instanceof TECableBase && !(te instanceof ITriniumObj)) {
				TECableBase cable = (TECableBase) te;

				if (cable.getGrid() != null) {
					gridindex = cable.getGrid().gridIndex;
					// System.out.println("found grid method");
					break;
				}
			} else if (te instanceof TETriniumConverter) {
				TETriniumConverter controller = (TETriniumConverter) te;

				if (controller.getGrid() != null) {
					// System.out.println("found grid method");
					gridindex = controller.getGrid().gridIndex;
					break;
				}
			} else if (te instanceof TEZoroController) {
				TEZoroController controller = (TEZoroController) te;

				if (controller.getGrid() != null) {
					gridindex = controller.getGrid().gridIndex;
					break;
				}
			}
		}
	}

	public double getCableThickness() {
		switch (type) {
		case 0:
			return 4.0D / 16.0D;
		case 1:
			return 6.0D / 16.0D;
		}

		return 1.0D / 16.0D;
	}

	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(0, 0, 1, 0, 0);

		// System.out.println(gridindex);

		payload.intPayload[0] = gridindex;

		PacketTile packet = new PacketTile(descPacketId, xCoord, yCoord,
				zCoord, payload);
		return packet.getPacket();
	}

	@Override
	public GridPower getGrid() {
		GridPower grid = GridManager.getGrid(gridindex);

		return grid;

	}

	public boolean gridCheck() {
		for (int i = 0; i < 6; i++) {

			int x1 = xCoord + Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord + Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord + Config.SIDE_COORD_MOD[i][2];

			GridPower gridCheck = GridManager
					.getGridAt(x1, y1, z1, worldObj, i);

			if (getGrid() != null && gridCheck != null) {

				if (gridCheck.gridIndex < getGrid().gridIndex) {
					getGrid().removeCable(this);
					gridindex = gridCheck.gridIndex;
					getGrid().addCable(this);

					System.out.println("grid Was Changed to"
							+ gridCheck.gridIndex);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void handleTilePacket( PacketTile packet ) {

		gridindex = packet.payload.intPayload[0];

		if (Utils.isClientWorld()) {
			gridindex = packet.payload.intPayload[0];
			// System.out.println("hadlepacket"+gridindex);
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);

		if (Utils.isServerWorld()) {
			PacketUtils.sendToPlayers(getDescriptionPacket(), worldObj, xCoord,
					yCoord, zCoord, 192);
		}

	}

	public void onNeighborBlockChange() {

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void sendUpdatePacket( Side side ) {
		if ((Utils.isServerWorld()) && (side == Side.CLIENT)) {
			PacketUtils.sendToPlayers(getDescriptionPacket(), worldObj, xCoord,
					yCoord, zCoord, 192);

			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
		} else if ((Utils.isClientWorld()) && (side == Side.SERVER)) {
			PacketUtils.sendToServer(getDescriptionPacket());
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (Utils.isClientWorld()) {
			return;
		}

		boolean update = false;
		boolean updateGridCheck = false;

		if (getGrid() == null) {
			findGrid();

			if (getGrid() != null) {
				update = true;
				System.out.println("found Grid" + getGrid().gridIndex);
				getGrid().addCable(this);
			} else {
				// System.out.println("cable null");
			}
		}

		updateGridCheck = gridCheck();
		if (update || updateGridCheck) {
			sendUpdatePacket(Side.CLIENT);
		}
	}
}
