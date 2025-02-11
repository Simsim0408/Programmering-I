// Kostnad.java

class Kostnad.java
{
	public static void main (String[] args)
	{
		System.out.println("KOSTNAD");
		System.out.println();

		java.util.Scanner in = new java.util.Scanner(System.in);
		in.useLocale(java.util.Locale.US);

		System.out.print("Antalet bocker: ");
		int antal = in.nextInt();
		System.out.print("Pris pa bockerna: ");
		double pris = in.nextDouble();

		double kostnad = antal * pris;

		System.out.println("Den totala kostnaden: " + kostnad);
	}
}

