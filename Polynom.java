package Ex1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	private ArrayList <Monom> list = new ArrayList<Monom>(); 
	/**
	 * Zero (empty polynom)
	 */
	public ArrayList<Monom> getList(){
		return list;
	}
	public Polynom() {
		this.list = new ArrayList<Monom>(0);
	}
	public void setList(ArrayList<Monom> list) {
		this.list = list;
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	/**
	public Polynom(String s) {
		String [] str = s.split("\\+");
		for (int i = 0; i < str.length; i++) {
			if(str[i].contains("-")) {
				String [] str1 = str[i].split("-");
				if(str1[0].equals("")) {
					for (int j = 1; j < str1.length; j++) {
						String tmp = "-"+str1[j];
						Monom m = new Monom(tmp);
						this.add(m);

					}
				}
				else {
					Monom m  = new Monom(str1[0]);
					this.add(m);
					for (int j = 1; j < str1.length; j++) {
						String tmp = "-"+str1[j];
						m = new Monom(tmp);
						this.add(m);

					}

				}

			}
			else {
				Monom m = new Monom(str[i]);
				this.add(m);
			}

		}

	}
	**/
	public Polynom(String s) {
		String [] str = s.split(" ");
		for (int i = 0; i < str.length; i++) {
			if(str[i].charAt(0)=='+') {
				
				Monom m = new Monom(str[i].substring(1));
				this.add(m);
			}
			else {
				
				Monom m = new Monom(str[i]);
				this.add(m);
			}

		}

	}
	@Override
	public double f(double x) {
		double d = 0;
		Iterator<Monom> run = list.iterator();
		while(run.hasNext()) {
			Monom m = run.next();
			d += m.get_coefficient()*Math.pow(x, m.get_power());
		}
		return d;
	}

	@Override
	public void add(Polynom_able p1) {
		Polynom temp = (Polynom)p1.copy();
		Iterator<Monom> run = temp.iteretor();
		while(run.hasNext()) {
			Monom m = run.next();
			this.add(m);
		}

	}

	@Override
	public void add(Monom m1) {
		Iterator<Monom> run = list.iterator();
		Monom m = new Monom(m1.get_coefficient(),m1.get_power());
		while(run.hasNext()) {
			Monom_Comperator mc = new Monom_Comperator();
			Monom tmp = run.next();
			if(mc.compare(tmp, m) == 0) {
				tmp.add(m);
				if(tmp.get_coefficient() == 0) list.remove(tmp);
				return;
			}
			else if(mc.compare(tmp, m) > 0) {
				list.add(m);
				Collections.sort(list, mc);
				return;
			}
		}
		list.add(m1);	
	}

	@Override
	public void substract(Polynom_able p1) {
		Monom m = new Monom(-1,0);
		Polynom p2 = (Polynom) p1.copy();
		p2.multiply(m);
		this.add(p2);
	}

	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> run = p1.iteretor();
		Polynom org = (Polynom)this.copy();
		Polynom sum = new Polynom();
		while(run.hasNext()) {
			org.multiply(run.next());
			sum.add(org);
			org = (Polynom)this.copy();
		}
		this.list=sum.list;
	}

	public boolean equals(Object p1) {
		if(!(p1 instanceof Polynom)) return false;
		
		Iterator<Monom> run = this.iteretor();
		Iterator<Monom> run1 =((Polynom) p1).iteretor();
		while(run.hasNext() && run1.hasNext()) {
			if(!run.next().equals(run1.next())){
				return false;
			}	
		}
		if(run.hasNext()==false && run1.hasNext()==false)
			return true;
		return false;
	}

	@Override
	public boolean isZero() {
		return list.isEmpty();
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if(f(x0)*f(x1)>0) {
			System.out.println("Error");
		}
		else if(f(x0)==0) {
			return x0;

		}
		else if(f(x1) == 0) return x1;
		else {
			while(x1-x0 > 2*eps) {
				double mid = (x0+x1)/2;
				if(f(mid) == 0) return 0;
				if(f(x0)*f(mid) < 0) {
					x1 = mid;
				}
				else {
					x0 = mid;
				}
			}
			return (x0+x1)/2;
		}
		return 0;
	}

	@Override
	public Polynom_able copy() {
		Polynom ans = new Polynom();
		Iterator<Monom> run = list.iterator();
		while(run.hasNext()) {
			Monom m = run.next();
			Monom m1 = new Monom(m.get_coefficient(),m.get_power());
			ans.add(m1);
		}
		return ans;
	}

	@Override
	public Polynom_able derivative() {
		Polynom ans = new Polynom();
		Iterator<Monom> iter = iteretor();
		while (iter.hasNext()) {
			ans.add(iter.next().derivative());
		}
		return ans;

	}

	@Override
	public double area(double x0, double x1, double eps) {
		double ans = 0;
		double i = 0;
		for (i = x0; i < x1; i = i + eps) {
			double y = i + (eps / 2);
			if (f(y) > 0)
				ans = ans + (f(y) * eps);
		}
		return ans;
	}


	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> a=list.iterator();
		return a;
	}
	@Override
	public void multiply(Monom m1) {
		Iterator<Monom> run = list.iterator();
		while(run.hasNext()) {
			run.next().multipy(m1);
		}

	}
	
	@Override
	public String toString() {
		Iterator<Monom> run = list.iterator();
		String ans = "";
		if(!run.hasNext()) return Monom.ZERO.toString();
		while(run.hasNext()) {
			Monom m = run.next();	
			if(list.indexOf(m) == 0) ans = m.toString();
			else {
				if(m.toString().charAt(0) == '-') ans += (" "+m.toString());
				else ans += " +"+m.toString();
			}

		}
		return ans;
	}
	@Override
	public function initFromString(String s) {
		function f = new Polynom(s);
		return f;
		
	}




}
