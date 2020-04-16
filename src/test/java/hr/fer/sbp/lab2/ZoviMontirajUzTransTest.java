package hr.fer.sbp.lab2;

import org.junit.Test;

public class ZoviMontirajUzTransTest {

	private ZoviMontirajUzTrans zoviMontirajUzTrans = new ZoviMontirajUzTrans();

	@Test
	public void zoviMontirajUzTransA() {
		zoviMontirajUzTrans.zoviMontirajUzTrans(101, "S6");
	}

	@Test
	public void zoviMontirajUzTransB() {
		zoviMontirajUzTrans.zoviMontirajUzTrans(137, "S4");
	}

	@Test
	public void zoviMontirajUzTransC() {
		zoviMontirajUzTrans.zoviMontirajUzTrans(101, "S5");

	}

	@Test
	public void zoviMontirajUzTransD() {
		zoviMontirajUzTrans.zoviMontirajUzTrans(101, "S4");
	}

	@Test
	public void zoviMontirajUzTransE() {
		zoviMontirajUzTrans.zoviMontirajUzTrans(180, "S5");
	}
}