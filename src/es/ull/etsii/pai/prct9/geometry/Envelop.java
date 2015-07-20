package es.ull.etsii.pai.prct9.geometry;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
//Interfaz que permite conocer si el objeto implicito envuelve al explicito
public interface Envelop {
	boolean envelops(Interceptable object);
}
