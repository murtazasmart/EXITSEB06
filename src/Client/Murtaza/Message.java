package Client.Murtaza;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 6/3/2017.
 */
public class Message implements Serializable{
    String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
