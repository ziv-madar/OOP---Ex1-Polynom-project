
package Ex1;

import java.util.Comparator;
import java.util.Iterator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
		
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) {
		s = s.toLowerCase();
		if(!s.contains("x")) {
			this._coefficient = Double.parseDouble(s);
			this._power = 0;
		}
		else {
			String [] str0 = s.split("x");
			if(s.charAt(0)=='x'&&s.length()>=2) {
				this._coefficient = 1;
				String [] str3=s.split("\\^");
				this._power = Integer.parseInt(str3[1]);
			}
			else if(str0.length == 0) {
				this._coefficient = 1;
				this._power = 1;
			}
			else if(str0.length == 1) {
				if(str0[0].equals("-")) {
					this._coefficient = -1;
					this._power = 1;
				}
				else {
					this._coefficient = Double.parseDouble(str0[0]);
					this._power = 1;
				}
			}
			else {

				String [] str1 = s.split("x");
				if(str1[0].equals("-")) {
					this._coefficient = -1;
					String [] str3=s.split("\\^");
					this._power = Integer.parseInt(str3[1]);

				}
				else {
					this._coefficient = Double.parseDouble(str1[0]);

					String [] str2=s.split("\\^");


					this._power = Integer.parseInt(str2[1]);
					if(this._power<0) {
						this._coefficient = -999;
						this._power = 0;	
					}
				}
			}

		}
	}
	public void add(Monom m) {
		if(this._power != m._power)
			System.out.println("Error - Power not identity cannot be added");
		else {
			this._coefficient = this._coefficient+m._coefficient;
			//		System.out.println(this._coefficient+"x^"+this._power);
		}		
	}

	public void multipy(Monom d) {
		this._coefficient = this._coefficient*d._coefficient;
		this._power = this._power+d._power;
		//		System.out.println(this._coefficient+"x^"+this._power);
	}
	

	public String toString() {
		if(this._power < 0) return "Erorr";
		if(this._coefficient==0) return "0";
		if(this._power==0) return ""+this._coefficient;
		if(this._power==1) return ""+this._coefficient+"x";
		String ans = this._coefficient+"x^"+this._power;
		return ans;
	}
	
	public boolean equals(Object other) {

		if(other instanceof Monom) {
			Monom e = ((Monom)other);
			if(Math.abs(e.get_coefficient()) < EPSILON && Math.abs(this.get_coefficient()) < EPSILON) return true;
			return Math.abs(e.get_coefficient()-this.get_coefficient())<EPSILON && Math.abs(e.get_power()-this.get_power()) < EPSILON;
		}
		return false;
		
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		function f = new Monom(s);
		
		return f;
	}
	@Override
	public function copy() {
		function f = new Monom(this.get_coefficient(),this._power);
		return f;
	}	
	


}
