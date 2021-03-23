package insumo;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Papa implements Serializable {
    private int timer;
    public String id;

    public Papa(String id) {
        this.id = id;
        this.timer = ThreadLocalRandom.current().nextInt(4, 6 + 1);
    }

    public int decrementar(){
        timer --;
        return timer;
    }

    public String getId() {
        return id;
    }
}
