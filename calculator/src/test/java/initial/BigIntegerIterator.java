package initial;

import java.util.ArrayList;
import java.util.List;

class BigIntegerIterator {
    private final List<String> contain = new ArrayList<>(500);
    private final List<Integer> reference = new ArrayList<>(500);

    BigIntegerIterator(int i) {
        contain.add("" + i + "");
        reference.add(i);
    }

    Integer getContain() {
        return Math.max(Integer.decode(contain.get(0)),reference.get(0));
    }
}

