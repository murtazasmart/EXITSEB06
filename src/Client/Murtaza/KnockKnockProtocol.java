package Client.Murtaza;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 5/28/2017.
 */
class KnockKnockProtocol implements Serializable {
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
