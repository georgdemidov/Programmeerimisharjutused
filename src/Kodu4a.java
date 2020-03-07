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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

import static java.lang.Math.abs;

public class Kodu4a {

    static String[][] temperatuuriAndmeteMaatriks(String fail) throws IOException{
        String[][] maatriks = new String[12][5];
        double temperatuurideSumma = 0;
        double keskmineTemperatuur = 0;
        double suurim = Integer.MIN_VALUE;
        double vähim = Integer.MAX_VALUE;
        double vahe = 0;
        int i = 0;
        int kuu = 1;
        int päevadeArv = 0;
        String [] kuud = {"jaanuar", "veebruar", "märts", "aprill", "mai", "juuni", "juuli", "august", "september", "oktoober", "november", "detsember"};
        BufferedReader lugeja = new BufferedReader(new FileReader(fail));
        ArrayList<String> temperatuurid = new ArrayList<>();
        while (true) {
            String rida = lugeja.readLine();
            if (rida == null) {
                break;
            } else {
                if (!rida.equals("") && rida.charAt(0) == '2') {
                    temperatuurid.add(rida);
                }
            }
        }
        ListIterator<String> list = temperatuurid.listIterator();
        while (list.hasNext()){
            päevadeArv++;
            String rida = list.next();
            String[] osad = rida.split(" ");
            int sümbol = Character.getNumericValue(osad[0].charAt(6));
            if (sümbol == kuu){
                temperatuurideSumma += Double.parseDouble(osad[2]);
                if(Double.parseDouble(osad[2]) < vähim){
                    vähim = Double.parseDouble(osad[2]);
                }
                if (Double.parseDouble(osad[2]) > suurim){
                    suurim = Double.parseDouble(osad[2]);
                }
            }else{
                vahe = suurim - vähim;
                keskmineTemperatuur = temperatuurideSumma / (päevadeArv);
                maatriks[i][0] = kuud[i];
                maatriks[i][1] = String.valueOf(keskmineTemperatuur);
                maatriks[i][2] = String.valueOf(suurim);
                maatriks[i][3] = String.valueOf(vähim);
                maatriks[i][4] = String.valueOf(vahe);
                i++;
                if (kuu < 9){
                    kuu++;
                }else{
                    kuu = 0;
                }
                päevadeArv = 0;
                temperatuurideSumma = 0;
                suurim = Integer.MIN_VALUE;
                vähim = Integer.MAX_VALUE;
            }
        }
        vahe = suurim - vähim;
        keskmineTemperatuur = temperatuurideSumma/ (päevadeArv);
        maatriks[i][0] = kuud[i];
        maatriks[i][1] = String.valueOf(keskmineTemperatuur);
        maatriks[i][2] = String.valueOf(suurim);
        maatriks[i][3] = String.valueOf(vähim);
        maatriks[i][4] = String.valueOf(vahe);
        return  maatriks;
    }

    static void vormistus(String[][] maatriks){
        for (int i = 0; i < 12; i++) {
            System.out.printf("%10s %10.3f %10.3f %10.3f %10.3f", maatriks[i][0], Double.parseDouble(maatriks[i][1]), Double.parseDouble(maatriks[i][2]), Double.parseDouble(maatriks[i][3]), Double.parseDouble(maatriks[i][4]));
            System.out.println();
        }
    }

    static double keskmisteKeskmine(String[][] maatriks){
        double keskmisteKeskmine = 0;
        for (int i = 0; i < 12; i++) {
            keskmisteKeskmine += Double.parseDouble(maatriks[i][1]);
        }
        return keskmisteKeskmine / 12;
    }

    static String lähimKuu(String[][] maatriks){
        double keskmine = keskmisteKeskmine(maatriks);
        String lähimkuu = "tere";
        double vahe = Integer.MAX_VALUE;
        for (int i = 0; i < 12; i++) {
            if (abs(keskmine - Double.parseDouble(maatriks[i][1])) < vahe){
                vahe = keskmine - Double.parseDouble(maatriks[i][1]);
                lähimkuu = maatriks[i][0];
            }
        }
        return lähimkuu;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Kodutöö nr 4a.                          Programmi väljund");
        System.out.println("=========================================================:" + "\n");
        System.out.println();
        System.out.println("       AASTA 2018 TEMPERATUURIDE TABEL KUUDE LÕIKES");
        System.out.println("    E-ilmajaama (Tartus füüsikahoone katusel) andmetel\n");
        System.out.println("             keskmine     suurim      vähim       vahe");
        String fail = "src/temperatuurid2018.txt";
        vormistus(temperatuuriAndmeteMaatriks(fail));
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.printf("Keskmiste keskmine: " + "%.3f", keskmisteKeskmine(temperatuuriAndmeteMaatriks(fail)));
        System.out.println();
        System.out.println("Sellele lähima keskmisega kuu on " + lähimKuu(temperatuuriAndmeteMaatriks(fail)) + ".");
        System.out.println("\n" + "=========================================================.");
        System.out.println("Georg Demidov                     " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}