package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest2 {

	@Test
	void test() {
		Monom str = new Monom("5x^7");
		Monom m = new Monom(5,7);
		
		if(!str.equals(m))
			fail("Not yet implemented");
	}
	
	@Test

	void testadd() throws Exception {

		double d1 = Math.random()*100;
		double d2 = Math.random()*100;

		int power = (int)(Math.random()*100);

		Monom m1 = new Monom(d1 , power);
		Monom m2 = new Monom(d2 , power);

		m1.add(m2);

		if((m1.get_coefficient() != d1+d2))

			fail("ERORR : coefficient and power are not equal !,to that after adding the two monoms");
	}
	
	@Test

	void testMultiply() {

		double coef1 = Math.random()*10;
		double coef2 = Math.random()*10;

		int power1 = (int)(Math.random()*10);
		int power2 = (int)(Math.random()*10);

		Monom m1 = new Monom(coef1,power1);
		Monom m2 = new Monom(coef2,power2);

		m1.multipy(m2);

		if(m1.get_coefficient()!=coef1*coef2||m1.get_power()!=power1+power2)

			fail("ERROR FREND :both Monom have to be equals");

	}
	
	@Test

	void testToString() {

		double coef = Math.random()*10;
		int power = (int)(Math.random()*10);

		Monom m = new Monom(coef,power);

		if(coef == 0) {

			if(!m.toString().equals("0"))

				fail("EROR");
		}
	}

}
