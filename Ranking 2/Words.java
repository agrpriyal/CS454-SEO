package cs454.indexing;

public class Words {
	
	int id;
	String word;
	String localFileName;
	String url;
	double tfdifValue;
	int wordCount;
	String title;
	String desc;
	String author;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getLocalFileName() {
		return localFileName;
	}
	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getTfdifValue() {
		return tfdifValue;
	}
	public void setTfdifValue(double tfdifValue) {
		this.tfdifValue = tfdifValue;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((localFileName == null) ? 0 : localFileName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tfdifValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + wordCount;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Words other = (Words) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (localFileName == null) {
			if (other.localFileName != null)
				return false;
		} else if (!localFileName.equals(other.localFileName))
			return false;
		if (Double.doubleToLongBits(tfdifValue) != Double
				.doubleToLongBits(other.tfdifValue))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (wordCount != other.wordCount)
			return false;
		return true;
	}
	
	
	

}
