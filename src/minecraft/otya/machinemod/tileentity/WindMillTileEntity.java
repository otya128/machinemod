package otya.machinemod.tileentity;

public class WindMillTileEntity extends BaseMachineTileEntity{
	public float rotateX;

	@Override
	public void updateEntity() {
		super.updateEntity();
		rotateX = (rotateX + 0.1f) % 360f;
	}
}
