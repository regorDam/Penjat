/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bc
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class cArxiu {
	private File file;
        private File scoreFile;

	// constructor amb path per defecte
	public cArxiu() {
		this.file = new File("words.dat");
                this.scoreFile = new File("scores.dat");
	}
        
	public ArrayList<String> llegirDades() {
		ArrayList<String> dades = new ArrayList<String>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				dades.add(line);
			}
		} catch (Exception e) {
                        System.out.println(e.toString());
		}

		return dades;
	}
        public ArrayList<String> llegirDades(String AllVALUE) {
		ArrayList<String> dades = new ArrayList<String>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(scoreFile));
			while ((line = br.readLine()) != null) {
				dades.add(line);
			}
		} catch (Exception e) {
                        System.out.println(e.toString());
		}

		return dades;
	}

	// boolean per saber si el metode a funcionat correctament
	public boolean escriureDades(ArrayList<String> dades) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(scoreFile));
			for (int x = 0; x < dades.size(); x++) {
				osw.write(dades.get(x));
				osw.write('\n');
			}
			osw.flush();
			osw.close();
			System.out.println("arxiu score creat correctament");
			return true;
		} catch (Exception e) {
                    System.out.println(e.toString());
                    return false;
		}
        }
        public void createFile(File file){
            try {
                OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file));
                osw.flush();
                osw.close();
                System.out.println("arxiu words creat correctament");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

}
