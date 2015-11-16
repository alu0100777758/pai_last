package es.ull.etsii.pai.practicafinal.editor;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;

public class Editor {
	public static final String[] MAPS = { "0random", "caida.rvsbm",
			"Cholazos.rvsbm", "elreydelacolina.rvsbm", "esto.rvsbm",
			"esto2.rvsbm", "hookAdventure.rvsbm", "lanzacohetes.rvsbm",
			"hook.rvsbm", "M.rvsbm", "M2.rvsbm", "movimiento.rvsbm",
			"MuerteUzi.rvsbm", "MuerteUzi2.rvsbm", "random.rvsbm",
			"UnaCaja.rvsbm" };
	public static boolean rebuildFiles = true;

	public static void main(String[] args) {
		checkFileSystem();
		EditorFrame frame = new EditorFrame();
		frame.setTitle("Red VS Blue Editor");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
	}

	public static void checkFileSystem() {
		String separator = System.getProperty("file.separator");
		String dirpath = System.getProperty("user.dir");
		// directorio texturas
		File dir = new File(dirpath + separator + "textures");
		dir.mkdir();
		// directorio de mapas
		dir = new File(dirpath + separator + "maps");
		dir.mkdir();
		if (isRebuildFiles()) {
			for (String map : MAPS) {
//				System.out.println(map);
//				ResourceManager.getInstance().extractFromJar("Recursos/maps/"+map, dirpath+separator+"maps");
				try {
//					BvsR_Map loadedMap = BvsR_Map.load("Recursos/maps/"+map);
					BvsR_Map loadedMap = BvsR_Map.load(BvsR_Map.class.getClassLoader().getResource("maps/"+map));
					saveMap(loadedMap, "maps/"+map);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void saveMap(BvsR_Map loaded, String string) {
		loaded.markForTexture();
		try (OutputStream file = new FileOutputStream(string);
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(loaded);
		} catch (IOException ex) {
			System.out.println("error");
			ex.printStackTrace();
		}

	}
	public static boolean isRebuildFiles() {
		return rebuildFiles;
	}

	public static void setRebuildFiles(boolean rebuildFiles) {
		Editor.rebuildFiles = rebuildFiles;
	}

}