package kata;

import java.math.BigInteger;

class IdGenerator {

    private BigInteger j = new BigInteger("100000000000");

    public long next() {
        j = j.add(new BigInteger("1"));
        return j.longValue();
    }
}
