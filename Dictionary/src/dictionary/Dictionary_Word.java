package dictionary;

@SuppressWarnings("rawtypes")
public class Dictionary_Word implements Comparable {
	
	private String word, definition;

	public Dictionary_Word(String word, String definition) {
		super();
		this.word = word;
		this.definition = definition;
	}

	public String getWord() {
		return word;
	}

	public String getDefinition() {
		return definition;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Override
	public int compareTo(Object other) {
		return (this.getWord()).compareTo(((Dictionary_Word)other).getWord());
	}	
	
	
	

}
