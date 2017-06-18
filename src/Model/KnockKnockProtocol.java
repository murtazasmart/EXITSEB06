package Model;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class KnockKnockProtocol implements Serializable {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public KnockKnockProtocol() {
    }

    public KnockKnockProtocol(String text) {
        this.text = text;
    }

    String processInput(Object object) {
        return "INDIDE PROCESS INPUT";
    }

}

