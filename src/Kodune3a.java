/*****************************************************************************
 *										MALL-PROGRAMM
 *
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 3a
 * Teema: 
 * 		Järjendi töötlemine.
 *      Vaadeldavad järjendid saadakse andmefailist "temperatuurid2019.txt", 
 *         mille ühes reas on (tühikuga eraldatult) 
 *               kuupäev kujul aasta-kuu-päev, 	nt "2018-01-01" 
 *               kellaaeg kujul tund:min:sek, 	nt "10:15:00," (lõpus koma!)
 *               temperatuuri näit kümnendpunktiga arvu kujul, nt "0.575023584906";
 *         andmefailist saadavateks järjenditeks on: 
 *               kuupäevade järjend, kellaaegade järjend ja temperatuuride järjend. 
 *
 * 
 * Mall-programmi autor: Jüri Kiho
 * Kaasautor: Georg Demidov, programmeeris järgmised meetodid
 *          [ 0) temperatuur(String päev, String kell) ] 
 *			  1) 
 *			  2)
 *            3)
 *            4)
 *            5)
 *						--- Käitlemine käsurealt ---
 * 					Eeldus: jooksvas kaustas on 
 ' 								see fail (Kodune3a.java, utf8 vormingus),
 * 								klassfail Gen_RiduTekstifailist.class,
 * 								andmefail temperatuurid2019.txt. 
 *					Kompileerimine:
 *								>javac -encoding utf8 Kodune3a.java 
 *					Käivitamine:
 *								>java Kodune3a 
 * 
 *****************************************************************************/
 
