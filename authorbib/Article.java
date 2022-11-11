package authorbib;

import java.util.ArrayList;


//-----------------------------------------------------
//COMP 249
//Assignment: COMP 249 Assignment 3
//Written by: Nathan Crozier (40048644)
//-----------------------------------------------------

/**
 * @author Nathan Crozier
 * @version 4.10.0
 * @see AuthorBibCreator
 *
 */
public class Article {

	protected String articleNumber;
	protected String[] authors;
	protected String journal;
	protected String title;
	protected String year;
	protected String volume;
	protected String number;
	protected String pages;
	protected String[] keywords;
	protected String doi;
	protected String iSSN;
	protected String month;

	/**
	 * Default constructor, initializes all variables to null.
	 */
	public Article() {
		super();
		this.articleNumber = null;
		this.authors = null;
		this.journal = null;
		this.title = null;
		this.year = null;
		this.volume = null;
		this.number = null;
		this.pages = null;
		this.keywords = null;
		this.doi = null;
		this.iSSN = null;
		this.month = null;
	}

	/**
	 * @param articleNumber The article number for the article.
	 * @param author An array of authors for the article.
	 * @param journal The title of the journal for the article.
	 * @param title The title of the article.
	 * @param year The publishing year of the article.
	 * @param volume The volume of the article.
	 * @param number The article number.
	 * @param pages The pages of reference from the journal.
	 * @param keywords The keywords from the article.
	 * @param doi The DOI: URL from the article.
	 * @param iSSN The ISSN number from the article.
	 * @param month The article month.
	 */
	public Article(String articleNumber, String[] author, String journal, String title, String year, String volume,
			String number, String pages, String[] keywords, String doi, String iSSN, String month) { 
		super();
		this.articleNumber = articleNumber;
		this.authors = author;
		this.journal = journal;
		this.title = title;
		this.year = year;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		this.keywords = keywords;
		this.doi = doi;
		this.iSSN = iSSN;
		this.month = month;
	}

	/**
	 * @param refinedList The refined ArrayList of strings that is returned by the refineThis() method found in the AuthorBibCreator class.
	 * @return Returns an ArrayList of Articles that have been cleaned up and initialized using the strings in the parameter.
	 * Pass in a list that's been refined using the refineThis() method and it will return a list of created article objects.
	 */
	public static ArrayList<Article> createArticleList(ArrayList<String> refinedList) { // Pass in a list that's been
																						// refined using the
																						// refineThis() method and it
																						// will return a list of created
																						// article objects.

		if (refinedList == null) {
			return null;
		}

		String artNum = null, journal = null, title = null, year = null, volume = null, number = null, pages = null,
				doi = null, iSSN = null, month = null;
		String[] authors = null, keywords = null; // Create an empty variable or array for all of the spots that article
													// information can go.

		ArrayList<Article> allArticles = new ArrayList<Article>(); // Create the ArrayList that will hold all of the
																	// article objects.

		for (String s : refinedList) { // Split each article into its individual pieces of information.
			String[] tags = s.split("},");

			for (String k : tags) { // Check each piece of information and depending on what the program detects it
									// as, clean it up and store it where it belongs.

				k = k.trim();

				if (k.trim().equals(""))
					break;

				if (k.contains("author={")) {

					String[] temp = k.split("\n");

					artNum = temp[0].replaceAll(",", "");
					artNum = artNum.trim();

					temp[1] = temp[1].trim();
					temp[1] = temp[1].replace("author={", "");
					k = k.replaceAll("}", "");

					authors = temp[1].split(" and ");
				}

				else if (k.contains("journal={")) {
					journal = k.replace("journal={", "");
					journal = journal.replaceAll("}", "");
					journal = journal.trim();
				}

				else if (k.contains("title={")) {
					title = k.replace("title={", "");
					title = title.replaceAll("}", "");
					title = title.trim();
				} else if (k.contains("year={")) {
					year = k.replace("year={", "");
					year = year.replaceAll("}", "");
					year = year.trim();
				}

				else if (k.contains("volume={")) {
					volume = k.replace("volume={", "");
					volume = volume.replaceAll("}", "");
					volume = volume.trim();
				} else if (k.contains("number={")) {
					number = k.replace("number={", "");
					number = number.replaceAll("}", "");
					number = number.trim();
				} else if (k.contains("pages={")) {
					pages = k.replace("pages={", "");
					pages = pages.replaceAll("}", "");
					pages = pages.trim();
				} else if (k.contains("keywords={")) {
					k = k.trim();
					k = k.replace("keywords={", "");
					k = k.replace("}", "");
					keywords = k.split(";");
				}

				else if (k.contains("doi={")) {
					doi = k.replace("doi={", "");
					doi = doi.replaceAll("}", "");
					doi = doi.trim();
				} else if (k.contains("ISSN={")) {
					iSSN = k.replace("ISSN={", "");
					iSSN = iSSN.replaceAll("}", "");
					iSSN = iSSN.trim();
				} else if (k.contains("month={")) {
					month = k.replace("month={", "");
					month = month.replaceAll("}", "");
					month = month.trim();

				}
			}

			allArticles.add(new Article(artNum, authors, journal, title, year, volume, number, pages, keywords, doi,
					iSSN, month)); // At the end, store all of the information in a new Article object and add it
									// to the array to be returned. After adding this article, return to the start
									// of the loop and start working on creating a new one.

		}

		return allArticles; // Return the newly created array of article objects.
	}

