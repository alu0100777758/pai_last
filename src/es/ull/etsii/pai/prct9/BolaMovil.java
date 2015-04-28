package es.ull.etsii.pai.prct9;



public class BolaMovil {

	public static void main(String[] args) {
		if(args.length != 1 ){
			System.out.println("Debe pasar como argumento el número de pixeles de cada desplazamiento");
			System.exit(1);
		}
		GameControl game = GameControl.getInstance();
		try{
			game.setActorSpeed(Integer.parseInt(args[0]));
			game.play();
		}catch(NumberFormatException e){
			System.out.println("Error en el argumento:" + e.getMessage());
		}
	}
}
