package Exception_Code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestException {
    @Test
    public void test00() {
        assertEquals(2, 1 + 3);
    }

    @Test
    public void test01() {
        Executable e = () -> System.out.println(10/0);
        assertThrows(ArithmeticException.class, e);
    }

    @Test
    public void test02() {
        assertThrows(ArithmeticException.class, () -> {int x = 10/0; });
    }

}
