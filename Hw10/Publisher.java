public class Publisher {
	private String PubName;
	private int PubYear;

	/** Creates a new instance of Subject */
	public Publisher() {
		setPubName("");
		setPubYear(0);

	}

	public Publisher(String Name, int Year) {
		setPubName(Name);
		setPubYear(Year);
	}

	public void setPubName(String Name) {
		this.PubName = Name;
	}

	public void setPubYear(int Year) {
		this.PubYear = Year;
	}

	public String getPubName() {
		return (this.PubName);
	}

	public int getPubYear() {
		return (this.PubYear);
	}

	public String toString() {
		String str = "";
		str = getPubName() + " " + getPubYear() + " ";
		return (str);
	}
}
