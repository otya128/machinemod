package otya.machinemod.electric.machine;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import otya.machinemod.MachineMod;
import otya.machinemod.block.BaseMachineBlock;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import otya.machinemod.electric.wire.WireBlock;

public class BaseElectric extends BaseMachineBlock{
	public BaseElectric(int par1) {
		super(par1);
		this.setUnlocalizedName(this.getClass().getName());
		//this.needsRandomTick=true;
		//this.setTickRandomly(true);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public BaseElectric(int par1,Material par2) {
		super(par1,par2);
		this.setUnlocalizedName(this.getClass().getName());
		//this.needsRandomTick=true;
		//this.setTickRandomly(true);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) 
	{
		System.out.println("hogeeeeeeeeeeee");
		//((BaseElectricTileEntity) par1World.getBlockTileEntity(par2, par3, par4)).updateEntity_();
	}
	public float getInEU(int side,BaseElectricTileEntity thisTileEntity)
	{
		return 0;
	}
	public float getOutEU(int side,BaseElectricTileEntity thisTileEntity)
	{
		return 0;
	}
	/**
	 * !!-=getInEU
	 *     <-getInEU
	 * par1<-par2
	 * @param par1
	 * @param par2
	 */
	public void recvEU(BaseElectricTileEntity par1,BaseElectricTileEntity par2,int side)
	{
		
	}
	/**
	 * !!+=getOutEU
	 *     <-getOutEU
	 * par1<-par2
	 * @param par1
	 * @param par2
	 */
	public void sendEU(BaseElectricTileEntity par1,BaseElectricTileEntity par2,int side)
	{
		
	}
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if(!par1World.isRemote)
		{
			if(par5EntityPlayer.getCurrentItemOrArmor(0)!=null&&par5EntityPlayer.getCurrentItemOrArmor(0).itemID==28901+256)
			{
				BaseElectricTileEntity etile=((BaseElectricTileEntity) par1World
						.getBlockTileEntity(par2, par3, par4));
				par5EntityPlayer.addChatMessage(java.lang.String.format("%s:%f %sreq:%f %sMax:%f battery:%f",MachineMod.unit, etile.EUWatch,MachineMod.unit,etile.EUreq,MachineMod.unit,etile.EUMax,etile.battery));
				return true;
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		BaseElectricTileEntity bete = (BaseElectricTileEntity)par1World.getBlockTileEntity(par2, par3, par4);
		if(bete!=null&&(!(this instanceof WireBlock)))
		{
			bete.Break();
		}
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
		BaseElectricTileEntity tile = (BaseElectricTileEntity)par1World.getBlockTileEntity(par2, par3, par4);
		tile.EUmap=new HashMap<BaseElectricTileEntity, Float>();
		tile.EUSearch(par2, par3, par4);
		if(tile.EUMaxmap!=null)tile.EUMaxmap.clear();//tile.EUreqmap?.clear();
		if(tile.EUreqmap!=null)tile.EUreqmap.clear();
		tile.EUMax=0F;
		tile.EUreq=0F;
		for(BaseElectricTileEntity i:tile.EUmap.keySet())
		{
			if(i.EUMaxmap!=null)i.EUMaxmap.clear();
			if(i.EUreqmap!=null)i.EUreqmap.clear();
			i.EUMax=0;
			i.EUreq=0;
		}
		System.out.println("kousin");
    }
}
