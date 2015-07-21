package es.ull.etsii.pai.common;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> {
	private static final long serialVersionUID = 9032499745453854434L;
	private int head = 0;
	
	public E forward(){
		setHead(getHead()+1);
		return get();
	}
	public E backwards(){
		setHead(getHead()-1);
		return get();
	}
	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
		if(getHead()<0)
			setHead(size()-1);
		else if(getHead()>=size())
			setHead(0);
	}
	public E get() {
		return get(getHead());
	}
	public void set(E value){
		set(getHead(), value);
	}
	
}
