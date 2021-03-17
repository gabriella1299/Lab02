package it.polito.tdp.alien;

import java.util.LinkedList;
import java.util.List;

public class AlienDictionary {

	private List<WordEnhanced> dizionario;

	public AlienDictionary() {
		super();
		this.dizionario = new LinkedList<WordEnhanced>();
	}
	
	public void addWord(String alienWord, List<String> translation) {
		WordEnhanced w=ricercaWord(alienWord);
		if(w!=null) {
			w.setTranslation(translation);
		}
		else {
			w=new WordEnhanced(alienWord, translation);
			dizionario.add(w);
		}
	}
	
	public List<String> generaListaTraduzioni(String[] traduzioni) {
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
	
	public WordEnhanced ricercaWord(String s) {
		for(WordEnhanced d:dizionario) {
			if(d.getAlienWord().equals(s))
				return d;
		}
		return null;
	}
	
	public List<String> translateWord(String alienWord) {
		WordEnhanced w=ricercaWord(alienWord);
		if(w!=null)
			return w.getTranslation();
		else 
			return null;
	}
	
	public void resetDictionary() {
		this.dizionario.clear();
	}
}
