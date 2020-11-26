package advice.afterreturningadvice;

import java.util.Random;

public class KeyGenerator {
    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_КЕУ = 0xACDF03F590AE56L;
    private Random rand = new Random();

    public long getKey() {
        int х = rand.nextInt(3);
        if (х == 1) {
            return WEAK_KEY;
        }
        return STRONG_КЕУ;
    }
}
