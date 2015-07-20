package es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TreeSet;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;


public class Ladder{
	private static final long serialVersionUID = -909949403806750885L;
	
	public final static String LADDER_FILE = "ladder.sav";
	public final static int MAX_ENTRIES = 10;
	
	private TreeSet<LadderEntry> ladder;
	private static Ladder instance = null;
	
	private Ladder() {
		try{
			loadLadder();
		}catch(Exception e){
			ladder = new TreeSet<LadderEntry>();
			saveLadder();
		}
	}
	
	
	public static Ladder getInstance() {
		if (instance == null)
			instance = new Ladder();
		return instance;
	}
	
	/**
	 * Guarda en fichero el ladder.
	 * 
	 * TODO Hace falta agregar la parte relativa del path
	 */
	public void saveLadder() {
		try (OutputStream file = new FileOutputStream(LADDER_FILE);
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(getLadder());
		} catch (IOException ex) {
			System.out.println("error");
			ex.printStackTrace();
		}
	}
	/**
	 * Carga el ladder.
	 * TODO Hace falta agregar la parte relativa del path.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void loadLadder() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream save = new ObjectInputStream(new FileInputStream(LADDER_FILE));
		TreeSet<LadderEntry> ladder;
		
		ladder = (TreeSet<LadderEntry>)save.readObject();
		this.setLadder(ladder);
		save.close();
		
	}
	
	public void insertEntry(LadderEntry entry) {
		getLadder().add(entry);
		if (getLadder().size() > MAX_ENTRIES)
			getLadder().remove(getLadder().first());
	}
	
	public ArrayList<LadderEntry> getAllEntries() {
		ArrayList<LadderEntry> result = new ArrayList<LadderEntry>();
		
		for (int i = 0; i < getLadder().size(); i++)
			result.add((LadderEntry)(getLadder().toArray()[i]));
		return result; 
	}

	public boolean canEnter (int score)  {
		if (getLadder().size() < MAX_ENTRIES)
			return true;
		if (getLadder().first().getScore() < score)
			return true;
		return false;
		
	}
	private TreeSet<LadderEntry> getLadder() {
		return ladder;
	}


	private void setLadder(TreeSet<LadderEntry> ladder) {
		this.ladder = ladder;
	}
}
