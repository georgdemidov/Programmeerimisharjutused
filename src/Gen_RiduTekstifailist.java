/******************************************************************************************
*                                 GENERAATOR-KLASS 
*                           tekstifailist ridade hankimiseks 
*                             Realiseerib liidese Iteraator
*
*      			Konstruktorile antakse 
*              		tekstifaili lihtnimi (kui see fail asub generaatoriga samas kaustas) 
*              		või täisnimi.
*
*      			Isendimeetod next() annab välja tekstifaili järjekordse rea.
*
*                  Seda klassi kasutatakse nagu iteraatorit ikka, nt: 
*                     Gen_RiduTekstifailist gen = new Gen_RiduTekstifailist(<faili nimi>); 
*                     String rida;
*                     while(gen.hasNext()){
*                        rida = gen.next(); // järjekordne rida
*                        ...
*                     }//while
*
*@author Jüri Kiho
*
*@copyright Kopeerimine lubatud; muutmine/modifitseerimine lubatud ainult autori loal.  
******************************************************************************************/

public class Gen_RiduTekstifailist implements java.util.Iterator{

   // isendimuutujad:		
   private String fNimi;               // vaadeldava faili nimi
   private java.io.BufferedReader br;  // failist lugeja
   private String rida;                // jrk rida, valmis välja andmiseks
   private boolean ammendatud;         // ammendatuse tunnus

   public Gen_RiduTekstifailist(String fNimi){ // konstruktor 

      this.fNimi = fNimi;

      try{
 
		br = new java.io.BufferedReader(new java.io.FileReader(fNimi));

         // lugeda esimene rida:	
         rida = br.readLine();

      }catch (java.io.IOException e) {
         e.printStackTrace(); 
         System.exit(1);
      }

      ammendatud = rida == null;

   }//konstruktor

   @Override
   public boolean hasNext(){
      return !ammendatud; 
   }

   @Override
   public String next() {
      String anda = new String(rida); // rida panna hoiule (välja andmiseks)
      try{
         rida = br.readLine();
      }catch (java.io.IOException e) {
         e.printStackTrace(); 
         System.exit(1);
      }
      ammendatud = rida == null;
      return anda; // anda välja (see, mis enne oli väljal rida)
   }//next

}//class Gen_RiduTekstifailist
