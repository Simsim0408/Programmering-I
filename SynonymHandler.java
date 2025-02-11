//package programmeringI.Labboration2;  // SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic




****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter, // Denna rad är en importdeklaration som importerar klasser och paket inom java.io javapaketet.
                     // IOException. // .io står för input och output operationer som att läsa och skriva filer. * syftar på ALLA klasser i det nämnda paketet.

public class SynonymHandler //public indikerar att klassen är tillgänglig och kan användas från andra klasser. En klass beskriver ett objekt. SynonymHandler är namnet på klassen
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile) //static anger att metoden tillhör klassen snarare än en specifik instans av klassen.
    	//synonymFile får sin information från SynonymData filerna, readSynonymData gör sedan om alla orden till en Array.
                                         throws IOException // String[] är returneringstypen av metoden. Metoden förväntas returnera en array av strängar.
                                         // readSynonymData är namnet på metoden. Detta namn är självvalt och bör vara beskrivande för vad metoden gör.
                                         // String synonymFile är parameterlistan för metoden. Metoden tar in en strängsparameter med namnet synonymFile. listan för över värden till en metod.
                                         // throws IOException indikerar att metoden kan kasta en IOException.
                                        // IOException är en typ av undantag (exception) som kan uppstå när det uppstår problem med in- eller utmatning,
                                        // till exempel när du läser eller skriver till en fil. Den använder sig av try-block.
    {
        BufferedReader reader = new BufferedReader( //reader är en variabel.
        //BufferedReader är en klass i Java som används för att läsa text från en inmatningsström med effektiv buffring.
        // Buffring innebär att data lagras temporärt i en mellanlagringsenhet (buffer) innan den används eller behandlas vidare

	        new FileReader(synonymFile)); //new betyder att en ny insats skapas, FileReader används för att läsa av tecken från en fil, (synonymfile) är parameter.
        int numberOfLines = 0; //int är datatyp som lagrar heltal. number of lines används för att hålla koll på antalet rader. = 0 sätter värdet till 0.
        String synonymLine = reader.readLine(); // Detta är datatypen för variabeln. reader.readLine är ett anrop till metoden readLine() på en tidigare skapad
                                                //BufferedReader
        while (synonymLine != null)             // while inleder loopen,(synonymLine != null) är villkoret, alltså kommer koden köras tills villkoret är uppfyllt.
        {
			numberOfLines++; //varje gång raden körs ökar antalet rader med 1.
			synonymLine = reader.readLine(); // Detta läser nästa rad från den angivna inmatningsströmmen (reader) och lagrar den i variabeln synonymLine.
			//Så i varje iteration av loopen läses en ny rad och lagras i synonymLine.
		}
		reader.close(); //reader.close() används för att stänga den öppnade inmatningsströmmen, i detta fall en BufferedReader.

		String[] synonymData = new String[numberOfLines]; // new String[numberOfLines]; Detta är en nyinstansiering av en array av strängar.
		// Storleken på arrayen är specificerad som numberOfLines, vilket innebär att arrayen har plats för numberOfLines element (strängar).
        reader = new BufferedReader(new FileReader(synonymFile));
		for (int i = 0; i < numberOfLines; i++)
		    synonymData[i] = reader.readLine();
		reader.close();

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data.
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData,
	    String word) throws IllegalArgumentException
	{
		// adding code here
		// Finds the index of the line containing the word and removes it from synonymData
		int index = synonymLineIndex(synonymData, word);
		String[] updatedSynonymData = new String[synonymData.length - 1];
		int j = 0;
		// Copies synonymData except for the line containing the word
		for (int i = 0; i < synonymData.length; i++) {
			if (i != index) {
				updatedSynonymData[j++] = synonymData[i];
			}
		}
		return updatedSynonymData;
	}

    // getSynonyms returns synonyms in a given synonym line.
	private static String[] getSynonyms (String synonymLine)
	{
		// add code here
		// Parses the line and extracts synonyms separated by commas
		int delimiterIndex = synonymLine.indexOf('|');
		String synonyms = synonymLine.substring(delimiterIndex + 1).trim();
		return synonyms.split(", ");
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym) throws IllegalArgumentException
	{
		// adding code here
		// Retrieves index of the line containing the word in synonymData
		int index = synonymLineIndex(synonymData, word);
		String[] synonyms = getSynonyms(synonymData[index]);
		// Checks if the synonym already exists for the word
		for (String syn : synonyms) {
			if (syn.equalsIgnoreCase(synonym)) {
				throw new IllegalArgumentException("Synonym already exists.");
			}
		}
		synonymData[index] += ", " + synonym; // Adds the new synonym to the existing line
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
    // If there is only one synonym for the given word, an
    // exception of the type IllegalStateException is thrown.
	public static void removeSynonym (String[] synonymData,
	    String word, String synonym)
	    throws IllegalArgumentException, IllegalStateException
	{
		// adding code here
		// Retrieves index of the line containing the word in synonymData
		int index = synonymLineIndex(synonymData, word);
		String[] synonyms = getSynonyms(synonymData[index]);
		// Checks if the synonym is the only one for the word
		if (synonyms.length <= 1) {
			throw new IllegalStateException("Cannot remove the only synonym.");
		}

		boolean found = false;
		StringBuilder updatedSynonyms = new StringBuilder();
		// Removes the specified synonym from the line
		for (String syn : synonyms) {
			if (!syn.equalsIgnoreCase(synonym)) {
				updatedSynonyms.append(syn).append(", ");
			} else {
				found = true;
			}
		}

		// Handles cases where the synonym is not found or found
		if (!found) {
			throw new IllegalArgumentException("Synonym not found.");
		}
		updatedSynonyms.setLength(updatedSynonyms.length() - 2); // Removing the last ", "
		synonymData[index] = synonymData[index].substring(0, synonymData[index].indexOf('|') + 1)
				+ updatedSynonyms.toString(); // Updates the synonym line
	}

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
		// adding code here
		// Implements selection sort to arrange strings in ascending order (ignoring case)
		for (int i = 0; i < strings.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < strings.length; j++) {
				if (strings[j].compareToIgnoreCase(strings[minIndex]) < 0) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				String temp = strings[i];
				strings[i] = strings[minIndex];
				strings[minIndex] = temp;
			}
		}
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine)
    {
		// adding code here
		// Retrieves synonyms from the line, sorts them, and constructs a sorted line
		String[] synonyms = getSynonyms(synonymLine);
		sortIgnoreCase(synonyms);
		StringBuilder sortedLine = new StringBuilder(synonymLine.substring(0, synonymLine.indexOf('|') + 1));
		for (int i = 0; i < synonyms.length; i++) {
			sortedLine.append(synonyms[i]);
			if (i != synonyms.length - 1) {
				sortedLine.append(", ");
			}
		}
		return sortedLine.toString();
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
		// adding code here
		// Sorts synonym lines and sorts synonyms within each line
		sortIgnoreCase(synonymData);
		for (int i = 0; i < synonymData.length; i++) {
			synonymData[i] = sortSynonymLine(synonymData[i]);
		}
	}
}