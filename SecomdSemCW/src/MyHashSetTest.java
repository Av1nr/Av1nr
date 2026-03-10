import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyHashSetTest {
    private MyHashSet<String> set;

    @Before
    public void setUp() {
        set = new MyHashSet<>();
    }

    @Test
    public void testAdd() {
        assertTrue(set.add("element1"));
        assertFalse(set.add("element1")); // дубликат
        assertEquals(1, set.size());
    }

    @Test
    public void testRemove() {
        set.add("element1");
        set.add("element2");

        assertTrue(set.remove("element1"));
        assertFalse(set.contains("element1"));
        assertEquals(1, set.size());

        assertFalse(set.remove("element3"));
        assertEquals(1, set.size());
    }

    @Test
    public void testContains() {
        set.add("element");
        assertTrue(set.contains("element"));
        assertFalse(set.contains("missing"));
    }

    @Test
    public void testSizeAndIsEmpty() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        set.add("a");
        assertFalse(set.isEmpty());
        assertEquals(1, set.size());

        set.remove("a");
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testClear() {
        set.add("a");
        set.add("b");
        set.clear();
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
        assertFalse(set.contains("a"));
    }
    @Test
    public void testAddDuplicateAfterRemove() {
        set.add("x");
        set.remove("x");
        assertTrue(set.add("x"));
        assertEquals(1, set.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullThrowsNPE() {
        set.add(null);
    }
}