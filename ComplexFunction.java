package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

import jdk.nashorn.api.tree.BinaryTree;

public class ComplexFunction implements complex_function {
	
	Node root;
	Operation oper;
	class Node 
	{ 
	    String key; 
	    Node left, right; 
	  
	    public Node(String item) 
	    { 
	        key = ""+item; 
	        left = right = null; 
	    } 
	}
	
	public ComplexFunction(function f) {
		this.root = new Node(f.toString());	
	}
	public ComplexFunction(Node n) {
		this.root = n;	
	}
	public ComplexFunction(String op,function f1,function f2) {
		switch (op) {
		case "plus": this.root = new Node(oper.Plus.toString());
			break;
		case "mul": this.root = new Node(oper.Times.toString());
			break;
		case "div": this.root = new Node(oper.Divid.toString());
			break;
		default:
			break;
		}
		
		this.root.left = new Node(f1.toString());
		this.root.right = new Node(f2.toString());
	}
	


	@Override
	public double f(double x) {
		return f(root,x);
	}
	
	public double f(Node n,double x) {
		if(n.key.equals(oper.Plus.toString())) {
			return f(n.left,x)+f(n.right,x);
		}
		else if(n.key.equals(oper.Times.toString())) {
			return f(n.left,x)*f(n.right,x);
		}
		else if(n.key.equals(oper.Divid.toString())) {
			return f(n.left,x)/f(n.right,x);
		}
		else if(n.key.equals(oper.Comp.toString())) {
			return f(n.left,f(n.right,x));
		}
		else if(n.key.equals(oper.Max.toString())) {
			return Math.max(f(n.left,x), f(n.right,x));
		}
		else if(n.key.equals(oper.Min.toString())) {
			return Math.min(f(n.left,x), f(n.right,x));
		}
		else {
			Polynom p = new Polynom(n.key) ;
			return p.f(x);
		}
	}

	@Override
	public function initFromString(String s) {
		if(!(s.contains("(") && s.contains(")"))){
			Polynom p = new Polynom();
			return p.initFromString(s);
		}
		else {
			int start = s.indexOf("(");
			int mid = s.indexOf(",");
		}
		
		
		
		return c;
	}

	@Override
	public function copy() {
		
		return null;
	}

	@Override
	public void plus(function f1) {
		Node temp = new Node(oper.Plus.toString());
		temp.left=this.root;
		if(f1 instanceof Monom || f1 instanceof Polynom) {
		temp.right = new Node(f1.toString());
		}
		else {
		temp.right = ((ComplexFunction)f1).root;
		}
		this.root = temp;
	}

	@Override
	public void mul(function f1) {
		Node temp = new Node(oper.Times.toString());
		temp.left = this.root;
		if(f1 instanceof Monom || f1 instanceof Polynom) {
			temp.right = new Node(f1.toString());
			}
			else {
			temp.right = ((ComplexFunction)f1).root;
			}
		this.root = temp;
		
	}

	@Override
	public void div(function f1) {
		Node temp = new Node(oper.Divid.toString());
		temp.left = this.root;
		if(f1 instanceof Monom || f1 instanceof Polynom) {
			temp.right = new Node(f1.toString());
			}
			else {
			temp.right = ((ComplexFunction)f1).root;
			}
		this.root = temp;
		
	}

	@Override
	public void max(function f1) {
		Node temp = new Node(oper.Max.toString());
		temp.left = this.root;
		if(f1 instanceof Monom || f1 instanceof Polynom) {
			temp.right = new Node(f1.toString());
			}
			else {
			temp.right = ((ComplexFunction)f1).root;
			}
		this.root = temp;
		
		
	}

	@Override
	public void min(function f1) {
		Node temp = new Node(oper.Min.toString());
		temp.left = this.root;
		if(f1 instanceof Monom || f1 instanceof Polynom) {
			temp.right = new Node(f1.toString());
			}
			else {
			temp.right = ((ComplexFunction)f1).root;
			}
		this.root = temp;
		
	}

	@Override
	public void comp(function f1) {
		Node temp = new Node(oper.Comp.toString());
		temp.left = this.root;
		if(f1 instanceof Monom || f1 instanceof Polynom) {
			temp.right = new Node(f1.toString());
			}
			else {
			temp.right = ((ComplexFunction)f1).root;
			}
		this.root = temp;
		
		
	}

	@Override
	public function left() {
		ComplexFunction left = new ComplexFunction(root.left);
		return left;
	}

	@Override
	public function right() {
		ComplexFunction right = new ComplexFunction(root.right);
		return right;
	}

	@Override
	public Operation getOp() {
		return oper;
	}
	
	public String toString() {
		return toString(this.root);
	}
	
	public String toString(Node n) {
		if(n == null) return "";
		else if(n.key.equals(oper.Plus.toString())) {
			return oper.Plus+"( "+toString(n.left)+" , "+toString(n.right)+" )";
		}
		else if(n.key.equals(oper.Times.toString())) {
			return oper.Times+"( "+toString(n.left)+" , "+toString(n.right)+" )";
		}
		else if(n.key.equals(oper.Divid.toString())) {
			return oper.Divid+"( "+toString(n.left)+" , "+toString(n.right)+" )";
		}
		else {
			return n.key;
		}
	}
}
