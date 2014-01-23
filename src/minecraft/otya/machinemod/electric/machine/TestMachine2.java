package otya.machinemod.electric.machine;

import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.LogWrapper;
import net.minecraft.logging.LogAgent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import otya.machinemod.MachineMod;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;

public class TestMachine2 extends TestMachine {

	public TestMachine2(int par1) {
		super(par1);
		this.setTextureName("");
		this.setUnlocalizedName(String.format("Test%sGen",MachineMod.unit));
		// TODO 自動生成されたコンストラクター・スタブ
	}
	@Override
	public float getInEU(int side,BaseElectricTileEntity thisTileEntity)
	{
		return 0;
	}
	@Override
	public float getOutEU(int side,BaseElectricTileEntity thisTileEntity)
	{
		return 1;
	}
	@Override
	public void recvEU(BaseElectricTileEntity thisTileEntity,BaseElectricTileEntity sendTileEntity,int side)
	{
		LogWrapper.log(Level.WARNING, "recv%s???????",MachineMod.unit);
	}
	@Override
	public void sendEU(BaseElectricTileEntity par1,BaseElectricTileEntity par2,int side)
	{
		//par2.EU+=this.getOutEU(side,par1);
	}
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		if (par1World.isRemote)
		{
			return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
		}
		else
		{
			if(!super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9))
			{
			// GUIを開く
			par5EntityPlayer.openGui(MachineMod.instance, MachineMod.instance.guiMachine2ID, par1World, par2, par3, par4);
			}
			return true;
		}
	}
	@Override
	public TileEntity createNewTileEntity(World par1)
	{
		return new BaseElectricTileEntity(0,1,0,false);
	}

}
