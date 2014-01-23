package otya.machinemod.tileentity;

import java.io.DataOutputStream;
import java.io.IOException;
 



import otya.machinemod.MachineMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.*;
import net.minecraft.tileentity.TileEntity;
 



import com.google.common.io.ByteArrayDataInput;
 



import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseMachineTileEntity extends TileEntity {
	public int[] plate;
	public int[] plateMetadata;
	public BaseMachineTileEntity()
	{super();
		this.blockType = MachineMod.BaseMachineBlock;
		//System.out.printf("new!!x=%d,y=%d,z=%d\n",this.xCoord, this.yCoord, this.zCoord);
		this.plate = new int[6];
		this.plateMetadata = new int[6];
	}
	public int getPlate(int side)
	{
		//if(side==0)return 0;
		//if(side>=6)return 0;
		//System.out.print(this);
		//System.out.printf("x=%d,y=%d,z=%d\n",this.xCoord, this.yCoord, this.zCoord);
		return this.plate[side];
	}
	public int getPlateMetadata(int side)
	{
		//if(side==0)return 0;
		//if(side>=6)return 0;
		return this.plateMetadata[side];
	}
	public void readFromNBTPlate(NBTTagCompound par1NBTTagCompound)
	{
		int[] plate = par1NBTTagCompound.getIntArray("Plate");
		if(plate==null)return;
		if(plate.length!=6) return;
		this.plate=plate.clone();
		int[] platemeta = par1NBTTagCompound.getIntArray("PlateMetadata");
		if(platemeta.length<6) return;
		this.plateMetadata=platemeta.clone();
	}
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		//読み込み
		readFromNBTPlate(par1NBTTagCompound);
		//System.out.printf("x=%d,y=%d,z=%d\n",this.xCoord, this.yCoord, this.zCoord);
		//System.out.print(this);
		//System.out.print("Read[");
		//for(int i=0;i<side.length-1;i++)
		/*{
			System.out.print(side[i]);
			System.out.print(",");
		}
		System.out.print(side[side.length-1]);
		System.out.println("]");*/
		/*
		this.Side[0] = par1NBTTagCompound.getInteger("Side1");
		this.Side[1] = par1NBTTagCompound.getInteger("Side2");
		this.Side[2] = par1NBTTagCompound.getInteger("Side3");
		this.Side[3] = par1NBTTagCompound.getInteger("Side4");
		this.Side[4] = par1NBTTagCompound.getInteger("Side5");
		this.Side[5] = par1NBTTagCompound.getInteger("Side6");
		//*/
	}
	public void writeToNBTPlate(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setIntArray("Plate", this.plate);
		par1NBTTagCompound.setIntArray("PlateMetadata", this.plateMetadata);
	}
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		this.writeToNBTPlate(par1NBTTagCompound);
		/*System.out.printf("x=%d,y=%d,z=%d\n",this.xCoord, this.yCoord, this.zCoord);
		System.out.print(this);
		System.out.print("Write[");
		for(int i=0;i<Side.length-1;i++)
		{
			System.out.print(Side[i]);
			System.out.print(",");
		}
		System.out.print(Side[Side.length-1]);
		System.out.println("]");*/
		/*
		par1NBTTagCompound.setInteger("Side1", this.Side[0]);
		par1NBTTagCompound.setInteger("Side2", this.Side[1]);
		par1NBTTagCompound.setInteger("Side3", this.Side[2]);
		par1NBTTagCompound.setInteger("Side4", this.Side[3]);
		par1NBTTagCompound.setInteger("Side5", this.Side[4]);
		par1NBTTagCompound.setInteger("Side6", this.Side[5]);
		//*/
	}
/*@Override

public void readToPacket(ByteArrayDataInput data) {

}*/
	public void setPlate(int par6, int itemID) {
		// TODO 自動生成されたメソッド・スタブ
		//System.out.println(par6);
		//if (par6 >= 6 || par6 <= 0)
		//	return;
		this.plate[par6] = itemID;
	}

	public void setPlateMetadata(int par6, int meta) {
		this.plateMetadata[par6] = meta;
	}

	public void readToPacket(ByteArrayDataInput data) {
		//アイテムの読み込み
		for (int i = 0; i < this.plate.length; i++) {
			int id = data.readInt();
			this.plate[i]=id;
		}
	}
 
	public void writeToPacket(DataOutputStream dos) {
		try {
			//アイテムの書き込み
			for (int i = 0; i < this.plate.length; i++) {
				dos.writeInt(this.plate[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
        }

        public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        readFromNBT(packet.data);
        }
}
