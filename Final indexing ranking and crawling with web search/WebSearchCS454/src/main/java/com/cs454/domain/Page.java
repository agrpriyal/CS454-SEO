package com.cs454.domain;

public class Page
{
	String url;
	String description;
	String title;
	String author;
	String localPath;
	int count;
	double tfid;
	double  pageAnalysisValue;
	double privateRankValue;
	double calculatedTfid;
	double cacculatedPageRank;
	double overAllWeight;
	
	public Page()
	{	
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTfid() {
		return tfid;
	}

	public void setTfid(double tfid) {
		this.tfid = tfid;
	}	
	
	public double getPageAnalysisValue() {
		return pageAnalysisValue;
	}

	public void setPageAnalysisValue(double pageAnalysisValue) {
		this.pageAnalysisValue = pageAnalysisValue;
	}

	public double getPrivateRankValue() {
		return privateRankValue;
	}

	public void setPrivateRankValue(double privateRankValue) {
		this.privateRankValue = privateRankValue;
	}

	public double getCalculatedTfid() {
		return calculatedTfid;
	}

	public void setCalculatedTfid(double calculatedTfid) {
		this.calculatedTfid = calculatedTfid;
	}

	public double getCacculatedPageRank() {
		return cacculatedPageRank;
	}

	public void setCacculatedPageRank(double cacculatedPageRank) {
		this.cacculatedPageRank = cacculatedPageRank;
	}

	public double getOverAllWeight() {
		return overAllWeight;
	}

	public void setOverAllWeight(double overAllWeight) {
		this.overAllWeight = overAllWeight;
	}
	
	
	
	/*public static Comparator<Pagerank> StuNameComparator = new Comparator<Pagerank>() {

		public int compare(Pagerank s1, Pagerank s2) {
			
			double tfid1 = s1.getTfid();
			double tfid2 = s2.getTfid();

		   //ascending order
		   return (int) (tfid1-tfid2);

		   //descending order
		   //return StudentName2.compareTo(StudentName1);
	    }};*/

	    /*Comparator for sorting the list by roll no
	    public static Comparator<Student> StuRollno = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {

		   int rollno1 = s1.getRollno();
		   int rollno2 = s2.getRollno();

		   For ascending order
		   return rollno1-rollno2;

		   For descending order
		   //rollno2-rollno1;
	   }};*/
	
}
