package es.ull.etsii.pai.prct9.geometry;

//Interfaz que permite conocer si el objeto implicito envuelve al explicito
public interface Envelop {
	boolean envelops(Interceptable object);
}
