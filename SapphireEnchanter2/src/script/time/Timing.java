package script.time;

import java.util.Random;

public enum Timing {
    TIME_GAUSSIAN,

    ;

    Random r = new Random();
    int timeGaussian = (int)r.nextGaussian();

}
