/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 3a
 * Teema:
 * Failist E-ilmajaama andmete lugemine ning vastavte meetodite koostamine
 *
 * Autor: Georg Demidov
 *
 *****************************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Kodu3a {

    static double aastaKeskmineTemperatuur(ArrayList<String> massiiv){
        double summa = 0;
        int i = 0;
        for (String rida : massiiv) {
            String[] osad = rida.split(" ");
            summa += Double.parseDouble(osad[2]);
            i++;
        }
        double keskmine = summa / i;
        return keskmine;
    }

    static double[] maksimumJaMiinimum(ArrayList<String> massiiv) {
        double miinimum = Integer.MAX_VALUE;
        double maksimum = Integer.MIN_VALUE;
        double[] tulemus = new double[2];
        for (String rida : massiiv) {
            String[] osad = rida.split(" ");
            if (Double.parseDouble(osad[2]) < miinimum) {
                miinimum = Double.parseDouble(osad[2]);
            } else if (Double.parseDouble(osad[2]) > maksimum) {
                maksimum = Double.parseDouble(osad[2]);
            }
        }
        tulemus[0] = miinimum;
        tulemus[1] = maksimum;
        return tulemus;
    }

    static double[] kuudeKeskmisedTemperatuurid(ArrayList<String> massiiv) {
        double[] tulemus = new double[12];
        for (String rida : massiiv) {
            for (int i = 0; i < 2; i++) {
                String[] osad = rida.split(" ");
            }
        }
        return tulemus;
    }

    static String päevadeVäikseimTemperatuurideVahe(ArrayList<String> massiiv) {
        int i = 0;
        String väikseimVahe = "tere";
        double summa = 0;
        double miinimum = Integer.MAX_VALUE;
        double maksimum = Integer.MIN_VALUE;
        for (String rida: massiiv) {
            i++;
            String[] osad = rida.split(" ");
            String päev = osad[0];
            String järgminePäev = massiiv.get(i);
            if (päev.charAt(6) != järgminePäev.charAt(6)){

            }
        }
        return väikseimVahe;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader("src/temperatuurid2019.txt"));
        ArrayList<String> temperatuurid = new ArrayList<>();
        while (true) {
            String rida = buffer.readLine();
            if (rida == null) {
                break;
            } else {
                if (!rida.equals("") && rida.charAt(0) == '2') {
                    temperatuurid.add(rida);
                }
            }
        }
        System.out.println(Arrays.toString(maksimumJaMiinimum(temperatuurid)));
        System.out.println(aastaKeskmineTemperatuur(temperatuurid));
    }
}