
import java.io.Serializable;

public class Assignment implements Serializable {
    private String title;
    private String description;

    public Assignment(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title + ": " + description;
    }
}
