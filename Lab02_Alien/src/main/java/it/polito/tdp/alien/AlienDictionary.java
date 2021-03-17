package it.polito.tdp.alien;

import java.util.LinkedList;
import java.util.List;

public class AlienDictionary {

	private List<Word> dizionario;

	public AlienDictionary(List<Word> word) {
		super();
		this.dizionario = new LinkedList<Word>();
	}
	
	public void addWord(String alienWord, String translation) {
		Word w=ricercaWord(alienWord);
		if(w!=null) {
			w.setTranslation(translation);
		}
		else {
			w=new Word(alienWord, translation);
			dizionario.add(w);
		}
	}
	
	public Word ricercaWord(String s) {
		for(Word d:dizionario) {
			if(d.getAlienWord().equals(s))
				return d;
		}
		return null;
	}
	
	public String translateWord(String alienWord) {
		Word w=ricercaWord(alienWord);
		if(w!=null)
			return w.getTranslation();
		else 
			return null;
	}
}
