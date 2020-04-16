package hr.fer.sbp.lab2;

import org.junit.Test;

public class MontirajUzTransTest {

	private MontirajUzTrans montirajUzTrans = new MontirajUzTrans();
	
	@Test
	public void montirajUzTransA(){
		montirajUzTrans.montirajUzTrans(101, "S6");
	}

	@Test
	public void montirajUzTransB(){
		montirajUzTrans.montirajUzTrans(137, "S4");
	}

	@Test
	public void montirajUzTransC(){
		montirajUzTrans.montirajUzTrans(101, "S5");

	}

	@Test
	public void montirajUzTransD(){
		montirajUzTrans.montirajUzTrans(101, "S4");
	}

	@Test
	public void montirajUzTransE(){
		montirajUzTrans.montirajUzTrans(180, "S5");
	}
}