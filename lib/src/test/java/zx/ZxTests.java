package zx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZxTests {

    @Test
    public void test() {
        var promise = Zx.$("echo hello");
        var s = promise.join();
        assertEquals("hello\n", s);
    }
}
