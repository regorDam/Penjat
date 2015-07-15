
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import jdk.nashorn.internal.runtime.JSType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bc
 */
public class cPenjat {

    private String paraula;
    private cArxiu arxiu;
    private ArrayList<String> dades;
    private ArrayList<String> llestresOk;
    private String pintat;
    private int intents;

    public cPenjat() {
        this.arxiu = new cArxiu();
        this.llestresOk = new ArrayList<>();
        this.intents = 12;
        carregar();
    }

    private boolean carregar() {
        dades = arxiu.llegirDades();
        nouJoc();
        return true;
    }

    private void randomSelect() {
        int tamany = dades.size();
        Random rnd = new Random();
        int var = (int) (rnd.nextDouble() * tamany + 1);
        while (dades.get(var).length() < 4 || dades.get(var).length() > 10) {
            var = (int) (rnd.nextDouble() * tamany + 1);
        }
        paraula = dades.get(var);
    }

    public void nouJoc() {
        randomSelect();
        pintat = pintaEspais();
        llestresOk.clear();
        intents = 12;
        System.out.println(paraula);
    }

    public String pintaEspais() {
        String espais = "";
        try {
            for (char c : paraula.toCharArray()) {
                espais = espais + " _ ";
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return espais;
    }

    ///////////////////////////////////////////////
    public String pintaEspais(String p, char var) {
        String espais = p;
        espais = espais + var;
        return espais;
    }

    public String pintaEspais(String p, ArrayList<String> var) {
        String espais = p;
        for (String s : var) {
            for (char c : s.toCharArray()) {
                espais = espais + c;
            }
        }

        return espais;
    }

    public String pintaEspais(String p) {
        String espais = p;
        espais = espais + " _ ";
        return espais;
    }

    /////////////////////////////////////////////////
    public boolean checkChar(String value) {
        boolean check = false;
        try {
            if (!JSType.isNumber(value)) {
                if (value.length() == 1) {
                    check = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return check;
    }

    public boolean lletraInParaula(String value) {
        boolean check = false;
        String print = "";
        char lletra = value.toCharArray()[0];
        for (char c : paraula.toCharArray()) {
            if (c == lletra) {
                llestresOk.add("" + c);
                print = test();
                check = true;
            }
        }
        pintat = print;
        if (!check) {
            intents--;
        }
        return check;
    }

    public String getPintat() {
        return this.pintat;
    }

    public int getIntents() {
        return this.intents;
    }
    public String getParaula(){
        return this.paraula;
    }

    public boolean getWin() {
        return llestresOk.size() == paraula.length();
    }

    public String test() {
        String test = "";
        char[] test2 = paraula.toCharArray();
        char[] pintatResult = new char[paraula.length()];
        for (int x = 0; x < paraula.length(); x++) {
            for (String comp : llestresOk) {
                if (test2[x] == comp.toCharArray()[0]) {
                    pintatResult[x] = test2[x];
                }
            }
        }
        for (int x = 0; x < pintatResult.length; x++) {
            if (!(test2[x] == pintatResult[x])) {
                test2[x] = '_';
            }
        }
        for (int x = 0; x < test2.length; x++) {
            test = test + " " + test2[x];
        }
        return test;
    }

    public void guardarScore(String player) {
        Date sysDate = new Date();
        String dateString = sysDate.toString();
        ArrayList<String> dato = new ArrayList<String>() {
            {
                arxiu.llegirDades(player).stream().forEach((line) -> {
                    add(line);
                });
            //////V1/////
               /* add(player);
                add(dateString);
                add(""+getIntents());
            ////////V2///// */  
                add(player +";"+dateString+";"+getIntents());
            }
        };

        arxiu.escriureDades(dato);
    }
}
