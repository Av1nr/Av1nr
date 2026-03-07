import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyDoubleLinkedListTest {

    private MyDoubleLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyDoubleLinkedList<>();
    }
    @Test
    void addLast() {
        list.addLast("A");
        list.addLast("B");
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void addFirst() {
        list.addFirst("A");
        list.addFirst("B");
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
    }

    @Test
    void addByIndex() {
        list.add("A");
        list.add("C");
        list.add(1, "B");
        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }
    @Test
    void get() {
        list.add("A");
        list.add("B");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }
    @Test
    void remove() {
        list.add("A");
        list.add("B");
        String removed = list.removeFirst();
        assertEquals("A", removed);
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }
    @Test
    void removeLast() {
        list.add("A");
        list.add("B");
        assertEquals("B", list.removeLast());
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }
    @Test
    void removeByIndex() {
        list.add("A");
        list.add("B");
        list.add("C");
        String removed = list.remove(1); // удаляем "B"
        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void removeByValue() {
        list.add("A");
        list.add("B");
        list.add("A");
        assertTrue(list.remove("A"));
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1)); // второй "A" остался
    }

    @Test
    void contains() {
        list.add("A");
        list.add("B");
        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
        assertFalse(list.contains("C"));
    }

    @Test
    void indexOf() {
        list.add("A");
        list.add("B");
        list.add("A");
        assertEquals(0, list.indexOf("A"));
        assertEquals(1, list.indexOf("B"));
        assertEquals(-1, list.indexOf("C"));
    }
    @Test
    void size() {
        assertEquals(0, list.size());
        list.add("A");
        assertEquals(1, list.size());
        list.removeFirst();
        assertEquals(0, list.size());
    }
    @Test
    void isEmpty() {
        assertEquals(list.isEmpty() , true);
        list.add("A");
        assertFalse(list.isEmpty());
    }
}