package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.interfaces.IXorGridObj;



public class TETriniumSun extends TEPoweredBase implements IXorGridObj{


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
	public void init() {
		
	}

}
