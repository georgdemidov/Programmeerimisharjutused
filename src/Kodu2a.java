/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 2a
 * Teema:
 * Üksikul saarel elava eraku kingavaliku simulatsioon.
 *
 * Autor: Georg Demidov
 *
 *****************************************************************************/

public class Kodu2a {

    //Koostan meetodi, mis simuleerib mündiviset oletades, et tahame saada ainult kulli. Kui programm tagastab väär ehk "false", tuli kiri, kui tõsi ehk "true", siis tuli kull.
    //Meetodil puudub argument ning meetod tagastab "boolean"-i ehk tõeväärtuse (tõsi või väär).
    static boolean kull(){
        //Juhul, kui funktsiooni "Math.random()" tulemusel tuleb poollõigul [0; 0.5) asetsev arv, siis tagastatav väärtus on tõsi ehk "true", vastasel juhul ([0.5; 1)) tagastab väär ehk "false".
        return Math.random() < 0.5;
    }

    //Meetod paljajaluVäljumisteArv võtab argumendiks täisarvu "väljumisiKokku" ning selle abil, leiab eraku paljajalu väljumiste arvu iga saja väljumise järel.
    //Antud meetod ei tagasta midagi, kuna tulemused kuvatakse koheselt terminali kasutades teist meetodit "vormistus()".
    static void paljajaluVäljumisteArv(int väljumisiKokku){
        //Tähistan mündiviske puhul, et kui tuleb kull, siis erak väljub ja siseneb vasakust uksest.
        //Täisarvuline muutuja "vasakUks" algväärtus on kolm, sest selle ukse ees asetseb alguses 3 kinga paari.
        int vasakUks = 3;
        //Tähistan mündiviske puhul, et kui tuleb kiri, siis erak väljub ja siseneb paremast uksest.
        //Täisarvuline muutuja "paremUks" algväärtus on kolm, sest selle ukse ees asetseb alguses 3 kinga paari.
        int paremUks = 3;
        //Koostan muutuja "paljadJalad", mis muutub iga kord ühe võrra suuremaks, kui toimub paljajalu väljumine.
        int paljadJalad = 0;
        //Paljajalu väljumiste arvu leidmiseks kasutan topelt tsüklit. Esimene tsükkel on ainult selleks, et kuvada iga 100 väljumise järgsed tulemused.
        for (int j = 100; j <= väljumisiKokku ; j+= 100) {
            //Antud tsükkel käib läbi 200 korda, sest iga väljumise kotha on ka üks sisenemine ehk selle tõttu peab käima läbi tsüklit iga kord 200 korda.
            for (int i = 0; i < 200; i++) {
                //Selle tinglausega kontrollin, kas arv jagub 2-ga, sest see simuleerib minu ülesandes väljumist. Kui muutuja i jagub kahega, siis erak väljub.
                //Kuna iga teine arv on paarisarv, siis toimubki väljumine ja sisenemine korda mööda.
                if (i % 2 == 0) {
                    //Kasutan loodud meetodit kull(), mis simuleerib mündiviset. Kui tuleb kull, siis erak väljub vaskust uksest.
                    if (kull()) {
                        //Kui vasaku ukse juures on 0 paari kingi, siis väljub erak paljajalu ning muutujale "paljadJalad" liidetakse üks.
                        if (vasakUks == 0) {
                            paljadJalad++;
                        //Kui vasaku ukse juures on kingapaar, siis erak väjub kingadega ning muutujast "vasakUks" lahutatakse üks.
                        }else{
                            vasakUks--;
                        }
                    //Kasutan loodud meetodit kull(), mis simuleerib mündiviset. Kui tuleb kiri, siis erak väljub paremast uksest.
                    }else{
                        //Kui parema ukse juures on 0 paari kingi, siis väljub erak paljajalu ning muutujale "paljadJalad" liidetakse üks.
                        if (paremUks == 0) {
                            paljadJalad++;
                        //Kui parema ukse juures on kingapaar, siis erak väjub kingadega ning muutujast "paremUks" lahutatakse üks.
                        }else{
                            paremUks--;
                        }
                    }
                //Kui arv ei jagu kahega, siis erak siseneb majja.
                }else{
                    //Kasutan loodud meetodit kull(), mis simuleerib mündiviset. Kui tuleb kull, siis erak siseneb vaskust uksest.
                    if (kull()) {
                        //Kontrollin ega kummagi ukse juures pole 6 paari kingi, sest kui on see tähendab, et erak tuli tagasi paljajalu ja ta ei pea tagasi panema kingapaari.
                        if (vasakUks == 6 || paremUks == 6) {
                            ;
                        //Kui erakul on ikkagi kingad jalas, siis ta paneb need tagasi vasaku ukse juurde. Muutujale "vasakUks" liidetakse üks.
                        }else{
                            vasakUks++;
                        }
                    //Kasutan loodud meetodit kull(), mis simuleerib mündiviset. Kui tuleb kiri, siis erak siseneb paremast uksest.
                    }else{
                        //Kontrollin ega kummagi ukse juures pole 6 paari kingi, sest kui on see tähendab, et erak tuli tagasi paljajalu ja ta ei pea tagasi panema kingapaari.
                        if (vasakUks == 6 || paremUks == 6) {
                            ;
                        //Kui erakul on ikkagi kingad jalas, siis ta paneb need tagasi parema ukse juurde. Muutujale "paremUks" liidetakse üks.
                        }else{
                            paremUks++;
                        }
                    }
                }
            }
            //Pärast igat 100-ndat väljumist kutsun välaj meetodi vormistus(), et kuvada terminalis vastavate väljumiste statistika (väljumised kokku, paljajalu väljumised, paljajalu väljumiste protsendi).
            vormistus(j, paljadJalad);
        }
    }

