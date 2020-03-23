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
        //Loon maatriksi suurusega 12 korda 4 ning muud vajaliku muutujad.
        String[][] maatriks = new String[12][4];
        double temperatuurideSumma = 0;
        double keskmineTemperatuur = 0;
        double suurim = Integer.MIN_VALUE;
        double vähim = Integer.MAX_VALUE;
        double vahe = 0;
        int i = 0;
        int kuu = 1;
        int päevadeArv = 0;
        //Muutuja lugeja abil, loen failidest sisse kõik selle read ning salvestan need muutujasse lugeja.
        BufferedReader lugeja = new BufferedReader(new FileReader(fail));
        //Massiivi temperatuuridJaKuupäevad loon selleks, et muutujast lugeja viia kõik read sellesse massiivi.
        ArrayList<String> temperatuuridJaKuupäevad = new ArrayList<>();
        while (true) {//Tsükkel töötab nii kaua, kuni selle sees mitte rakendada meetodit "break".
            //Muutuja rida salvestab uue faili rea sõnena enda sisse.
            String rida = lugeja.readLine();
            //Kui järgmist rida pole lõpetab tsükkel töö.
            if (rida == null) {
                break;
            //Vastasel juhul lisab tsükkel massiivi faili rea sõnena.
            } else {
                if (!rida.equals("") && rida.charAt(0) == '2') {
                    temperatuuridJaKuupäevad.add(rida);
                }
            }
        }
        //Loon endali listi list, mis saab endale samad väärtused, mis massiiv temperatuuridJaKuupäevad. Seda tegin selleks, sest listiga on mugavam töötada tsükli sees.
        ListIterator<String> list = temperatuuridJaKuupäevad.listIterator();
        while (list.hasNext()){//Tsükkel töötab nii kaua, kuni listis on olemas järgmine element.
            päevadeArv++;
            String rida = list.next();
            //Eraldan iga rea elemendid üksteisest, selleks, et temperatuur ja kuupäevad oleksid eraldi.
            String[] osad = rida.split(" ");
            int sümbol = Character.getNumericValue(osad[0].charAt(6));
            //Antud tinglausesse sisenetakse, siis kui praguse kuupäeva kuu on sama, mis muutuja kuu väärtus.
            if (sümbol == kuu){
                //Nii kaua kuni kestab praegune kuu lisatakse muutujale temperatuurideSumma pidevalt iga kuupäeava temperatuur.
                temperatuurideSumma += Double.parseDouble(osad[2]);
                //Antud tinglausetega kontrollitakse ega praegune temperatuur ei ole suurem või väiksem kui eelnev suurim või vähim temperatuur. Sellega leitakse iga kuu suurim ja vähim temperatuur.
                if(Double.parseDouble(osad[2]) < vähim){
                    vähim = Double.parseDouble(osad[2]);
                }
                if (Double.parseDouble(osad[2]) > suurim){
                    suurim = Double.parseDouble(osad[2]);
                }
            }else{
                //Leian iga kuu suurima ja vähima temperatuuride vahe.
                vahe = suurim - vähim;
                //Leian keskmise temperatuuri.
                keskmineTemperatuur = temperatuurideSumma / (päevadeArv);
                //Lisan vastavalt iga kuu vajalikud andmed maatriksisse.
                maatriks[i][0] = String.valueOf(keskmineTemperatuur);
                maatriks[i][1] = String.valueOf(suurim);
                maatriks[i][2] = String.valueOf(vähim);
                maatriks[i][3] = String.valueOf(vahe);
                i++;
                //Selleks, et vältida tsüklis vea tekkimist, kui jõuab kätte 10. kuu, muudan muutja kuu 0-ks.
                if (kuu < 9){
                    kuu++;
                }else{
                    kuu = 0;

                }
                //Kõik muutujate väärtused viin tagasi algsetele, sest iga kuu puhul andmed erinevad.
                päevadeArv = 0;
                temperatuurideSumma = 0;
                temperatuurideSumma += Double.parseDouble(osad[2]);
                suurim = Integer.MIN_VALUE;
                vähim = Integer.MAX_VALUE;
            }
        }
        //Kuna tsükli sees ma ei lisa detsebri kuu andmeid maatriksisse, ent leian need, siis pean viimase kuu sisestama maatriksisse tsüklist väljas pool.
        vahe = suurim - vähim;
        keskmineTemperatuur = temperatuurideSumma/ (päevadeArv);
        maatriks[i][0] = String.valueOf(keskmineTemperatuur);
        maatriks[i][1] = String.valueOf(suurim);
        maatriks[i][2] = String.valueOf(vähim);
        maatriks[i][3] = String.valueOf(vahe);
        return  maatriks;//Tagastan maatriksi.
    }

    /**
     * Antud meetod väljastab andmed vastavalt nõuetele- ümardab kolme komakohani ja jätab andmete vahele vajalikud vahed. Rakendatud on "printf" meetodit.
     * @param maatriks - Meetod võtab argumendiks sõne maatriksi, mille ta saab meetodist temperatuuriAndmeteMaatriks().
     * Meetod ei tagasta mitte midagi, sest selle meetodi abil lihtsalt kuvatakse maatriksi andmed vajalikul kujul ekraanile.
     */
    static void vormistus(String[][] maatriks){
        //Loon kuude massiivi, et iga kuu andmete jaoks samuti ekraanile kuvada ka antud kuu nimetus.
        String [] kuud = {"jaanuar", "veebruar", "märts", "aprill", "mai", "juuni", "juuli", "august", "september", "oktoober", "november", "detsember"};
        //Tsükli ees asendan ka punktid komadega.
        for (int i = 0; i < 12; i++) { //Tsükkel töötab 12 korda ehk sama palju kui aastas on kuid.
            System.out.print(String.format("%10s", kuud[i]));
            System.out.print(String.format("%11.3f", Double.parseDouble(maatriks[i][0])).replace(".", ","));
            System.out.print(String.format("%11.3f", Double.parseDouble(maatriks[i][1])).replace(".", ","));
            System.out.print(String.format("%11.3f", Double.parseDouble(maatriks[i][2])).replace(".", ","));
            System.out.print(String.format("%11.3f", Double.parseDouble(maatriks[i][3])).replace(".", ","));
            System.out.println();
        }
    }

    /**
     * Meetod keskmisteKeskmine leiab iga kuu keskmiste keskmise.
     * @param maatriks - Meetod võtab argumendiks sõne maatriksi, mille ta saab meetodist temperatuuriAndmeteMaatriks().
     * @return - Meetod tagastab ujukomaarvu, milleks on kuude keskmiste keskmine.
     */
    static double keskmisteKeskmine(String[][] maatriks){
        double keskmisteKeskmine = 0;
        for (int i = 0; i < 12; i++) { //Tsükkel töötab 12 korda ehk sama palju kui aastas on kuid.
            keskmisteKeskmine += Double.parseDouble(maatriks[i][0]);
        }
        return keskmisteKeskmine / 12;
    }

    /**
     * Meetod lähimKuu leiab kuu, mille keskmine temperatuur oli lähim kõikide kuude keskmisele temperatuurile.
     * @param maatriks - Meetod võtab argumendiks sõne maatriksi, mille ta saab meetodist temperatuuriAndmeteMaatriks().
     * @return - Meetod tagastab sõne, milleks on kuu, mille keskmine temperatuur oli lähim kõikide kuude keskmisele temperatuurile.
     */
    static String lähimKuu(String[][] maatriks){
        double keskmine = keskmisteKeskmine(maatriks);
        String lähimKuu = "tere";
        double vahe = Integer.MAX_VALUE;
        String [] kuud = {"jaanuar", "veebruar", "märts", "aprill", "mai", "juuni", "juuli", "august", "september", "oktoober", "november", "detsember"};
        for (int i = 0; i < 12; i++) { //Tsükkel töötab 12 korda ehk sama palju kui aastas on kuid.
            //Kui on kuu, mille keskmine on lähemal aasta keskmisele, võrreldes eelnevate kuudega, siis muutuja lähimKuu saab endale vastava kuu nimetuse.
            if (abs(keskmine - Double.parseDouble(maatriks[i][0])) < vahe){
                vahe = keskmine - Double.parseDouble(maatriks[i][0]);
                lähimKuu = kuud[i];
            }
        }
        return lähimKuu;//Meetod tagastab kuu, mille keskmine temperatuur oli lähim kõikide kuude keskmisele temperatuurile
    }

    //Peameetod, kuhu on koondatud meetodite testimine kui ka vormistuslik osa.
    public static void main(String[] args) throws IOException {
        System.out.println("Kodutöö nr 4a.                          Programmi väljund");
        System.out.println("=========================================================:" + "\n");
        System.out.println();
        String fail = "C:\\Users\\Georg\\IdeaProjects\\Programmeerimisharjutused\\src\\temperatuurid2019.txt";
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
        System.out.print("Keskmiste keskmine: " + String.format("%.3f", keskmisteKeskmine(temperatuuriAndmeteMaatriks(fail))).replace(".", ","));
        System.out.println();
        System.out.println("Sellele lähima keskmisega kuu on " + lähimKuu(temperatuuriAndmeteMaatriks(fail)) + ".");
        System.out.println("\n" + "=========================================================.");
        System.out.println("Georg Demidov                     " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}