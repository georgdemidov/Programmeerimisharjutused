public class Kodu2a {
    //Koostan meetodi, mis simuleerib mündiviset oletades, et tahame saada ainult kulli. Kui programm tagastab false, tuli kiri, kui true, siis tuli kull.
    static boolean kull(){
        //Juhul, kui Math.random on alla 0.5, siis tagastatav väärtus on true, vastasel juhul (0.5-st 1-ni) tagastab false.
        return Math.random() < 0.5;
    }

    static int paljajaluVäljumisteArv(int väljumisiKokku){
        //Tähistan mündiviske puhul, et kui tuleb kull, siis erak väljub ja siseneb vasakust uksest.
        //Täisarvuline muutuja "vasakUks" algväärtus on kolm, sest selle ukse ees asetseb alguses 3 kinga paari.
        int vasakUks = 3;
        //Tähistan mündiviske puhul, et kui tuleb kiri, siis erak väljub ja siseneb paremast uksest.
        //Täisarvuline muutuja "paremUks" algväärtus on kolm, sest selle ukse ees asetseb alguses 3 kinga paari.
        int paremUks = 3;
        //Koostan muutuja "paljadJalad", mis muutub iga kord ühe võrra suuremaks, kui toimub paljajalu väljumine.
        int paljadJalad = 0;
        //Tsüklit rakendades korrutan väljumisiKokku kahega, sest peab ka arvestama, et iga väljumise korral, peab ka tagasi sisenema.
        for (int i = 0; i < väljumisiKokku * 2; i++) {
            if (i % 2 == 0) {
                if (kull()) {
                    if (vasakUks == 0) {
                        paljadJalad++;
                    }else{
                        vasakUks--;
                    }
                }else{
                    if (paremUks == 0) {
                        paljadJalad++;
                    }else{
                        paremUks--;
                    }
                }
            }else{
                if (kull()) {
                    if (vasakUks == 6 || paremUks == 6) {
                        ;
                    }else{
                        vasakUks++;
                    }
                }else{
                    if (vasakUks == 6 || paremUks == 6) {
                        ;
                    }else{
                        paremUks++;
                    }
                }
            }
        }
        return paljadJalad;
    }

    static double paljajaluVäljumisteProtsent(int väljumisiKokku, int paljajaluVäljumised){
        double protsent = (double)paljajaluVäljumised / (double)väljumisiKokku * 100;
        return protsent;
    }

    public static void main(String[] args){
        System.out.println("Kodutöö nr 2a.                          Programmi väljund");
        System.out.println("=========================================================:" + "\n");
        System.out.println("Väljumisi    Paljajalu    Paljajalu");
        System.out.println("    kokku    väljumisi    väljumiste %");
        for (int i = 100; i <= 2000; i += 100) {
            int väljumisiKokku = i;
            int paljadJalad = paljajaluVäljumisteArv(väljumisiKokku);
            double protsent = paljajaluVäljumisteProtsent(väljumisiKokku, paljadJalad);
            System.out.printf("%7s %13s %12.2f%% %n", väljumisiKokku, paljadJalad, protsent);

        }
        System.out.println("\n" + "=========================================================.");
        System.out.println("Georg Demidov                     " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}