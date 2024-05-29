import java.util.jar.Attributes.Name;

public class Author {
    private String AuName;
    private String AuSurName;

    /** Creates a new instance of Subject */
    public Author() {
        setAuName("");
        setAuSurName("");

    }

    public Author(String Name, String SurName) {
        setAuName(Name);
        setAuSurName(SurName);
    }

    public void setAuName(String Name) {
        this.AuName = Name;
    }

    public void setAuSurName(String SurName) {
        this.AuSurName = SurName;
    }

    public String getAuName() {
        return (this.AuName);
    }

    public String getAuSurName() {
        return (this.AuSurName);
    }

    public String toString() {
        String str = "";
        str = getAuName() + " " + getAuSurName() + " ";
        return (str);
    }
}
