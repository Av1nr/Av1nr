import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MyArrayListTest {
    private MyArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }
    @Test
    void addItem(){
        list.add("A");
        list.add("B");
        assertEquals(2 ,list.size());
        list.add("C");
        assertEquals(3 , list.size());
        assertEquals("C" , list.get(2));

    }
    @Test
    void addByIndex(){
        list.add("A");
        list.add("B");
        assertEquals("B" , list.get(1));
        list.add(1 , "C");
        assertEquals("C" , list.get(1));
        assertEquals("B" , list.get(2));
    }
    @Test
    void get(){
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("C" , list.get(2));
        assertEquals("B" , list.get(1));
    }
    @Test
    void removeByIndex(){
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove(0);
        assertEquals("B" , list.get(0));
    }
    @Test
    void removeBoolean(){
        list.add("A");
        list.add("B");
        list.add("C");
        assertTrue(list.remove("A"));
        assertFalse(list.remove("D"));
    }
    @Test
    void size(){
        list.add("A");
        list.add("B");
        assertEquals(2 ,list.size());
        list.add("C");
        assertEquals(3 , list.size());
    }
    @Test
    void isEmpty(){
        assertTrue(list.isEmpty());
        list.add("A");
        list.add("B");
        list.add("C");
        assertFalse(list.isEmpty());
    }
    @Test
    void clear(){
        list.add("A");
        list.add("B");
        list.add("C");
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }
    @Test
    void contains(){
        list.add("A");
        assertFalse(list.contains("B"));
        list.add("B");
        assertTrue(list.contains("B"));
        list.add("C");
    }
    @Test
    void indexOf(){
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals(0 , list.indexOf("A"));
        assertEquals(1 , list.indexOf("B"));
    }
}
