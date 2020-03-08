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

//Lisan antud liidesed, sest need on vajalikud, et programm töötaks.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import static java.lang.Math.abs;

public class Kodu4a {
    /**
     * Antud meetod tagastab 12 korda 4 maatriksi, kus on i-ndas reas iga i-nda kuu keskmine temperatuur, suurim aasta temperatuur, vähim aasta temperatuur ning suurima ja vähima temperatuuri vahe.
     * @param fail - Argumendiks annan meetodile sõnena lähteandmete faili kasutatee.
     * @return - Meetod tagastab sõnede maatriksi.
     * @throws IOException - Lisan meetodile juurde selle täpsustuse, et meetod saaks aru, juhul kui faili avamisega on probleeme, siis see viskab ette vea, mis näitab, et tekib probleem faili avamisega.
     */
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
                maatriks[i][0] = String.valueOf(keskmineTemperatuur);
                maatriks[i][1] = String.valueOf(suurim);
                maatriks[i][2] = String.valueOf(vähim);
                maatriks[i][3] = String.valueOf(vahe);
                i++;
                if (kuu < 9){
                    kuu++;
                }else{
                    kuu = 0;
                }
                päevadeArv = 0;
                temperatuurideSumma = 0;
                temperatuurideSumma += Double.parseDouble(osad[2]);
                suurim = Integer.MIN_VALUE;
                vähim = Integer.MAX_VALUE;
            }
        }
        vahe = suurim - vähim;
        keskmineTemperatuur = temperatuurideSumma/ (päevadeArv);
        maatriks[i][0] = String.valueOf(keskmineTemperatuur);
        maatriks[i][1] = String.valueOf(suurim);
        maatriks[i][2] = String.valueOf(vähim);
        maatriks[i][3] = String.valueOf(vahe);
        return  maatriks;
    }

    static void vormistus(String[][] maatriks){
        String [] kuud = {"jaanuar", "veebruar", "märts", "aprill", "mai", "juuni", "juuli", "august", "september", "oktoober", "november", "detsember"};
        for (int i = 0; i < 12; i++) {
            System.out.printf("%10s %10.3f %10.3f %10.3f %10.3f", kuud[i], Double.parseDouble(maatriks[i][0]), Double.parseDouble(maatriks[i][1]), Double.parseDouble(maatriks[i][2]), Double.parseDouble(maatriks[i][3]));
            System.out.println();
        }
    }

    static double keskmisteKeskmine(String[][] maatriks){
        double keskmisteKeskmine = 0;
        for (int i = 0; i < 12; i++) {
            keskmisteKeskmine += Double.parseDouble(maatriks[i][0]);
        }
        return keskmisteKeskmine / 12;
    }

    static String lähimKuu(String[][] maatriks){
        double keskmine = keskmisteKeskmine(maatriks);
        String lähimkuu = "tere";
        double vahe = Integer.MAX_VALUE;
        String [] kuud = {"jaanuar", "veebruar", "märts", "aprill", "mai", "juuni", "juuli", "august", "september", "oktoober", "november", "detsember"};
        for (int i = 0; i < 12; i++) {
            if (abs(keskmine - Double.parseDouble(maatriks[i][0])) < vahe){
                vahe = keskmine - Double.parseDouble(maatriks[i][0]);
                lähimkuu = kuud[i];
            }
        }
        return lähimkuu;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Kodutöö nr 4a.                          Programmi väljund");
        System.out.println("=========================================================:" + "\n");
        System.out.println();
        String fail = "C:\\Users\\demidov\\IdeaProjects\\Programmeerimisharjutused\\src\\temperatuurid2018.txt";
        if (Character.getNumericValue(fail.charAt(76)) == 8){
            System.out.println("       AASTA 2018 TEMPERATUURIDE TABEL KUUDE LÕIKES");
        }else{
            System.out.println("       AASTA 2019 TEMPERATUURIDE TABEL KUUDE LÕIKES");
        }
        System.out.println("    E-ilmajaama (Tartus füüsikahoone katusel) andmetel\n");
        System.out.println("             keskmine     suurim      vähim       vahe");
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