package otya.machinemod.electric.item;

import otya.machinemod.MachineMod;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemReader extends Item {

	public ItemReader(int par1) {
		super(par1);
		this.setTextureName(MachineMod.DomainName+"reader");
		this.setCreativeTab(CreativeTabs.tabTools);
		// TODO 自動生成されたコンストラクター・スタブ
	}
/*    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	TileEntity tile=world.getBlockTileEntity(x, y, z);
    	if(tile instanceof BaseElectricTileEntity)
    	{
    		BaseElectricTileEntity etile=(BaseElectricTileEntity)tile;
    		player.addChatMessage(Boolean.toString(world.isRemote));
    		player.addChatMessage(java.lang.String.format("EU:%f EUreq:%f EUMax:%f battery:%f", etile.EU,etile.EUreq,etile.EUMax,etile.battery));
    		return true;
    	}
        return false;
    }*/
}
