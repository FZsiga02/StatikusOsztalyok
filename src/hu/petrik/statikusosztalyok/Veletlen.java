package hu.petrik.statikusosztalyok;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class Veletlen {
    private Veletlen(){}

    private static final Random rnd = new Random();
    private static final List<String> vezNevek = feltolt("files/veznev.txt");
    private static final List<String> ferfiKerNevek = feltolt("files/ferfikernev.txt");;
    private static final List<String> noiKerNevek = feltolt("files/noikernev.txt");;

    private static List<String> feltolt(String fajlnev) {
        List<String> lista = new ArrayList<>();
        try {
            Scanner file = new Scanner(new File(fajlnev));
            while(file.hasNext()) {
                String sor = file.nextLine();
                lista.add(sor);
            }
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public static int velEgesz(int min, int max){
        return rnd.nextInt(max - min + 1) + min;
    }

    public static char velKarakter(char min, char max){
        return (char) velEgesz(min, max);
    }

    public static String velVezetekNev(){
        return  vezNevek.get(rnd.nextInt(vezNevek.size()));
    }

    /**
     * Véletlen magyar keresztnév generálása
     * @param nem A generált keresztnév neme. Férfi esetén true, Nő esetén false.
     * @return A generált keresztnév
     */
    public static String velKeresztNev(boolean nem){
        String keresztNev;
        if (nem){
            keresztNev = velFerfiKeresztNev();
        } else {
            keresztNev = velNoiKeresztNev();
        }
        return keresztNev;
    }

    private static  String velFerfiKeresztNev(){
        return ferfiKerNevek.get(rnd.nextInt(ferfiKerNevek.size()));
    }

    private static  String velNoiKeresztNev(){
        return noiKerNevek.get(rnd.nextInt(noiKerNevek.size()));
    }

    /**
     * Véletlen magyar név generálása
     * @param nem A generált név neme. Férfi esetén true, Nő esetén false.
     * @return A generált név
     */
    public static  String velTeljesNev(boolean nem){
        return velVezetekNev() + " " + velKeresztNev(nem);
    }

    public static String velDatum (int ev1, int ev2){
        String year = String.valueOf(rnd.nextInt(ev2- ev1) + ev1);
        String month = String.valueOf(rnd.nextInt(12 - 1) + 1);
        String day;
        if (Integer.valueOf(month) == 4 || Integer.valueOf(month) == 6 || Integer.valueOf(month) == 9 || Integer.valueOf(month) == 11){
            day = String.valueOf(rnd.nextInt(30 - 1) + 1);
        } else if (Integer.valueOf(month) == 2){
            day = String.valueOf(rnd.nextInt(28 - 1) + 1);
        } else {
            day = String.valueOf(rnd.nextInt(31 - 1) + 1);
        }
        return String.valueOf(year + "-" + month + "-" + day );
    }

    public static String velEmail (String nev){
        int eldont = rnd.nextInt(1 - 0) + 0;
        boolean neme;
        if (eldont == 0){
            neme = true;
        } else {
            neme = false;
        }
        nev = Normalizer.normalize(velTeljesNev(neme), Normalizer.Form.NFD);
        nev = nev.replaceAll("[\\s\\p{InCombiningDiacriticalMarks}]", "");
        int sorszam = rnd.nextInt(99 - 1) + 1;
        return nev.toLowerCase() + sorszam + "@gmail.com";
    }

    public static String velMobil(){
        int eldont = rnd.nextInt(4 - 1) + 1;
        int szolgaltato;
        if (eldont == 1){
            szolgaltato = 20;
        } else if (eldont == 2){
            szolgaltato = 30;
        } else if (eldont == 3){
            szolgaltato = 70;
        } else {
            szolgaltato = 50;
        }
        int szam1 = rnd.nextInt(999 - 100) + 100;
        int szam2 = rnd.nextInt(99 - 10) + 10;
        int szam3 = rnd.nextInt(99 - 10) + 10;

        return "+36" + " (" + szolgaltato + ") " + szam1 + "-" + szam2 + "-" + szam3;
    }
}
