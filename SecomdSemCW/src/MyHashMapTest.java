
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyHashMapTest {
    private MyHashMap<String, String> map;

    @Before
    public void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    public void testPutAndGet() {
        assertNull(map.put("key1", "value1"));
        assertEquals("value1", map.get("key1"));
        assertEquals(1, map.size());

        // Обновление существующего ключа
        assertEquals("value1", map.put("key1", "value2"));
        assertEquals("value2", map.get("key1"));
        assertEquals(1, map.size());
    }

    @Test
    public void testRemove() {
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals("value1", map.remove("key1"));
        assertNull(map.get("key1"));
        assertEquals(1, map.size());

        assertNull(map.remove("key3")); // несуществующий ключ
        assertEquals(1, map.size());
    }

    @Test
    public void testRemoveFromHeadAndMiddle() {
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        assertEquals("1", map.remove("A"));
        assertNull(map.get("A"));
        assertEquals(2, map.size());
        assertEquals("3", map.remove("C"));
        assertNull(map.get("C"));
        assertEquals(1, map.size());
        assertEquals("2", map.get("B"));
    }

    @Test
    public void testContainsKey() {
        map.put("key", "value");
        assertTrue(map.containsKey("key"));
        assertFalse(map.containsKey("missing"));
    }

    @Test
    public void testContainsValue() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertTrue(map.containsValue("value1"));
        assertTrue(map.containsValue("value2"));
        assertFalse(map.containsValue("value3"));
    }

    @Test(expected = NullPointerException.class)
    public void testPutNullKeyThrowsNPE() {
        map.put(null, "value");
    }

    @Test
    public void testSizeAndIsEmpty() {
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put("key", "value");
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());

        map.remove("key");
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void testClear() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("key1"));
        assertNull(map.get("key2"));
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, "value" + i);
        }
        assertEquals(20, map.size());
        for (int i = 0; i < 20; i++) {
            assertEquals("value" + i, map.get("key" + i));
        }
        assertTrue(map.containsKey("key19"));
    }

    @Test
    public void testCollisionHandling() {
        class Key {
            private final String id;

            Key(String id) {
                this.id = id;
            }

            @Override
            public int hashCode() {
                return 42;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Key)) return false;
                return id.equals(((Key) obj).id);
            }
        }

        MyHashMap<Key, String> map = new MyHashMap<>();
        Key k1 = new Key("1");
        Key k2 = new Key("2");
        Key k3 = new Key("3");

        map.put(k1, "value1");
        map.put(k2, "value2");
        map.put(k3, "value3");

        assertEquals("value1", map.get(k1));
        assertEquals("value2", map.get(k2));
        assertEquals("value3", map.get(k3));

        map.remove(k2);
        assertNull(map.get(k2));
        assertEquals("value1", map.get(k1));
        assertEquals("value3", map.get(k3));
    }
}
