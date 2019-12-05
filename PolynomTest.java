package Ex1;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
	}
	public static void test1() {
		System.out.println("*********** test 1 ***********");
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2","-0.5x^2","4x","5"};
		for (int i = 0; i < monoms.length; i++) {
			p1.add(new Monom(monoms[i]));
		}
		System.out.println("p1="+p1);
		System.out.println("p1'="+p1.derivative());
		double root1 = p1.root(-4, -2.5, Monom.EPSILON);
		double root2 = p1.root(-2.8, 11, Monom.EPSILON);
		System.out.println("p1 root1:"+root1);
		System.out.println("p1 root2:"+root2); 
		Monom m1 = new Monom(root1,2);
		Monom m2 = new Monom(-3,2);
		if(m1.equals(m2)) System.out.println(m1+"  equals  "+m2);
		System.out.println("area "+p1+" between -3.5 to 0 is "+p1.area(-3.5, 0, Monom.EPSILON));
		
		
		Polynom p2=new Polynom();
		Monom m = new Monom(monoms[1]);
		p2.add(m);
		System.out.println("p2 = "+p2);
		double aa = p2.area(0, 1, 0.0001);
		Polynom copy=(Polynom)p2.copy();
		p2.substract(p2);
		System.out.println(copy+"-"+copy+"="+p2);
		System.out.println("area =" +aa);
	}
	public static void test2() {
		System.out.println();
		System.out.println("*********** test 2 ***********");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		
		
		
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
				System.out.println("p1: "+p1);
				System.out.println("p2: "+p2);
				p1.add(p2);
				System.out.println("p1+p2: "+p1);
				p1.multiply(p2);
				System.out.println("(p1+p2)*p2: "+p1);
				String s1 = p1.toString();
		

	}
}
