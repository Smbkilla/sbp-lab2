package hr.fer.sbp.lab2;

import org.junit.Test;

public class ZoviMontirajBezTransTest {

	private ZoviMontirajBezTrans zoviMontirajBezTrans = new ZoviMontirajBezTrans();

	@Test
	public void zoviMontirajBezTransA() {
		zoviMontirajBezTrans.zoviMontirajBezTrans(101, "S6");
	}

	@Test
	public void zoviMontirajBezTransB() {
		zoviMontirajBezTrans.zoviMontirajBezTrans(137, "S4");
	}

	@Test
	public void zoviMontirajBezTransC() {
		zoviMontirajBezTrans.zoviMontirajBezTrans(101, "S5");

	}

	@Test
	public void zoviMontirajBezTransD() {
		zoviMontirajBezTrans.zoviMontirajBezTrans(101, "S4");
	}

	@Test
	public void zoviMontirajBezTransE() {
		zoviMontirajBezTrans.zoviMontirajBezTrans(180, "S5");
	}
}