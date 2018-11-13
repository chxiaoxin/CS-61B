import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static OffByN offbyn = new OffByN(3);
    @Test
    public void testOffByN() {assertTrue(offbyn.equalChars('a','d'));}
}
