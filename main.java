package Ex1;

import java.util.StringTokenizer;

public class main {
	public static void main(String[] args) throws Exception {

		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
			System.out.println(cf3);
		}
		
		ComplexFunction cf4 = (ComplexFunction) cf3.copy();
        System.out.println(cf4);
        
        
//        ComplexFunction cf6 = ((ComplexFunction) cf4.initFromString("plus(plus(x,x),plus(x,x))"));
        
        ComplexFunction cf5 = ((ComplexFunction) cf4.initFromString("plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)"));
        System.out.println(cf5);
        
        ComplexFunction a = new ComplexFunction("plus",new Polynom("3x"),new Polynom("x^2"));
        ComplexFunction b = new ComplexFunction(new Polynom("x +2x +x^2"));
        
	}
	
	


}

