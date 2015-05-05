package es.ull.etsii.pai.prct9.geometry;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */


/**
 * Clase con un conjunto de ecuaciones para el movimiento parabolico.
 * @author Sabato
 *
 */
public class EcuacionesMovimientoParabolico {
	/**
	 * Calcula la posicion en un tiempo dado. //Ojo con el signo de la gravedad.
	 * @param posInicial
	 * @param velInicial
	 * @param tiempo
	 * @param gravedad
	 * @return
	 */
	public static Point2D posicion(Point2D posInicial, Point2D velInicial, Double tiempo, Double gravedad) {
		Point2D posFinal;
		Double coordX;
		Double coordY;
		
		coordX = velInicial.x() * tiempo + posInicial.x();
		coordY = -(0.5) * gravedad * tiempo * tiempo + velInicial.y() * tiempo  + posInicial.y();
		
		posFinal = new Point2D(coordX, coordY);
		return posFinal;
	}
	/**
	 * Calcula la velocidad para un momento dado de la trayectoria.
	 * @param velInicial
	 * @param tiempo
	 * @param gravedad
	 * @return
	 */
	public static Point2D velocidadInstantanea (Point2D velInicial, Double tiempo, Double gravedad) {
		Point2D posFinal;
		Double coordX;
		Double coordY;
		
		coordX = velInicial.x();
		coordY = velInicial.y() - gravedad * tiempo;
		
		posFinal = new Point2D(coordX, coordY);
		return posFinal;
	}
	

}
