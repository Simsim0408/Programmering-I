class testTenta
{
 public static void main (String[] args)
 {
 int dagar = 365;
 int vecka = 7;
 int antalHelaVeckor1 = dagar % vecka; // antal rest dagar
 int antalHelaVeckor2 = dagar / vecka;
 System.out.println ("number of rest days: " + antalHelaVeckor1);
 System.out.println ("number of whole weeks: " + antalHelaVeckor2);
 }
}