    //Antud meetodiga leian paljajalu väljumiste protsendi. Selle jaoks jagan eraku paljajalu väljumised kogu väljumiste arvuga ning korrutan 100-ga.
    //Meetod tagastab sõne, sest ülesande vormistuses oli vaja komakoht eraldada komaga, mitte punktiga. Seetõttu pidin kasutama "String.format()" funktsiooni.
    //Meetodi argumentideks on eraku väljumised kokku ning tema paljajalu väljumised. Mõlemad argumendid on täisarvud.
    static String paljajaluVäljumisteProtsent(int väljumisiKokku, int paljajaluVäljumised){
        //Arvutan välja protsendi.
        double protsent = (double)paljajaluVäljumised / (double)väljumisiKokku * 100;
        //Esialgu ümardan muutuja "protsent", mis on ujukomaarv, kahe komakohani, pärast seda asendan arvu sees oleva punkti komaga ning lõpetuseks muudan
        //muutuja "protsent" sõneks. Tulemuseks tuleb muutuja "sõneProtsent", mille ka antud meetod tagastab.
        String sõneProtsent = String.valueOf(String.format("%.2f", protsent).replace(".", ","));
        return sõneProtsent;
    }

    //Meetod vormistus on mõeldud selleks, et vormistada andmed selleks vajalikul moel. Meetod ei tagasta mitte midagi, sest see prindib kohe vastavad tulemused terminali.
    //Meetodi argumentideks on eraku väljumised kokku ning tema paljajalu väljumised. Mõlemad argumendid on täisarvud.
    static void vormistus(int väljumisiKokku, int paljadJalad){
        //Leian paljajalu väljumiste protsendi, et kuvada see terminalis.
        String protsent = paljajaluVäljumisteProtsent(väljumisiKokku, paljadJalad);
        //Kasutades "printf()" funktsiooni vormistan ülesande vastavalt juhendile. Jutumärkides on muutujale vastav käsk. "%arvs" paigutab arvu vastavasse vahemikku.
        //"%%" kuvab terminali %- märgi ning "%n" abil liigub käsurida uuele reale.
        System.out.printf("%7s %13s %12s %% %n", väljumisiKokku, paljadJalad, protsent);
        //Meetod ei tagasta midagi, sest see on "void" meetod, mis ei peagi midagi tagastama.
    }

    //Peameetod, milles samuti kontrollin, kas meetodid töötavad korrektselt. Samuti on peameetodisse koondatud osa vormistuslikust poolest.
    public static void main(String[] args){
        System.out.println("Kodutöö nr 2a.                          Programmi väljund");
        System.out.println("=========================================================:" + "\n");
        System.out.println("Väljumisi    Paljajalu    Paljajalu");
        System.out.println("    kokku    väljumisi    väljumiste %");
        paljajaluVäljumisteArv(2000);
        System.out.println("\n" + "=========================================================.");
        System.out.println("Georg Demidov                     " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}