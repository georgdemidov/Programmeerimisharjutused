import java.util.Arrays;
import java.util.List;
import java.lang.*;

public class Kodu5a2 {

    static int kaashaalikuühenditeArv (String lause){
        Character[] kaashäälikud = {'b', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 's', 'z', 'z', 't', 'v'};
        List<Character> list = Arrays.asList(kaashäälikud);
        int kaashäälikuühenditeLoendur = 0;
        for (int i = 0; i < lause.length(); i++) {
            int KHÜLoendur = 0;
            while(list.contains(Character.toLowerCase(lause.charAt(i))) && list.contains(Character.toLowerCase(lause.charAt(i + 1)))){
                i++;
                KHÜLoendur++;
                if (i+2 > lause.length()){
                    break;
                }
            }
            if (KHÜLoendur > 0){
                kaashäälikuühenditeLoendur++;
            }
        }
        return kaashäälikuühenditeLoendur;
    }

    public static void main(String[] args) {
        System.out.println("Kodutöö nr 5a-2.                        Programmi väljund");
        System.out.println("=========================================================:" + "\n");

        String lause = "Mait Malmsten kehastus korstnapühkijaks.";
        System.out.println("Kaashäälikuühendeid tekstis: " + '"'+lause+'"' + " on " + kaashaalikuühenditeArv(lause) + ".\n");

        lause = "MaitMalmstenkehastuskorstnapühkijaks";
        System.out.println("Kaashäälikuühendeid tekstis: " + '"'+lause+'"' + " on " + kaashaalikuühenditeArv(lause) + ".");

        System.out.println("\n" + "=========================================================.");
        System.out.println("Georg Demidov                     " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}
