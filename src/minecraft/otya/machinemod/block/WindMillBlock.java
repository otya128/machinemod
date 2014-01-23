package otya.machinemod.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import otya.machinemod.tileentity.BaseMachineTileEntity;
import otya.machinemod.tileentity.WindMillTileEntity;

public class WindMillBlock extends BaseMachineBlock{

	public WindMillBlock(int par1) {
		super(par1);
		this.setUnlocalizedName("WindMill");
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return new WindMillTileEntity();
	}
}
