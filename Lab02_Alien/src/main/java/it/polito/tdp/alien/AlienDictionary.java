package it.polito.tdp.alien;

import java.util.LinkedList;
import java.util.List;

public class AlienDictionary {

	private List<WordEnhanced> dictionary;

	public AlienDictionary() {
		super();
		this.dictionary = new LinkedList<WordEnhanced>();
	}
	
	/*public void addWord(String alienWord, List<String> translation) {
		WordEnhanced w=ricercaWord(alienWord);
		if(w!=null) {
			w.setTranslation(translation);
			return;
		}
		else {
			w=new WordEnhanced(alienWord, translation);
			dizionario.add(w);
		}
	}
	*/
	public void addWord(String alien, String trans) {
		WordEnhanced w = new WordEnhanced(alien);
		if (dictionary.contains(w)) {
			dictionary.get(dictionary.indexOf(w)).setTranslation(trans);
			return;
		}
		w.setTranslation(trans);
		dictionary.add(w);
	}
	
	
	/*public List<String> generaListaTraduzioni(String[] traduzioni) {
		List<String> trad=new LinkedList<String>();
		for(int i=1;i<traduzioni.length;i++) {
			if(traduzioni[i]!=null)
				trad.add(traduzioni[i]);
		}
//		for(int i=0;i<trad.size();i++){
//		    System.out.println(trad.get(i));
//		}
		
		return trad;
	}
	*/
	
	/*public WordEnhanced ricercaWord(String s) {
		for(WordEnhanced d:dizionario) {
			if(d.getAlienWord().equals(s))
				return d;
		}
		return null;
	}
	*/
	
	/*public List<String> translateWord(String alienWord) {
		WordEnhanced w=ricercaWord(alienWord);
		if(w!=null)
			return w.getTranslation();
		else 
			return null;
	}
	*/
	
	 public String translateWord(String alien) {
		WordEnhanced we = new WordEnhanced(alien);
		if (dictionary.contains(we))
			return dictionary.get(dictionary.indexOf(we)).getTranslation();
		return null;
	}
	
	public String translateWordWildCard(String alienWildCard) {

		// Utilizzo le regual expression di Java (posso usare stringa.matches())
		// Sostituisco "?" con "."
		// "." nelle regex indica un qualsiasi carattere
		alienWildCard = alienWildCard.replaceAll("\\?", ".");

		int matchCounter = 0; //quante volte la parola viene trovata
		StringBuilder sb = new StringBuilder();

		for (WordEnhanced w : dictionary) {
			if (w.compareWild(alienWildCard)) {
				matchCounter++;
				sb.append(w.getTranslation() + "\n");
			}
		}

		if (matchCounter != 0)
			return sb.toString();
		else
			return null;
	}	
	 
	public void resetDictionary() {
		this.dictionary.clear();
	}
}