// Kasutab generaator-klassi Gen_RiduTekstifailist 
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Kodune3a{
	
	// Kolm järjendit (massiivi), mis formeeritakse faili ridadest  
	static String[] kuupäev;
	static String[] kellaaeg;
	static double[] temperatuur;
	
	
	// Ülli Õpilase koostatud meetodid:
	// --------------------------------------------------------------NÄIDIS!
	/** Leida antud ajal mõõdetud temperatuur.
	 *  Globaalsed parameetrid: järjendid 'kuupäev', 'kellaaeg' ja 'temperatuur'.
	 * @param päev Kuupäev kujul aaaa-kk-pp.
	 * @param kell Kellaaeg kujul tt:mm:00.
	 * @return Temperatuur kuupäeval 'päev' ajal 'kell' (null, kui päev või kell on olematu/vigane)
	 */
	static Double üksTemperatuur(String päev, String kell){
		for(int i = 0; i < kuupäev.length; i++)
			if(kuupäev[i].equals(päev) && kellaaeg[i].equals(kell))
				return temperatuur[i];					
		return null;
	}//temperatuur

	static double [] maksimumJaMiinimum(double[] temperatuurid){
		double miinimum = Integer.MAX_VALUE;
		double maksimum = Integer.MIN_VALUE;
		double[] tulemus = new double[2];
		for (double rida : temperatuurid) {
			if (rida < miinimum) {
				miinimum = rida;
			} else if (rida > maksimum) {
				maksimum = rida;
			}
		}
		tulemus[0] = miinimum;
		tulemus[1] = maksimum;
		return tulemus;
	}

	static double aastaKeskmineTemperatuur(double[] temperatuurid){
		double summa = 0;
		int i = 0;
		for (double rida: temperatuurid) {
			i++;
			summa += rida;
		}
		double keskmine = summa / i;
		return keskmine;
	}

	static double[] kuudeKeskmisedTemperatuurid(String[] kuupäevad, double[] temperatuurid) {
		double[] tulemus = new double[12];
		double kuuKekmine = 0;
		int i = 0;
		int j = 0;
		int h = 0;
		for (String rida: kuupäevad) {
			kuuKekmine += temperatuurid[i];
			i++;
			if (i == temperatuurid.length || kuupäevad[i].charAt(6) != kuupäevad[i-1].charAt(6)){
				h++;
				tulemus[j] = kuuKekmine / (i / h);
				j++;
				kuuKekmine = 0;
			}
		}
		return tulemus;
	}

	static String päevadeVäikseimTemperatuurideVahe(String[] kuupäevad, double[] temperatuurid){
		double miinimum;
		double maksimum;
		double vahe = Integer.MAX_VALUE;
		String kuupäev;
		for (int i = 0; i < kuupäevad.length; i++) {
			miinimum = Integer.MAX_VALUE;
			maksimum = Integer.MIN_VALUE;
			while (kuupäevad[i].charAt(9) != kuupäevad[i+1].charAt(9)){
				if (temperatuurid[i] > maksimum){
					maksimum = temperatuurid[i];
				}else if (temperatuurid[i] < miinimum){
					miinimum = temperatuurid[i];
				}
			}
			if ((maksimum - miinimum) < vahe){
				vahe = maksimum - miinimum;
				kuupäev
			}
		}
		return kuupäev;
	}

	//////////////////////////////////////////////// LAHENDAMINE-TESTIMINE:
	public static void main(String[] args) {
		System.out.println("Kodutöö nr 3a.                          Programmi väljund");
		System.out.println("=========================================================:");
		//////////////////////////////////// ETTEVALMISTUS:
		String fNimi = "C:\\Users\\Georg\\IdeaProjects\\Programmeerimisharjutused\\src\\temperatuurid2019.txt"; // lühinimi, fail asub jooksvas kaustas
		String[] read = readFailist(fNimi);  
		// massiivi 'read' elemendiks on rida failist fNimi, 
		// 									näiteks "2018-12-12 23:45:00, -2.07697986577"
		kolmJärjendit(read); // kolm järjendit (massiivi) faili ridadest
		// formeeritud on kolm järjendit (kuupäev[], kellaaeg[] ja temperatuur[])
		// kontroll, 10 esimest elementi igast järjendist:
		System.out.println("\nKontrolliks, järjendite algused: ");
		System.out.println("kuupäev[]\tkellaaeg[]\t temperatuur[]");
		for(int i = 0; i < 10; i++)
			System.out.println(kuupäev[i] + "\t" + kellaaeg[i] + "\t" + temperatuur[i]);
		System.out.println("   ...   \t   ...   \t    ...");
		//
		System.out.println();
		System.out.printf("%s %.2f %s", "Aasta madalaim temperatuur oli", maksimumJaMiinimum(temperatuur)[0], "kraadi.");
		System.out.println();
		System.out.printf("%s %.2f %s", "Aasta kõrgeim temperatuur oli", maksimumJaMiinimum(temperatuur)[1], "kraadi.");
		System.out.println();
		//Aasta keskmise temperatuuri meetodi tesitimine.
		System.out.println();
		System.out.printf("%s %.2f %s", "Aasta keskmine temperatuur oli", aastaKeskmineTemperatuur(temperatuur), "kraadi.");
		System.out.println();
		//Iga kuu keskmise temperatuuri meetodi testimine.
		System.out.println();
		String [] kuud = {"Jaanuari", "Veebruari", "Märtsi", "Aprilli", "Mai", "Juuni", "Juuli", "Augusti", "Septempri", "Oktoobri", "Novembri", "Detsembri"};
		double[] kuudeKeskmised = kuudeKeskmisedTemperatuurid(kuupäev, temperatuur);
		for (int i = 0; i < 12; i++) {
			System.out.printf("%s %s %.2f %s", kuud[i], "keskmine temperatuur oli", kuudeKeskmised[i], "kraadi.");
			System.out.println();
		}
		//////////////////////////////////// ETTEVALMISTUS.
		//////////////////////////////////// Ülli Õpilase meetodite testrakendused:
		// --------------------------------------------------------------NÄIDIS!
		// meetodi üksTemperatuur() test:
		String sünnipäev = "2019-08-25";
		String kell = "12:00:00";
		System.out.print("\nMinu sünnipäeval eelmisel aastal (" + sünnipäev 
												+ ") oli keskpäevane temperatuur ");
		Double x = üksTemperatuur(sünnipäev, kell);
		if(x == null)
			System.out.println("\n!! Antud aega: " + sünnipäev + " " + kell + " ei leitud !!"); 
		else
			System.out.println(Math.round(x*10)/10.0 + " kraadi."); // üks koht pärast koma (punkti)
		// --------------------------------------------------------------
		System.out.println("\n=========================================================.");		
		System.out.println("Georg Demidov                     "
					+ new java.sql.Timestamp(System.currentTimeMillis())); // kellaajaga
		
	}

	//////////////////////////////////////////////////////// EELPROGRAMMEERITUD MEETODID:
	/** Sisestada read failist.
	 * @param fNimi Tekstifaili nimi. Faili ridade arv < 110000.
	 * @return Järjend, mille i-ndaks elemendiks on antud faili i-s rida.
	 */
	static String[] readFailist(String fNimi){
		String[] ss = new String[110000];	
		// sisestada read failist järjendisse ss:		
		Gen_RiduTekstifailist gen = new Gen_RiduTekstifailist(fNimi); // generaator 
		int i = 0; // indeks failides
		while(gen.hasNext()){
			String rida = gen.next(); // järjekordne rida failist
			if(rida.startsWith("20"))
				ss[i++] =  rida;
		}//while
		// i on loetud ridade arv (viimase indeks + 1)
		return Arrays.copyOfRange(ss, 0, i);	
	}//readFailist	
	
	/** Moodustada kuupäevade järjend, kellaaegane järjend, temperatuuride järjend.
	 * @param read Meetodiga 'readFailist' saadud ridade järjend.
	 * Tulemusena formeeritakse staatilised massiivid 'kuupäev', 'kellaaeg' ja 'temperatuur' (vt progr. alguses).
	 */	
	static void kolmJärjendit(String[] read){
		 int n = read.length;
		 kuupäev = new String[n];
		 kellaaeg = new String[n];
		 temperatuur = new double[n];
		 for(int i = 0; i < n; i++){ // iga rea korral
			kuupäev[i]  = read[i].substring(0, 10);
			kellaaeg[i] = read[i].substring(11, 19);
			temperatuur[i] = Double.parseDouble(read[i].substring(21, read[i].length()));
		}//for
	 }//kolmJärjendit
}