
package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions  {
	
	
	private ArrayList<function> f_list = new ArrayList();



	@Override
	public boolean add(function arg0) {
		return f_list.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return f_list.addAll(arg0);
	}

	@Override
	public void clear() {
		f_list.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return f_list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return f_list.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return f_list.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return f_list.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return f_list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return f_list.retainAll(arg0);
	}

	@Override
	public int size() {
		return f_list.size();
	}

	@Override
	public Object[] toArray() {
		return f_list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return f_list.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		String s = FileUtils.readFile(file);
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

	

}
