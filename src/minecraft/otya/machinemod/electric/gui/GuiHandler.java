package otya.machinemod.electric.gui;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
 
public class GuiHandler implements IGuiHandler {
 
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
 
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity instanceof BaseElectricTileEntity) {
			return new MachineContainer(player, (BaseElectricTileEntity) tileentity);
		}
		return null;
	}
 
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
 
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity instanceof BaseElectricTileEntity) {
			return new MachineGui(player, (BaseElectricTileEntity) tileentity);
		}
		return null;
	}
 
}
