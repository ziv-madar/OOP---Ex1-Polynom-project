package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

import jdk.nashorn.api.tree.BinaryTree;

public class ComplexFunction implements complex_function {

	private Node root;


	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}

	class Node 
	{ 
		private Operation oper;
		private function f;
		private Node left, right; 



		public Operation getOper() {
			return oper;
		}

		public void setOper(Operation oper) {
			this.oper = oper;
		}

		public function getF() {
			return f;
		}

		public void setF(function f) {
			this.f = f;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node(Operation oper,function left,function right) 
		{ 
			this.oper = oper; 
			this.left = new Node(left);
			this.right = new Node(right);

		}

		public Node(String oper,Node left,Node right) 
		{ 
			switch (oper) {
			case "plus": this.oper = Operation.Plus;
			break;
			case "mul": this.oper = Operation.Times;
			break;
			case "div": this.oper = Operation.Divid;
			break;
			case "comp": this.oper = Operation.Comp;
			break;
			case "max": this.oper = Operation.Max;
			break;
			case "min": this.oper = Operation.Min;
			break;
			default: this.oper = Operation.Error;
			}
			this.left = left;
			this.right = right;

		}
		public Node(Operation oper,Node left,Node right) {
			this.oper = oper;
			this.left = left;
			this.right = right;
		}

		public Node(function f) 
		{
			this.f = f;
			this.oper = Operation.None;
			left = right = null;
		}

		public Node(Operation oper) {
			this.oper = oper;
		}
	}

	public ComplexFunction(function f) {
		this.root = new Node(f);	
	}
	public ComplexFunction(Node n) {
		this.root = n;	
	}
	public ComplexFunction(String op,function f1,function f2) throws Exception {
		switch (op) {
		case "plus": this.root = new Node(Operation.Plus,f1,f2);
		break;
		case "mul": this.root = new Node(Operation.Times,f1,f2);
		break;
		case "div": this.root = new Node(Operation.Divid,f1,f2);
		break;
		case "comp": this.root = new Node(Operation.Comp,f1,f2);
		break;
		case "max": this.root = new Node(Operation.Max,f1,f2);
		break;
		case "min": this.root = new Node(Operation.Min,f1,f2);
		break;
		default: throw new Exception("You wrote a non-existent operator");	
		}
		this.root.left = new Node(f1);
		this.root.right = new Node(f2);
	}



	public ComplexFunction() {
	}


	@Override
	public double f(double x)  {
		try {
			return f(root,x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public double f(Node n,double x) throws Exception {
		if(n.oper != null) {
			switch (n.oper) {
			case Plus: return f(n.left,x)+f(n.right,x);

			case Times: return f(n.left,x)*f(n.right,x); 	


			case Divid:
				double denominator = f(n.right,x);
				if(denominator == 0) {
					n.oper = Operation.Error;
					throw new Exception("Can't divide by zero");
				}
				return f(n.left,x)/denominator; 

			case Comp: return f(n.left,f(n.right,x));

			case Max: return Math.max(f(n.left,x), f(n.right,x));

			case Min: return Math.min(f(n.left,x), f(n.right,x));

			case None: return n.f.f(x);

			default: throw new Exception("The node holds an error operator");

			}
		}
		else {
			throw new Exception("The node is empty");
		}

	}

	@Override
	public function initFromString(String s)  {
		ComplexFunction ans = new ComplexFunction();
		ans.root = initFromString1(s);
		return ans;
	}



	public Node initFromString1(String s)  {
		if(!(s.contains("(") && s.contains(")"))){
			Polynom p = new Polynom();
			return new Node(p.initFromString(s));
		}
		else {
			int count = 1;
			int first = s.indexOf("(");
			int comma1 = s.indexOf(",");
			String str = s.substring(first+1, comma1);
			if(!str.contains("(")) {
				String oper = s.substring(0, first);
				String left = s.substring(first+1, comma1);
				String right = s.substring(comma1+1, s.length()-1);
				Node ans = new Node(oper,new Node(new Polynom(left)),initFromString1(right));
				return ans;
			}
			for (int i = first+1; i < s.length(); i++) {
				if(s.charAt(i) == '(') count++;
				else if(s.charAt(i) == ')') {
					count--;
					if(count == 0) {
						String oper = s.substring(0, first);
						String left = s.substring(first+1, comma1);
						String right = s.substring(comma1+1, s.length()-1);
						Node ans = new Node(oper,new Node(new Polynom(left)),new Node(new Polynom(right)));
						return ans;	
					}
					else if(count == 1) {
						String oper = s.substring(0, first);
						String left = s.substring(first+1,i+1);
						String right = s.substring(i+2, s.length()-1);
						if(right.contains(")")){
							Node ans = new Node(oper,initFromString1(left),new Node(new Polynom(right)));
							return ans;
						}
						Node ans = new Node(oper,initFromString1(left),initFromString1(right));
						return ans;
					}
				}
			}
		}
		return null;
	}

	@Override
	public function copy()  {
		ComplexFunction f = new ComplexFunction();
		f.root = copy(this.root);
		return f;
	}


	private Node copy(Node root) {
		switch (root.oper) {
		case None: return new Node(root.f.copy());

		case Plus: return new Node(Operation.Plus,copy(root.left),copy(root.right));

		case Times: return new Node(Operation.Times,copy(root.left),copy(root.right));

		case Divid: return new Node(Operation.Divid,copy(root.left),copy(root.right));

		case Comp: return new Node(Operation.Comp,copy(root.left),copy(root.right));

		case Max: return new Node(Operation.Max,copy(root.left),copy(root.right));

		case Min: return new Node(Operation.Min,copy(root.left),copy(root.right));
		default: return null;

		}

	}
	@Override
	public void plus(function f1) {
		Node temp;
		if(f1 instanceof ComplexFunction) {
			temp = new Node(Operation.Plus,root,((ComplexFunction)f1).root);
		}
		else {
			temp = new Node(Operation.Plus,root,new Node(f1));
		}
		this.root = temp;
	}

	@Override
	public void mul(function f1) {
		Node temp;
		if(f1 instanceof ComplexFunction) {
			temp = new Node(Operation.Times,root,((ComplexFunction)f1).root);
		}
		else {
			temp = new Node(Operation.Times,root,new Node(f1));
		}
		this.root = temp;
	}

	@Override
	public void div(function f1) {
		Node temp;
		if(f1 instanceof ComplexFunction) {
			temp = new Node(Operation.Divid,root,((ComplexFunction)f1).root);
		}
		else {
			temp = new Node(Operation.Divid,root,new Node(f1));
		}
		this.root = temp;
	}

	@Override
	public void max(function f1) {
		Node temp;
		if(f1 instanceof ComplexFunction) {
			temp = new Node(Operation.Max,root,((ComplexFunction)f1).root);
		}
		else {
			temp = new Node(Operation.Max,root,new Node(f1));
		}
		this.root = temp;
	}




	@Override
	public void min(function f1) {
		Node temp;
		if(f1 instanceof ComplexFunction) {
			temp = new Node(Operation.Min,root,((ComplexFunction)f1).root);
		}
		else {
			temp = new Node(Operation.Min,root,new Node(f1));
		}
		this.root = temp;
	}

	@Override
	public void comp(function f1) {
		Node temp;
		if(f1 instanceof ComplexFunction) {
			temp = new Node(Operation.Comp,root,((ComplexFunction)f1).root);
		}
		else {
			temp = new Node(Operation.Comp,root,new Node(f1));
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
		return root.oper;
	}

	public String toString() {
		try {
			if(root == null)
				throw new Exception("The tree is empty");
			return toString(this.root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toString(Node n) throws Exception {
		switch (n.oper) {
		case Plus: return "plus( "+toString(n.left)+" , "+toString(n.right)+" )";

		case Times: return "mul( "+toString(n.left)+" , "+toString(n.right)+" )";

		case Divid: return "div( "+toString(n.left)+" , "+toString(n.right)+" )";

		case Comp: return "comp( "+toString(n.left)+" , "+toString(n.right)+" )";

		case Max: return "max( "+toString(n.left)+" , "+toString(n.right)+" )";

		case Min: return "min( "+toString(n.left)+" , "+toString(n.right)+" )";

		case None: return n.f.toString();
		default: return Operation.Error.toString();

		}
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ComplexFunction)) {
			return false;
		}
		ComplexFunction c = (ComplexFunction) obj;

		for (int i=-50;i<=50;i++) {
			double r = (Math.random()*1500)-750;
			if (this.f(r)!=c.f(r)) {
				
				return false;
			}
		}
		return true;
	}
}

