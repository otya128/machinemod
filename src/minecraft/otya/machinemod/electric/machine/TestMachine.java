package otya.machinemod.electric.machine;

import otya.machinemod.MachineMod;
import otya.machinemod.block.BaseMachineBlock;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TestMachine extends BaseElectric {
	public TestMachine(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("TestBattery");
		this.setTextureName(MachineMod.DomainName+"BAT");
		// TODO 自動生成されたコンストラクター・スタブ
	}
	@Override
	public TileEntity createNewTileEntity(World par1)
	{
		return new BaseElectricTileEntity(0,0,1,false);
	}
	@Override
	public float getInEU(int side,BaseElectricTileEntity thisTileEntity)
	{
		if(side==1)return 0;
		return 1;
	}
	@Override
	public float getOutEU(int side,BaseElectricTileEntity thisTileEntity)
	{
		if(side==1)return 1f;
		return 0;
	}
	@Override
	public void recvEU(BaseElectricTileEntity thisTileEntity,BaseElectricTileEntity sendTileEntity,int side)
	{
		//System.out.println("recv");
		thisTileEntity.battery+=thisTileEntity.getInEU(side);
	}
	@Override
	public void sendEU(BaseElectricTileEntity thisTileEntity,BaseElectricTileEntity sendTileEntity,int side)
	{
		//System.out.println("send");
		//System.out.printf("%s\n", thisTileEntity.battery);
		thisTileEntity.battery-=getOutEU(side,thisTileEntity);
		//sendTileEntity.battery+=getOutEU(side, thisTileEntity);
	}

}
