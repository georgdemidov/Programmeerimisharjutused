import java.util.ArrayList;
import java.util.List;

/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 * Teema:
 * 5. lähima algarvuringi leidmine lähtekohast a väiksemate arvude seast
 * tsüklite abil.
 *
 * Autor: Georg Demidov
 *
 *****************************************************************************/
public class Kodu1a {

    //Kontrollin antud meetodiga, kas arv on algarv või mitte ning tagastan muutuja "algarv", mis ütleb, kas antud arv on algarv(tõsi või väär).
    static boolean algarvulisuseKontroll(int arv) {
        boolean algarv = false;
        return algarv;
    }

    //Leian algarvu järgu.
    static int leiaJärk(int n) {
        int järk = 1;
        while (n >= Math.pow(10, järk)) {
            järk += 1;
        }
        return järk;
    }

    //Meetod eemaldaViimane, eemaldab madalaima järguga numbru arvust.
    static int eemaldaViimane(int n) {
        return n / 10;
    }

    //Meetod leiaViimane, leiab madalaima järguga numbri arvus.
    static int leiaViimane(int n) {
        return n % 10;
    }

    //Antud meetodiga, teen kindlaks, milline algarv on suurim algarvuringis ning tagastan selle.
    static int suurimaAlgarvuTagastamine(int algarv) {
        int suurimAlgarv = 0;
        int pikkus = leiaJärk(algarv) - 1;
        //Selle tsükliga, käin läbi kõik algarvuringi arvud, et teha kindlaks milline arv on suurim antud algarvuringis.
        for (int i = leiaJärk(algarv); i > 0; i--) {
            int viimane = leiaViimane(algarv);
            algarv = eemaldaViimane(algarv) + viimane * ((int) Math.pow(10, pikkus));
            if (suurimAlgarv < algarv) {
                suurimAlgarv = algarv;
            }
        }
        return suurimAlgarv;
    }

    //Antud meetodiga kontrollin, kas algarvul leidub algarvuring ning tagastan muutuja "a", mis ütleb, kas algarvul leidub algarvuring või mitte(tõsi või väär).
    static boolean algarvuringiLeidumine(int algarv) {
        boolean a = true;
        int pikkus = leiaJärk(algarv) - 1;
        //Antud tsükkel töötab sama mitu korda, kui on täisarvu "algarv" järk.
        for (int i = leiaJärk(algarv); i > 0; i--) {
            int viimane = leiaViimane(algarv);
            //Muudan muutujat "algarv" tõstes viimase numbri ette ning pärast kontrollin, kas uus arv on ka algarv.
            algarv = eemaldaViimane(algarv) + viimane * ((int) Math.pow(10, pikkus));
            if (algarvulisuseKontroll(algarv)) {
                a = true;
            } else {
                a = false;
                break;
            }
        }
        return a;
    }

    //Leian 5. suurimat algarvuringi, mis on lähtekohast "arv" väiksemate arvude hulgas.
    static void viieAlgarvuringiLeidmine(int arv) {
        System.out.println("Leitud algarvuringid:");
        //Selle tsükli abil, käin meetodi läbi 5. korda, et leida viis algarvuringi.
        for (int algarvuringideLoendur = 1; algarvuringideLoendur <= 5; algarvuringideLoendur++) {
            //Selle tsükliga, käin läbi kõik arvud, mis on väiksemad argumendist "arv", nii kaua, kuni ei leia algarvuringi.
            for (int i = arv; i > 1; i--) {
                arv--; //Lahutan täisarvust "arv" pidevalt ühe, selleks, et tsükkel saaks jätkata enda tööd sellest arvust, kus viimati leidus algarvuring.
                if (algarvuringiLeidumine(i)) {
                    if (suurimaAlgarvuTagastamine(i) > i) {
                        algarvuringideLoendur--;
                    } else {
                        System.out.println("suurim " + algarvuringideLoendur + ". ringis: " + suurimaAlgarvuTagastamine(i));
                    }
                    break;
                }
            }
        }
    }

    //Peameetod, milles samuti kontrollin, kas meetodid töötavad korrektselt. Samuti on peameetodisse koondatud ülesande vormistuslik osa.
    public static void main(String[] args) {
        int lähtekoht1 = 106;
        int lähtekoht2 = 4000;
        System.out.println("Kodutöö nr 1a.                          Programmi väljund");
        System.out.println("=========================================================:" + "\n");
        System.out.println("Antud lähtekoht: " + lähtekoht1);
        viieAlgarvuringiLeidmine(lähtekoht1);
        System.out.println();
        System.out.println("Antud lähtekoht: " + lähtekoht2);
        viieAlgarvuringiLeidmine(lähtekoht2);
        System.out.println("\n" + "=========================================================.");
        System.out.println("Georg Demidov                     " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}