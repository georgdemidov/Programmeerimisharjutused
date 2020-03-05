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
 *            0) temperatuur(String päev, String kell)
 *			  1) maksimumJaMiinimum(double[] temperatuurid)
 *			  2) aastaKeskmineTemperatuur(double[] temperatuurid)
 *            3) lokaalseteEkstreemumiteArv(double [] temperatuutid)
 *            4) kuudeKeskmisedTemperatuurid(String[] kuupäevad, double[] temperatuurid)
 *            5) päevadeVäikseimTemperatuurideVahe(String[] kuupäevad, double[] temperatuurid)
 *
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

	/**
	 * Meetod maksimumJaMiimimum() leiab 2019. aastal mõõdetud temperatuuridest kõige kõrgema temperatuuri ja kõige madalama temperatuuri.
	 * @param temperatuurid - Antud meetod võtab argumendiks ujukomaarvude massiivi, mis sisaldab endas temperatuure.
	 * @return - Meetod tagastab ujukomaarvude massiivi, milles on kaks elementi- kõrgeim ja madalaim temperatuur.
	 */
	static double [] maksimumJaMiinimum(double[] temperatuurid){
		//Loon muutujad miinimum ja maksimum, et nende abil kontrollida, kas temperatuur on madalaim või kõrgeim.
		double miinimum = Integer.MAX_VALUE;
		double maksimum = Integer.MIN_VALUE;
		double[] tulemus = new double[2];
		for (double element : temperatuurid) { //Käin massiivis läbi iga tema elemedi ja kontrollin ega ta pole suurem või väiksem, kui eelnevad temperatuurid.
			if (element < miinimum) {
				miinimum = element;
			}
			if (element > maksimum) {
				maksimum = element;
			}
		}
		//Lisan ujukomaarvude järjendisse tulemus madalaima ja kõrgeima temperatuuri.
		tulemus[0] = miinimum;
		tulemus[1] = maksimum;
		return tulemus;
	}

	/**
	 * Meetod aastaKeskmineTemperatuur() leiab 2019. aasta keskmise temperatuuri.
	 * @param temperatuurid - Antud meetod võtab argumendiks ujukomaarvude massiivi, mis sisaldab endas temperatuure.
	 * @return - Meetod tagastab ujukomaarvu, mis näitab 2019. aasta keskmist temperatuuri.
	 */
	static double aastaKeskmineTemperatuur(double[] temperatuurid){
		double summa = 0;
		for (double rida: temperatuurid) { //Käin massiivis läbi iga tema elemendi ning liidan selle muutujale summa juurde, et saada teada kogu temperatuuride summa.
			summa += rida;
		}
		//Muutuja keskmise abil jagan 2019. aasta temperatuuride kogusumma läbi massiivi pikkusega, sest selle pikkus näitab, mitu temperatuuri oli massiivis.
		double keskmine = summa / temperatuurid.length;
		return keskmine;
	}

	/**
	 * Meetod kuudeKeskmisedTemperatuurid() leiab 2019. aasta iga kuu keskmise temperatuuri.
	 * @param kuupäevad - Antud meetod võtab argumendiks sõnede massiivi, mis sisaldab endas 2019. aasta kuupäevi.
	 * @param temperatuurid - Antud meetod võtab argumendiks ujukomaarvude massiivi, mis sisaldab endas temperatuure.
	 * @return - Meetod tagastab ujukomaarvude massiivi, mis sisaldab endas 12 elementi ja iga element on vastavalt jaanuari kuni detsembri keskmine temperatuur.
	 */
	static double[] kuudeKeskmisedTemperatuurid(String[] kuupäevad, double[] temperatuurid) {
		//Loon ujukomaarvude massiivi, mis saab sisaldada kuni 12 elementi.
		double[] tulemus = new double[12];
		double kuuKeskmine = 0;
		//Loon kolm täisarvulist muutujat, mis aitavad mul toimetada tsükli sees.
		int i = 0;
		int j = 0;
		int h = 0;
		for (String rida: kuupäevad) {//Käin labi sõnede massiivi iga kuupäeva. Seda ülesannet oleks ka võinud lahendada "fori" tsükliga, ent mõtlesin, et sellega oleks huvitavam.
			//Lisan muutujale kuuKeskmine kuu iga temperatuuri.
			kuuKeskmine += temperatuurid[i];
			i++;
			//Lisan muutujale h pidevalt ühe, sest seda on vaja kasutada iga kuu temperatuuride summa läbi jagamisel mõõdetud temperatuuride arvuga.
			h++;
			//Kui lõpuks juhtub, et kuupäevade massiivis kuupäeval muutub kuu, siis siseneb meetod tinglausesse. Samuti, kui muutuja i pikkus on sama, mis temperatuuri massiivi pikkus, siis siseneb meetod tinglausesse.
			if (i == temperatuurid.length || kuupäevad[i].charAt(6) != kuupäevad[i-1].charAt(6)){
				//Lisan muutujale kohal j vastava kuu keskmise temperatuuri.
				tulemus[j] = kuuKeskmine / h;
				//Muudan h muutuja väärtuse tagasi 0 peale, sest igas kuus on erinev arv päevi.
				h = 0;
				j++;
				//Samuti muudan muutuja kuuKeskmine tagasi 0 peale, et iga kuu puhul lugeda selle kuu temperatuuride summat.
				kuuKeskmine = 0;
			}
		}
		return tulemus;
	}

	/**
	 * Meetod lokaalseteEkstreemumiteArv() leiab temperatuuride massiivist kõik lokaalsed ekstreemumid
	 * (kohad, kus [i-1] ja [i+1] on mõlemad väiksemad või suuremad kui element kohal i). Kusjuures, otspunkte ei tohi arvestada, sest need pole lokaalsed.
	 * @param temperatuutid - Antud meetod võtab argumendiks ujukomaarvude massiivi, mis sisaldab endas temperatuure.
	 * @return - Meetod tagastab täisarvu, mis näitab mitu lokaalset ekstreemumi oli temperatuuride massiivis.
	 */
	static int lokaalseteEkstreemumiteArv(double [] temperatuutid){
		int ekstreemumid = 0;
		for (int i = 1; i < temperatuutid.length - 1; i++) {//Käin tsüklit nii kaua läbi, kuni muutuja i ei ole võrdne temperatuurid massiivi pikkusega.
			//Kui temperatuur kohal i on suurem kui [i-1] ja [i+1], siis lisan muutujale ekstreemumid ühe.
			if (temperatuutid[i-1] < temperatuutid[i] && temperatuutid[i+1] < temperatuutid[i]){
				ekstreemumid += 1;
			//Kui temperatuur kohal i on väiksem kui [i-1] ja [i+1], siis lisan muutujale ekstreemumid ühe.
			}else if (temperatuutid[i-1] > temperatuutid[i] && temperatuutid[i+1] > temperatuutid[i]){
				ekstreemumid += 1;
			}
		}
		return ekstreemumid;
	}

	/**
	 * Meetod päevadeVäikseimTemperatuurideVahe, leiab päeva 2019. aastal, mille kõrgeima temperatuuri ja madalaima temperatuuri vahe oli väiksem, kui kõigil teistel päevadel.
	 * @param kuupäevad - Antud meetod võtab argumendiks sõnede massiivi, mis sisaldab endas 2019. aasta temperatuure.
	 * @param temperatuurid - Antud meetod võtab argumendiks ujukomaarvude massiivi, mis sisaldab endas temperatuure.
	 * @return - Meetod tagastab sõne, milleks on päev, millal oli kõrgeima ja madalaima temperatuuri vahe aasta jooksul kõige väiksem.
	 */
	static String päevadeVäikseimTemperatuurideVahe(String[] kuupäevad, double[] temperatuurid) {
		double miinimum;
		double maksimum;
		double minimaalneVahe = Integer.MAX_VALUE;
		//Muutuja sõne lõpuks saab endale väärtuseks päeva, millal temperatuuride vahe oli väikseim.
		String väikseimKuupäev = "tere";
		int j = 0;
		for (int i = 0; i < kuupäevad.length / 288; i++){//Käin tsükli läbi just nii mitu korda, sest kuupäevade tsükkel jagates 365-ga saab vastuseks 288. Selle tõttu saan käia tsükli läbi piisavalt kordi, et saada igat päeva analüüsida.
			miinimum = Integer.MAX_VALUE;
			maksimum = Integer.MIN_VALUE;
			//"While" tsükli abil, käin läbi iga päeva kõik temperatuurid eraldi. Nii kaua kuni ei saab uus päev, käin seda tsüklit läbi.
			while (kuupäevad[j].charAt(9) == kuupäevad[j+1].charAt(9) && kuupäevad[j].charAt(6) == kuupäevad[j+1].charAt(6)){
				//Kui temperatuur kohal j on väiksem, kui hetkel olev miinimumi väärtus, siis muudan muutuja miinimum väärtust.
				if(temperatuurid[j] < miinimum){
					miinimum = temperatuurid[j];
				}
				//Kui temperatuur kohal j on suurem, kui hetkel olev maksimumi väärtus, siis muudan muutuja miinimum väärtust.
				if (temperatuurid[j] > maksimum){
					maksimum = temperatuurid[j];
				}
				//Juhul, kui temperatuur kohal j, ei ole suurem kui maksimum või väiksem kui miinimum, siis lisan muutujale j ühe tinglausetest väljas pool.
				j++;
			}
			//Nüüd kontrollin, kas uue päeva kõrgeima ja madalaima temperatuuri vahe pole väiksem, kui seni kõige väiksem vahe.
			if ((maksimum - miinimum) < minimaalneVahe){
				//Juhul kui see on, muudan minimaalneVahe muutuja väärtust nüüdseks kõige väiksemaks vaheks.
				minimaalneVahe = maksimum - miinimum;
				//Samuti muudan muutuja väikseimKuupäev sõneks, milleks on kuupäev, millal esines väiksem vahe senini.
				väikseimKuupäev = kuupäevad[j];
			}
			//Lisan muutujale j ühe tsüklist väljas, sest vastasel juhul, ei käivituks enam "while" tsükkel mitte kordagi pärast esimest korda.
			j++;
		}
		return väikseimKuupäev;
	}

	//Peameetod, kuhu on koondatud meetodite testid ning, kuhu on ka koondatud vormistuslik pool.
	public static void main(String[] args) {
		System.out.println("Kodutöö nr 3a.                          Programmi väljund");
		System.out.println("=========================================================:");
		//////////////////////////////////// ETTEVALMISTUS:
		String fNimi = "C:\\Users\\Georg\\IdeaProjects\\Programmeerimisharjutused\\src\\temperatuurid2019.txt"; // lühinimi, fail asub jooksvas kaustas
		String[] read = readFailist(fNimi);  
		//massiivi 'read' elemendiks on rida failist fNimi,
		//näiteks "2018-12-12 23:45:00, -2.07697986577"
		kolmJärjendit(read); // kolm järjendit (massiivi) faili ridadest
		// formeeritud on kolm järjendit (kuupäev[], kellaaeg[] ja temperatuur[])
		// kontroll, 10 esimest elementi igast järjendist:
		System.out.println("\nKontrolliks, järjendite algused: ");
		System.out.println("kuupäev[]\tkellaaeg[]\t temperatuur[]");
		for(int i = 0; i < 10; i++)
			System.out.println(kuupäev[i] + "\t" + kellaaeg[i] + "\t" + temperatuur[i]);
		System.out.println("   ...   \t   ...   \t    ...");
		//Ülli Õpilase meetodite testrakendused:
		String sünnipäev = "2019-08-25";
		String kell = "12:00:00";
		System.out.print("\nMinu sünnipäeval eelmisel aastal (" + sünnipäev + ") oli keskpäevane temperatuur ");
		Double x = üksTemperatuur(sünnipäev, kell);
		if(x == null)
			System.out.println("\n!! Antud aega: " + sünnipäev + " " + kell + " ei leitud !!");
		else
			System.out.println(Math.round(x*10)/10.0 + " kraadi."); // üks koht pärast koma (punkti)
		// --------------------------------------------------------------
		//Madalaima ja kõrgeima aasta temperatuuri meetodi väljastamine.
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
		System.out.println("2019. aasta kuude keskmised temperatuurid:");
		System.out.println("------------------------------------------------");
		String [] kuud = {"Jaanuari", "Veebruari", "Märtsi", "Aprilli", "Mai", "Juuni", "Juuli", "Augusti", "Septempri", "Oktoobri", "Novembri", "Detsembri"};
		double[] kuudeKeskmised = kuudeKeskmisedTemperatuurid(kuupäev, temperatuur);
		for (int i = 0; i < 12; i++) {
			System.out.printf("%s %s %.2f %s", kuud[i], "keskmine temperatuur oli", kuudeKeskmised[i], "kraadi.");
			System.out.println();
		}
		System.out.println("------------------------------------------------");
		//Päevade miinimumi ja maksimumi väikseim vahe meetodi testimine.
		System.out.println();
		System.out.println("Päevade miinimumi ja maksimumi vahe oli kõige väiksem " + päevadeVäikseimTemperatuurideVahe(kuupäev, temperatuur) + ".");
		System.out.println();
		//Lokaalsete ekstreemumite arvu meetodi testimine.
		System.out.println("2019. aastal esines temperatuuride tekstifailis " + lokaalseteEkstreemumiteArv(temperatuur) +  " lokaalset ekstreemumit.");
		//Ülli Õpilase meetodite testrakendused:
		System.out.println("\n=========================================================.");		
		System.out.println("Georg Demidov                     "
					+ new java.sql.Timestamp(System.currentTimeMillis())); // kellaajaga
		
	}

	//EELPROGRAMMEERITUD MEETODID:
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