	/**
	 * @return returns a string of the authors found in the calling article that has been formatted in IEEE styling.
	 * A toString for the author array in IEEE format.
	 */
	public String listAuthorsIEEE() { //A toString for the author array in IEEE format.

		String listedAuthors = "";

		for (int i = 0; i < this.authors.length; i++) {

			if (i == this.authors.length - 1)
				listedAuthors += this.authors[i] + ".";
			else
				listedAuthors += this.authors[i] + ", ";

		}

		return listedAuthors;
	}

	/**
	 * @return returns a string of the authors found in the calling article that has been formatted in ACM styling.
	 * A toString for the author array in ACM format.
	 */
	
	public String listAuthorsACM() { // A toString for the author array in ACM format.

		String listedAuthors = "";

		listedAuthors += this.authors[0] + " et al.";

		return listedAuthors;
	}

	/**
	 * @return returns a string of the authors found in the calling article that has been formatted in NJ styling.
	 * A toString for the author array in NJ format.
	 */
	
	
	public String listAuthorsNJ() { /// A toString for the author array in NJ format.

		String listedAuthors = "";

		for (int i = 0; i < this.authors.length; i++) {

			if (i == this.authors.length - 1)
				listedAuthors += this.authors[i] + ".";
			else
				listedAuthors += this.authors[i] + " & ";

		}

		return listedAuthors;
	}

	/**
	 * @return returns a string of the keywords found in the calling article that has been formatted to be easy to read. 
	 * A toString for the keyword array in an article.
	 */
	
	public String listKeywords() { // Lists out the keywords in an easy to read way. Basically a toString for the
									// keyword arrays.

		String listedKeywords = "Keywords: ";

		for (String s : keywords) {

			listedKeywords += s + ", ";

		}

		return listedKeywords;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * A toString used for testing that all of the information reached where it needed to go. If you just want to display the information an article contains, use this.
	 */
	public String toString() { // A toString used for testing that all of the information reached where it
								// needed to go.

		return "The information of this article is as follows: " + "\nArticleNumber: " + this.articleNumber + "\n"
				+ this.listAuthorsIEEE() + "\nJournal: " + this.journal + "\nTitle: " + this.title + "\nYear: "
				+ this.year + "\nVolume: " + this.volume + "\nNumber: " + this.number + "\nPages: " + this.pages + "\n"
				+ this.listKeywords() + "\nDOI: " + this.doi + "\nISSN: " + this.iSSN + "\nMonth: " + this.month;

	}

	/**
	 * @return returns article number.
	 */
	public String getArticleNumber() {
		return articleNumber;
	}

	/**
	 * @return returns author array.
	 */
	public String[] getAuthors() {
		return authors;
	}

	/**
	 * @return returns journal string.
	 */
	public String getJournal() {
		return journal;
	}

	/**
	 * @return returns title string.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return returns year string.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return returns volume string.
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return returns article number in the form of a string.
	 */
	public String getNumber() { 
		return number;
	}

	/**
	 * @return returns article pages in the form of a string.
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * @return returns an array of keywords from an article.
	 */
	public String[] getKeywords() {
		return keywords;
	}

	/**
	 * @return returns DOI string.
	 */
	public String getDoi() {
		return doi;
	}

	/**
	 * @return returns ISSN in the form of a string.
	 */
	public String getiSSN() {
		return iSSN;
	}

	/**
	 * @return returns month string.
	 */
	public String getMonth() { 
		return month;
	}

	/**
	 * @param articleNumber the article number you want to change.
	 */
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	/**
	 * @param authors the array of authors you want to change to.
	 */
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	/**
	 * @param journal the journal title you want to change.
	 */
	public void setJournal(String journal) {
		this.journal = journal;
	}

	/**
	 * @param title the title you want to change.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param year the year you want to change.
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @param volume the volume you want to change.
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * @param number the article number you want to change (as a string).
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @param pages the page numbers you want to change.
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}

	/**
	 * @param keywords the array of keywords you want to change to.
	 */
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	/**
	 * @param doi the DOI you want to change.
	 */
	public void setDoi(String doi) {
		this.doi = doi;
	}

	/**
	 * @param iSSN the ISSN you want to change (in the form of a string).
	 */
	public void setiSSN(String iSSN) {
		this.iSSN = iSSN;
	}

	/**
	 * @param month the month you want to change.
	 */
	public void setMonth(String month) {
		this.month = month;
	}
}
