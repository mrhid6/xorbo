package mrhid6.xorbo.tileentities;

import net.minecraft.item.ItemStack;

public class TETriniumSun extends TileMachinePower{


	public int speed = 0;
	public boolean bob = false;
	private static int descPacketId;

	public TETriniumSun(){

		//inputsides = new boolean[6];
		//inputsideproviders = new IPowerProvider[6];
		//this.inventory = new ItemStack[0];
		//ticker = rand.nextInt(128);
		//this.myProvider = new PowerProviderXor();
		//this.myProvider.configure(1, 200);
	}

	@Override
	public boolean func_94042_c() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getMaxEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean canStart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean canFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processFinish() {
		// TODO Auto-generated method stub
		
	}

}
