/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 5a1
 * Teema:
 * Sõnedest kordusgruppide eemaldamine
 *
 * Autor: Georg Demidov
 *
 *****************************************************************************/

public class Kodu5a1 {

    static boolean kasOnKordusgrupp(StringBuilder sõne){
        boolean a = false;
        char eelmineTäht;
        if (sõne.length() < 2){
            ;
        }else{
            for (int i = 1; i < sõne.length(); i++) {
                eelmineTäht = sõne.charAt(i - 1);
                if (eelmineTäht == sõne.charAt(i)) {
                    a = true;
                    break;
                }
            }
        }
        return a;
    }

    static StringBuilder kordusgrppideEemaldamine(String sõne){
        StringBuilder sõna = new StringBuilder(sõne);
        char eelmineTäht;
        while (kasOnKordusgrupp(sõna)){
            for (int i = 1; i < sõna.length(); i++) {
                int kordusgrupiPikkus = 0;
                eelmineTäht = sõna.charAt(i - 1);
                while (eelmineTäht == sõna.charAt(i)){
                    kordusgrupiPikkus++;
                    i++;
                    if (i >= sõna.length()){
                        break;
                    }
                }
                if (kordusgrupiPikkus > 0){
                    kordusgrupiPikkus++;
                    i -= kordusgrupiPikkus;
                    sõna.delete(i, i+kordusgrupiPikkus);
                }
            }
        }
        return sõna;
    }

    public static void main(String[] args) {
        System.out.println("Kodutöö nr 5a-1.                        Programmi väljund");
        System.out.println("=========================================================:" + "\n");

        String antudSõne = "abbbaaacabbbbaac";
        System.out.println("Antud: " + antudSõne);
        System.out.println("  kordusgrupid eemaldatud:  " + kordusgrppideEemaldamine(antudSõne)+"\n");

        antudSõne = "aaabbbaa";
        System.out.println("Antud: " + antudSõne);
        System.out.println("  kordusgrupid eemaldatud:  " + kordusgrppideEemaldamine(antudSõne)+"\n");

        antudSõne = "baaacabbbbac";
        System.out.println("Antud: " + antudSõne);
        System.out.println("  kordusgrupid eemaldatud:  " + kordusgrppideEemaldamine(antudSõne)+"\n");

        antudSõne = "abbbaaacabbbbac";
        System.out.println("Antud: " + antudSõne);
        System.out.println("  kordusgrupid eemaldatud:  " + kordusgrppideEemaldamine(antudSõne)+"\n");

        antudSõne = "";
        System.out.println("Antud: " + antudSõne);
        System.out.println("  kordusgrupid eemaldatud:  " + kordusgrppideEemaldamine(antudSõne));

        System.out.println("\n" + "=========================================================.");
        System.out.println("Georg Demidov                     " + new java.sql.Timestamp(System.currentTimeMillis()));
    }

}