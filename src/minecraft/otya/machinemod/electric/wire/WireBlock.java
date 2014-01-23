package otya.machinemod.electric.wire;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import otya.machinemod.block.BaseMachineBlock;
import otya.machinemod.electric.machine.BaseElectric;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import otya.machinemod.tileentity.BaseMachineTileEntity;

public class WireBlock extends BaseElectric{
	/**
	 * 減衰を取得
	 * @param side
	 * @param thistileentity
	 * @return -EU
	 */
	public float getAttenuate(int side, BaseElectricTileEntity thistileentity)
	{
		return 0f;
	}
	public WireBlock(int par1) {
		super(par1,Material.cloth);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("TestWire");
		this.setTextureName("coal_block");
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	@Override
	public TileEntity createNewTileEntity(World par1)
	{
		return new BaseElectricTileEntity(0,0,0,true);
	}
	//レンダーで使ったり使わなかったり
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.6F, 0.6F, 0.6F);
	}
 
	//階段やハーフブロックみるといいかも
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.6F, 0.6F, 0.6F);
	}
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        AxisAlignedBB axisalignedbb1 = this.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
        double d = 0.0625D;
        if (axisalignedbb1 != null && par5AxisAlignedBB.intersectsWith(axisalignedbb1))
        {
        	//System.out.println("Hoge");
            par6List.add(axisalignedbb1);
        }{
            TileEntity tile=par1World.getBlockTileEntity(par2, par3, par4);
            if(tile instanceof BaseElectricTileEntity)
            {
            	BaseElectricTileEntity btile=(BaseElectricTileEntity)tile;
            	int j=-1;
				for(TileEntity i:btile.TileEntity)
				{
					j++;
					if(i instanceof BaseElectricTileEntity)
					switch(j)
					{
					case 0:
						par6List.add(AxisAlignedBB.getAABBPool().getAABB((double)par2 + d+0.299999D, (double)par3+d+0D, (double)par4+0.299999D + d, (double)(par2 + 0.299999D) - d, (double)par3 + 0.299999D - d, (double)(par4 + 0.700001D) - d));
						break;
					case 1:par6List.add(AxisAlignedBB.getAABBPool().getAABB((double)par2 + d+0.299999D, (double)par3+d+0.700001D, (double)par4+0.299999D + d, (double)(par2 + 0.700001D) - d, (double)par3 + 1D - d, (double)(par4 + 0.700001D) - d));
						break;
					case 4:
						par6List.add(AxisAlignedBB.getAABBPool().getAABB((double)par2 + d+0D, (double)par3+d+0.3D, (double)par4+0.3D + d, (double)(par2 + 0.3D) - d, (double)par3 + 0.7D - d, (double)(par4 + 0.7D) - d));
						break;
					case 5:
						par6List.add(AxisAlignedBB.getAABBPool().getAABB((double)par2 + d+0.7D, (double)par3+d+0.3D, (double)par4+0.3D + d, (double)(par2 + 1D) - d, (double)par3 + 0.7D - d, (double)(par4 + 0.7D) - d));
						break;
					case 2:
						par6List.add(AxisAlignedBB.getAABBPool().getAABB((double)par2 + d+0.3D, (double)par3+d+0.3D, (double)par4+0D + d, (double)(par2 + 0.7D) - d, (double)par3 + 0.7D - d, (double)(par4 + 0.3D) - d));
						break;
					case 3:
						par6List.add(AxisAlignedBB.getAABBPool().getAABB((double)par2 + d+0.3D, (double)par3+d+0.3D, (double)par4+0.7D + d, (double)(par2 + 0.7D) - d, (double)par3 + 0.7D - d, (double)(par4 + 1D) - d));
						break;
					}
				}
            }
        }
    }
	//当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z)
	{
		double d = 0.0625D;
		return AxisAlignedBB.getAABBPool().getAABB((double)x + d+0.3D, (double)y+0.3D, (double)z+0.3D + d, (double)(x + 0.7D) - d, (double)y + 0.7D - d, (double)(z + 0.7D) - d);
	}
 
	//ブロックに視点を合わせた時に出てくる黒い線のアレ
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return AxisAlignedBB.getAABBPool().getAABB((double)par2+0.2D, (double)par3+0.2D, (double)par4+0.2D, (double)(par2 +0.2D), (double)par3 +0.2D, (double)(par4 +0.2D));
	}
}
