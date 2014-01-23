package otya.machinemod.electric.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import otya.machinemod.MachineMod;
import otya.machinemod.block.BaseMachineBlock;
import otya.machinemod.electric.gui.MachineInventory;
import otya.machinemod.electric.machine.BaseElectric;
import otya.machinemod.electric.wire.WireBlock;
import otya.machinemod.tileentity.BaseMachineTileEntity;

//不具合
//----機
//で途中の電線を壊すと
//-- -木
//↑にEUが残ったままに
//Breakは引くんじゃなくて再計算するようにした方がいい！！
//
/**
 * 
 * @author hoge単語 'hoge' のスペルが正しくありません
 *
 */
public class BaseElectricTileEntity extends BaseMachineTileEntity{
	public static boolean isPlateStatic(TileEntity te,int side)
	{
		if(te instanceof BaseElectricTileEntity)
		{
			if(((BaseElectricTileEntity)te).plate[side]!=0)
			{
				return true;
			}
			return false;
		}
		return false;
	}
	@NBTData
	public float EUMax = 0; // 供給できる最大EU
	@NBTData
	public float EUreq = 0; // 要求しているEU
	@NBTData
	public float EU = 0;	// 実際のEU
	@NBTData
	public boolean isWire;
	public float EUWatch=0;
	public float battery = 0;
	//minecraftの順番と一致するように20140120
	public int[] connect=new int[6];
	public TileEntity[] TileEntity = new TileEntity[6];
	public Map<BaseElectricTileEntity,Float> EUmap;
	public Map<BaseElectricTileEntity,Float> EUreqmap;
	public Map<BaseElectricTileEntity,Float> EUMaxmap;
	protected int thisblock;
	public MachineInventory inventory;
	public BaseElectricTileEntity()
	{
		super();
		this.inventory = new MachineInventory(this);
		//System.out.println("[DEBUG]new ? wireblock");
	}
	public BaseElectricTileEntity(int EU,int EUMax,int EUreq,boolean isWire)
	{
		super();
		this.EU=EU;
		this.EUMax=EUMax;
		this.EUreq=EUreq;
		this.isWire=isWire;
		if(!isWire){
			this.EUmap=new HashMap<BaseElectricTileEntity,Float>();
			this.EUreqmap=new HashMap<BaseElectricTileEntity,Float>();
			this.EUMaxmap=new HashMap<BaseElectricTileEntity,Float>();
		}
		this.inventory = new MachineInventory(this);
	}
	/**
	 * 入力EUの取得
	 * @param side
	 * @return
	 */
	public float getInEU(int side)
	{
		return ((BaseElectric)Block.blocksList[this.thisblock]).getInEU(side,this);
	}
	/**
	 * 出力EUの取得
	 * @param side
	 * @return
	 */
	public float getOutEU(int side)
	{
		return ((BaseElectric)Block.blocksList[this.thisblock]).getOutEU(side, this);
	}
	public void sendEU(int side,BaseElectricTileEntity par2)
	{
		((BaseElectric)Block.blocksList[this.thisblock]).sendEU(this, par2,side);
	}
	public void recvEU(int side,BaseElectricTileEntity par2)
	{
		((BaseElectric)Block.blocksList[this.thisblock]).recvEU(this, par2,side);
	}
    public void setWorldObj(World par1World)
    {
		super.setWorldObj(par1World);
		/*System.out.printf("x:%s,y:%s,z:%s,isRemote%s\n",this.xCoord,this.yCoord,this.zCoord,par1World.isRemote);
		thisblock = this.worldObj.getBlockId(this.xCoord, this.yCoord,
				this.zCoord);
		if (thisblock != 4008){if(EUreqmap == null) {
			this.isWire = false;
			this.EUmap = new HashMap<BaseElectricTileEntity, Boolean>();
			this.EUreqmap = new HashMap<BaseElectricTileEntity, Float>();
			this.EUMaxmap = new HashMap<BaseElectricTileEntity, Float>();
			System.out.println("[DEBUG]new not wireblock");
		}} else {
			this.isWire = true;
			this.EUMax = 0;
			this.EUreq=0;
		}*/
    }
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		//再計算されるから不要(ただ加算されるだけ)
		this.EU=par1NBTTagCompound.getFloat("EU");
		this.EUMax=par1NBTTagCompound.getFloat("EUMax");
		this.EUreq=par1NBTTagCompound.getFloat("EUreq");
		this.battery=par1NBTTagCompound.getFloat("battery");
		//System.out.printf("[DEBUG] NBTREAD:EU:%f\tEUMax:%f\tEUreq:%f\n", EU,EUMax,EUreq);
	}
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setFloat("EU", this.EU);
		par1NBTTagCompound.setFloat("EUMax", this.EUMax);
		par1NBTTagCompound.setFloat("EUreq", this.EUreq);
		par1NBTTagCompound.setFloat("battery", this.battery);
	}
	@Override
	public void updateEntity() {

/*		for(int i=0;i<plate.length-1;i++)
			{
				System.out.print(plate[i]);
				System.out.print(",");
			}
			System.out.print(plate[plate.length-1]);
			System.out.println("]");*/
		//super.updateEntity();
		int x=this.xCoord,y=this.yCoord,z=this.zCoord;
		int block=this.worldObj.getBlockId(x, this.yCoord,
				this.zCoord);
		thisblock=block;

		if (thisblock != 4008){if(EUreqmap == null) {
			this.isWire = false;
			this.EUmap = new HashMap<BaseElectricTileEntity, Float>();
			this.EUreqmap = new HashMap<BaseElectricTileEntity, Float>();
			this.EUMaxmap = new HashMap<BaseElectricTileEntity, Float>();
			System.out.println("[DEBUG]new not wireblock");
		}} else {if(isWire != true) {
			this.isWire = true;
			this.EUMax = 0;
			this.EUreq=0;
		}
		}
		// 配列にする意味は別の個所を参照する必要性があるため
		//connect = new int[6];
		// TileEntity = new TileEntity[6];
		for (int i = 0; i < 6; i++) {
			// int BlockID = 0;
			switch (i) {
			case 0:
				connect[0] = this.worldObj.getBlockId(x,
						y - 1, z);
				TileEntity[0] = this.worldObj.getBlockTileEntity(x,
						y - 1, z);
				break;
			case 1:
				connect[1] = this.worldObj.getBlockId(x,
						y + 1, z);
				TileEntity[1] = this.worldObj.getBlockTileEntity(x,
						y + 1, z);
				break;
			case 4:
				connect[4] = this.worldObj.getBlockId(x - 1,
						y, z);
				TileEntity[4] = this.worldObj.getBlockTileEntity(
						x - 1, y, z);
				break;
			case 5:
				connect[5] = this.worldObj.getBlockId(x + 1,
						y, z);
				TileEntity[5] = this.worldObj.getBlockTileEntity(
						x + 1, y, z);
				break;
			case 2:
				connect[2] = this.worldObj.getBlockId(x, y,
						z - 1);
				TileEntity[2] = this.worldObj.getBlockTileEntity(x,
						y, z - 1);
				break;
			case 3:
				connect[3] = this.worldObj.getBlockId(x, y,
						z + 1);
				TileEntity[3] = this.worldObj.getBlockTileEntity(x,
						y, z + 1);
				break;
			}
		}
		if(this.worldObj.isRemote)return;
		BaseElectricTileEntity tileentity;
		if (this.isWire) {
			//this.EU=0;
			//this.EUreq=0;
			
			boolean isMachine=false;
			for (int i = 0; i < 6; i++) {
				if(connect[i]==4009){isMachine=true;break;}
				/*if (TileEntity[i] instanceof BaseElectricTileEntity) {
					tileentity = (BaseElectricTileEntity) TileEntity[i];
					if (!tileentity.isWire) {
						isMachine=true;
					}
				}*/
			}this.EUWatch=this.EU;
			if(!isMachine)
			{
				
				this.EU=0;
				//this.EUreq=0;
			}//20140119 2256こんなことしないで全部-1すればいい！！
			//ただ学校なので寝るtileentity
			return;
		}
		//System.out.println(((BaseElectric)Block.blocksList[this.thisblock]).getInEU(0, this.worldObj, x, y, z));
		EUmap.clear();// , tileentityCpy;
		EUmap.put(this, 0F);
		for (int i = 0; i < 6; i++) {
			if (TileEntity[i] instanceof BaseElectricTileEntity) {
				tileentity = (BaseElectricTileEntity) TileEntity[i];
				/*if(this.getOutEU(i)!=0)
				{
					if(tileentity.EU<tileentity.EUreq)
					{
						this.sendEU(i, tileentity);
						//this.battery+=this.EUreq;
						break;//これしないと加算される
						//tileentity.EU-=1;
					}
				}*/
			}
		}
		boolean recvFlg=false,sendFlg=false;
		for (this.side = 0; this.side < 6; this.side++) {
			
			if (TileEntity[this.side] instanceof BaseElectricTileEntity) {
				
				tileentity = (BaseElectricTileEntity) TileEntity[this.side];

				/*if (tileentity.isWire) {
					tileentity.EU++;
				}*/
				//switchだとbreakの意味が違うくなるからとりあえずイフ
				if(!sendFlg&&this.getOutEU(this.side)!=0)
				{
					this.EUMax=this.getOutEU(this.side);
					if(tileentity.EU<tileentity.EUreq)
					{
						this.sendEU(this.side, tileentity);
						sendFlg=true;//これしないと加算される
						//this.battery+=this.EUreq;
						//break;//これしないと加算される
						//tileentity.EU-=1;
					}
				}
				if(!recvFlg&&this.getInEU(this.side)!=0)
				//if(thisblock==4009)
				{
					this.EUreq=this.getInEU(this.side);
					if(this.EUreq!=0&&tileentity.EU>=this.EUreq)
					{
						this.EUWatch=this.EUreq;
						this.recvEU(this.side, tileentity);
						recvFlg=true;//これしないと加算される
						//this.battery+=this.EUreq;
						//break;//これしないと加算される
						//tileentity.EU-=1;
					}/*else
						if(this.EU>=this.EUreq)
						{
							this.EU-=this.EUreq;
							this.battery+=this.EUreq;break;
						}*/
					//tileentity.EUreq+=1;//-=EU	
				}
				switch(this.side)
				{
				case 0:
					if(EUCheckSingle(x,y-1,z))
					EUCheck(x,y-1,z);
					break;
				case 1:
					if(EUCheckSingle(x,y+1,z))
					EUCheck(x,y+1,z);
					break;
				case 2:
					if(EUCheckSingle(x,y,z-1))
					EUCheck(x,y,z-1);
					break;
				case 3:
					if(EUCheckSingle(x,y,z+1))
					EUCheck(x,y,z+1);
					break;
				case 4:
					if(EUCheckSingle(x-1,y,z))
					EUCheck(x-1,y,z);
					break;
				case 5:
					if(EUCheckSingle(x+1,y,z))
					EUCheck(x+1,y,z);
					break;
				}
			}
		}
/*
	try
	{
		saikitest2();

	}
	catch(Error e)
	{
		System.out.println(saikitest);
	}*/
		/*for (this.side = 0; this.side < 6; this.side++) {
			switch(this.side)
			{
			case 0:
				if(EUCheckSingle(x,y-1,z))
				EUCheck(x,y-1,z);
				break;
			case 1:
				if(EUCheckSingle(x,y+1,z))
				EUCheck(x,y+1,z);
				break;
			case 2:
				if(EUCheckSingle(x,y,z-1))
				EUCheck(x,y,z-1);
				break;
			case 3:
				if(EUCheckSingle(x,y,z+1))
				EUCheck(x,y,z+1);
				break;
			case 4:
				if(EUCheckSingle(x-1,y,z))
				EUCheck(x-1,y,z);
				break;
			case 5:
				if(EUCheckSingle(x+1,y,z))
				EUCheck(x+1,y,z);
				break;
			}
		}*/
		//EUCheck(x,y,z);
	
		}
	//スタックがもったいないのでグローバル変数に持たせる
	//そのうちアルゴリズムを変更する
	//優先順位はAPIが咲
	protected int side;	
	int saikitest;
	void saikitest2()
{
		saikitest++;
		saikitest2();
}
	//亀井かめい
	//EUCheck2->EUCheckSingle
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return isWire
	 */
	private boolean EUCheckSingle(int x,int y,int z)
	{
		TileEntity TileEntity = this.worldObj.getBlockTileEntity(x,y, z);
		if(TileEntity instanceof BaseElectricTileEntity)
		{
			EU((BaseElectricTileEntity)TileEntity);
			return ((BaseElectricTileEntity)TileEntity).isWire;
		}
		return false;
	}
	float gensui=0.0F;
	private void EUCheck(int x,int y,int z)
	{
		int[] connect=new int[6];
		TileEntity[] TileEntity = new TileEntity[6];
		for (int i = 0; i < 6; i++) {
		// int BlockID = 0;
		switch (i) {
		case 0:
			connect[0] = this.worldObj.getBlockId(x,
					y - 1, z);
			TileEntity[0] = this.worldObj.getBlockTileEntity(x,
					y - 1, z);
			break;
		case 1:
			connect[1] = this.worldObj.getBlockId(x,
					y+ 1, z);
			TileEntity[1] = this.worldObj.getBlockTileEntity(x,
					y + 1, z);
			break;
		case 4:
			connect[4] = this.worldObj.getBlockId(x - 1,
					y, z);
			TileEntity[4] = this.worldObj.getBlockTileEntity(
					x - 1, y, z);
			break;
		case 5:
			connect[5] = this.worldObj.getBlockId(x + 1,
					y, z);
			TileEntity[5] = this.worldObj.getBlockTileEntity(
					x + 1, y, z);
			break;
		case 2:
			connect[2] = this.worldObj.getBlockId(x, y,
					z - 1);
			TileEntity[2] = this.worldObj.getBlockTileEntity(x,
					y, z - 1);
			break;
		case 3:
			connect[3] = this.worldObj.getBlockId(x, y,
					z + 1);
			TileEntity[3] = this.worldObj.getBlockTileEntity(x,
					y, z + 1);
			break;
		}
	}
		BaseElectricTileEntity tileentity;// , tileentityCpy;
		for (int i = 0; i < 6; i++) {
			if(TileEntity[i] instanceof BaseElectricTileEntity)
			{
				tileentity=(BaseElectricTileEntity)TileEntity[i];
				if (EUmap.containsKey(tileentity))
					continue;
				EU(tileentity);
				if(tileentity.isWire)
				{
					gensui+=0.01;
					switch (i) {
					case 0:
						EUCheck(x, y - 1, z);
						break;
					case 1:
						EUCheck(x, y + 1, z);
						break;
					case 4:
						EUCheck(x - 1, y, z);
						break;
					case 5:
						EUCheck(x + 1, y, z);
						break;
					case 2:
						EUCheck(x, y, z - 1);
						break;
					case 3:
						EUCheck(x, y, z + 1);
						break;
					}
					gensui-=0.01;
				}
			}
		}
	}

	void EU(BaseElectricTileEntity tileentity) {
		if (EUmap.containsKey(tileentity))
			return;
		EUmap.put(tileentity, 0F);
		if (tileentity.isWire) {
			//New API 20140122
			if(this.getInEU(this.side)!=0)
			{
				float ineu=this.getInEU(this.side);
			if (!EUreqmap.containsKey(tileentity)) {//||EUreqmap.get(tileentity) !=1.0F
				//New API 20140122いねう
				EUreqmap.put(tileentity, ineu);
				tileentity.EUreq += ineu;
			}
			if (tileentity.EU >= ineu) {
				tileentity.EU -= ineu-gensui;
			}
			}
			if(this.getOutEU(this.side)!=0)
			{
				//おうてう
				float outeu=this.getOutEU(this.side);
				if (!EUMaxmap.containsKey(tileentity)) {
					EUMaxmap.put(tileentity, outeu);
					tileentity.EUMax += outeu;
				}
				if (tileentity.EUreq > tileentity.EU)
					tileentity.EU+=outeu-gensui;
			}
			//old codes
			/*switch (thisblock) {
			case 4009:
				if(this.getInEU(0)!=0)
				{
					float ineu=this.getInEU(0);
				if (!EUreqmap.containsKey(tileentity)
													 * ||EUreqmap.get(tileentity)
													 * !=1.0F
													 ) {
					//New API 20140122
					//いねう
					EUreqmap.put(tileentity, ineu);
					tileentity.EUreq += ineu;
					//EUreqmap.put(tileentity, this.EUreq);
					//tileentity.EUreq += this.EUreq;
				}
				if (tileentity.EU >= ineu) {
					tileentity.EU -= ineu;
				}
				//if (tileentity.EU >= this.EUreq) {
				//	tileentity.EU -= this.EUreq;
				//}
				}
				break;
			case 4010:
				if (!EUMaxmap.containsKey(tileentity)) {
					EUMaxmap.put(tileentity, this.EUMax);
					tileentity.EUMax += this.EUMax;
				}
				if (tileentity.EUreq > tileentity.EU)
					tileentity.EU++;
				break;
			}*/
			
		} else {
			/*
			 * if(connect[i]==4009) switch (thisblock) { case 4010:
			 * if(tileentity.EUreq>tileentity.EU) tileentity.EU++; break; }
			 */
		}

	}
	public void EUSearch(int x,int y,int z) {
		int[] connect=new int[6];
		TileEntity[] TileEntity = new TileEntity[6];
		for (int i = 0; i < 6; i++) {
			switch (i) {
			case 0:connect[0] = this.worldObj.getBlockId(x,y - 1, z);TileEntity[0] = this.worldObj.getBlockTileEntity(x,y - 1, z);break;
			case 1:connect[1] = this.worldObj.getBlockId(x,y + 1, z);TileEntity[1] = this.worldObj.getBlockTileEntity(x,y + 1, z);break;
			case 4:connect[4] = this.worldObj.getBlockId(x - 1,y, z);TileEntity[4] = this.worldObj.getBlockTileEntity(x - 1, y, z);break;
			case 5:connect[5] = this.worldObj.getBlockId(x + 1,y, z);TileEntity[5] = this.worldObj.getBlockTileEntity(x + 1, y, z);break;
			case 2:connect[2] = this.worldObj.getBlockId(x, y,z - 1);TileEntity[2] = this.worldObj.getBlockTileEntity(x,y, z - 1);break;
			case 3:connect[3] = this.worldObj.getBlockId(x, y,z + 1);TileEntity[3] = this.worldObj.getBlockTileEntity(x,y, z + 1);break;
			}
		}
		BaseElectricTileEntity tileentity;// , tileentityCpy;
		for (int i = 0; i < 6; i++) {
			if(TileEntity[i] instanceof BaseElectricTileEntity) {
				tileentity=(BaseElectricTileEntity)TileEntity[i];
				if (EUmap.containsKey(tileentity))
					continue;
				EUmap.put(tileentity, 0F);
				if(tileentity.isWire) {
					switch (i) {
					case 0:
						EUSearch(x, y - 1, z);
						break;
					case 1:
						EUSearch(x, y + 1, z);
						break;
					case 4:
						EUSearch(x - 1, y, z);
						break;
					case 5:
						EUSearch(x + 1, y, z);
						break;
					case 2:
						EUSearch(x, y, z - 1);
						break;
					case 3:
						EUSearch(x, y, z + 1);
						break;
					}
				}
			}
		}
		
	}
	public void Break()
	{
		for(BaseElectricTileEntity i : this.EUreqmap.keySet())
		{
			//System.out.print(i);
		//System.out.print(this);
			//System.out.printf("\t%f\t%f\n",i.EUreq,this.EUreq);
			i.EUreq-=this.EUreq;
		}
		for(BaseElectricTileEntity i : this.EUMaxmap.keySet())
		{
			i.EUMax-=this.EUMax;
		}
	}public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
        }

        public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        readFromNBT(packet.data);
        }
	/*@Override
	public void updateEntity() {
		super.updateEntity();
		int block=this.worldObj.getBlockId(this.xCoord, this.yCoord,
				this.zCoord);
		// 配列にする意味は別の個所を参照する必要性があるため
		//connect = new int[6];
		// TileEntity = new TileEntity[6];
		for (int i = 0; i < 6; i++) {
			// int BlockID = 0;
			switch (i) {
			case 0:
				connect[0] = this.worldObj.getBlockId(this.xCoord,
						this.yCoord - 1, this.zCoord);
				TileEntity[0] = this.worldObj.getBlockTileEntity(this.xCoord,
						this.yCoord - 1, this.zCoord);
				break;
			case 1:
				connect[1] = this.worldObj.getBlockId(this.xCoord,
						this.yCoord + 1, this.zCoord);
				TileEntity[1] = this.worldObj.getBlockTileEntity(this.xCoord,
						this.yCoord + 1, this.zCoord);
				break;
			case 2:
				connect[2] = this.worldObj.getBlockId(this.xCoord - 1,
						this.yCoord, this.zCoord);
				TileEntity[2] = this.worldObj.getBlockTileEntity(
						this.xCoord - 1, this.yCoord, this.zCoord);
				break;
			case 3:
				connect[3] = this.worldObj.getBlockId(this.xCoord + 1,
						this.yCoord, this.zCoord);
				TileEntity[3] = this.worldObj.getBlockTileEntity(
						this.xCoord + 1, this.yCoord, this.zCoord);
				break;
			case 4:
				connect[4] = this.worldObj.getBlockId(this.xCoord, this.yCoord,
						this.zCoord - 1);
				TileEntity[4] = this.worldObj.getBlockTileEntity(this.xCoord,
						this.yCoord, this.zCoord - 1);
				break;
			case 5:
				connect[5] = this.worldObj.getBlockId(this.xCoord, this.yCoord,
						this.zCoord + 1);
				TileEntity[5] = this.worldObj.getBlockTileEntity(this.xCoord,
						this.yCoord, this.zCoord + 1);
				break;
			}
		}
		BaseElectricTileEntity tileentity;//, tileentityCpy;
		for (int i = 0; i < 6; i++) {
			if (TileEntity[i] instanceof BaseElectricTileEntity) {
				tileentity = (BaseElectricTileEntity) TileEntity[i];
				if (this.isWire) {
					// for (int j = 0; i < 6; i++) {
					// if(j==i)continue;
					// if(TileEntity[j] instanceof BaseElectricTileEntity)
					// {
					// tileentityCpy=(BaseElectricTileEntity)TileEntity[j];
					if(tileentity.EUreq!=0)tileentity.EU += this.EU;
					if(!this.worldObj.isRemote){
					if(tileentity.isWire)
					if(tileentity.EUreq!=0)System.out.printf("EU:%f=%f\t", tileentity.EU, this.EU);
					//if(tileentity.isWire)
						System.out.printf("EUreq:%f+=%f\n", tileentity.EUreq,
							this.EUreq);
					}
					//System.out.printf("EUMax:%f+=%f\n", tileentity.EUMax,
					//		this.EUMax);
					if(tileentity.isWire)tileentity.EUreq += this.EUreq;
					tileentity.EUMax += this.EUMax;
					if(tileentity.EUreq!=0)this.EU = 0;
					//if(tileentity.isWire)
					//this.EUreq=0;
					// }
					// }
				}
				else
				{
					switch(block)
					{
					case 4009:
					battery+=this.EU;
					this.EUreq=1;
					tileentity.EUreq+=this.EUreq;
					this.EU=0;
					if(!this.worldObj.isRemote){
					System.out.printf("EUreq:%f=%f\t", tileentity.EUreq,
							this.EUreq);
					System.out.printf("battery:%f\n", battery);}break;
					case 4010:
						this.EU=1;
					if(tileentity.EUreq!=0)tileentity.EU+=this.EU;
					if(!this.worldObj.isRemote){
					System.out.printf("EUreq:%f=%f\t", tileentity.EUreq,
							this.EUreq);
					System.out.printf("battery:%f\n", battery);
					}
				}
					}
			}
		}*/
	/**
	 * これ付けたら自動でNBTに書き込んだり読まれたりするって有りそう
	 * @author hoge
	 *
	 */
	public @interface NBTData
	{
		
	}
	}


	

