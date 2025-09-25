import org.junit.Test;
import static org.junit.Assert;

public class SLL_IteratorTest {

    public static final String[] abc = { "A", "B", "C" };
    public static final String[] a = { "A" };
    public static final String[] empty = {};
    public static final String[] ab = { "A", "B" };
    public static final String[] b = { "B" };
    public static final String[] cba = { "C", "B", "A" };
    public static final String[] ba = { "B", "A" };
    public static final String[] bac = { "B", "A", "C" };
    public static final String[] abdc = { "A", "B", "D", "C" };
    public static final String[] dac = { "D", "A", "C" };
    public static final String[] eb = { "E", "B" };
    public static final String[] dbac = { "D", "B", "A", "C" };
    public static final String[] ac = { "A", "C" };
    public static final String[] e = { "E" };
    public static final String[] debac = { "D", "E", "B", "A", "C" };
    public static final String[] fg = { "F", "G" };
    public static final String[] debacfg = { "D", "E", "B", "A", "C", "F", "G" };
    public static final String[] hi = { "H", "I" };
    public static final String[] hidebacfg = { "H", "I", "D", "E", "B", "A", "C", "F", "G" };
    public static final String[] dbc = { "D", "B", "C" };
    public static final String[] dec = { "D", "E", "C" };
    public static final String[] def = { "D", "E", "F" };

    @Test
    public void test_iterator_basic1() {
        SLL<String> list = SLLTest.makeSLL(abc);
        SLL_Iterator<String> iter = new SLL_Iterator<>(list);
        Assert.assertTrue("^ABC hasNext", iter.hasNext());
        Assert.assertTrue("^ABC next == A", iter.next().equals("A"));
        Assert.assertTrue("A^BC hasNext", iter.hasNext());
        Assert.assertTrue("A^BC next == B", iter.next().equals("B"));
        Assert.assertTrue("AB^C hasNext", iter.hasNext());
        Assert.assertTrue("AB^C next == A", iter.next().equals("C"));
        Assert.assertTrue("ABC^ !hasNext", !iter.hasNext());
    }

    @Test
    public void test_iterator_basic2() {
        SLL<String> list = SLLTest.makeSLL(empty);
        SLL_Iterator<String> iter = new SLL_Iterator<String>(list);
        Assert.assertTrue("^empty hasNext", !iter.hasNext());
    }

    @Test
    public void test_iterator_set1() {
        SLL<String> list = SLLTest.makeSLL(empty);
        SLL_Iterator<String> iter = new SLL_Iterator<>(list);
        Assert.assertThrows("exception on initial set",
            MissingElementException.class,
            () -> { iter.set("D");});
    }

    @Test
    public void test_iterator_set2(){
        SLL<String> list = SLLTest.makeSLL(abc);
        SLL_Iterator<String> iter1 = new SLL_Iterator<>(list);
        iter1.next();
        iter1.set("D");
        String s = SLLTest.verifySLL(list, dbc);
        Assert.assertTrue("A^BC.set(D)->DBC" + s, s.equals(""));
        iter1.next();
        iter1.set("A");
        s = SLLTest.verifySLL(list, dac);
        Assert.assertTrue("D^BC.set(A)->DAC" + s, s.equals(""));
        iter1.set("E");
        s = SLLTest.verifySLL(list, dec);
        Assert.assertTrue("D^AC.set(E)->DEC" + s, s.equals(""));
        iter1.next();
        iter1.set("F");
        s = SLLTest.verifySLL(list, def);
        Assert.assertTrue("DE^C.set(F)->DEF" + s, s.equals(""));
    }

    @Test
    public void test_iterator_add() {
        SLL<String> list = SLLTest.makeSLL(empty);
        SLL_Iterator<String> iter = new SLL_Iterator<>(list);
        iter.add("A");
        String s = SLLTest.verifySLL(list, a);
        Assert.assertTrue("empty.add(A)->A" + s, s.equals(""));
        iter.add("B");
        s = SLLTest.verifySLL(list, ab);
        Assert.assertTrue("A^.add(B)->AB" + s, s.equals(""));
        list = SLLTest.makeSLL(bac);
        iter = new SLL_Iterator<>(list);
        iter.add("D");
        s = SLLTest.verifySLL(list, dbac);
        Assert.assertTrue("^BAC.add(D)->DBAC" + s, s.equals(""));
        iter.add("E");
        s = SLLTest.verifySLL(list, debac);
        Assert.assertTrue("D^BAC.add(E)->DEBAC" + s, s.equals(""));
        iter.next();
        iter.next();
        iter.next();
        iter.add("F");
        iter.add("G");
        s = SLLTest.verifySLL(list, debacfg);
        Assert.assertTrue("DEBAC^.add(F).add(G)->DEBACFG" + s, s.equals(""));
        list = SLLTest.makeSLL(abc);
        iter = new SLL_Iterator<>(list);
        iter.next();
        iter.next();
        iter.add("D");
        s = SLLTest.verifySLL(list, abdc);
        Assert.assertTrue("AB^C.add(D)->ABDC" + s, s.equals(""));

    }

    @Test
    public void test_iterator_remove() {
        SLL<String> list = SLLTest.makeSLL(empty);
        SLL_Iterator<String> iter = new SLL_Iterator<>(list);
        Assert.assertThrows("exception on empty remove",
                MissingElementException.class,
                () -> {
                    iter.remove();
                });
        list = SLLTest.makeSLL(bac);
        SLL_Iterator<String> iter2 = new SLL_Iterator<>(list);
        Assert.assertThrows("exception on initial remove",
                MissingElementException.class,
                () -> {
                    iter2.remove();
                });
        iter2.next();
        iter2.remove();
        String s = SLLTest.verifySLL(list, ac);
        Assert.assertTrue("B^AC.remove->AC" + s, s.equals(""));
        Assert.assertThrows("exception on second remove",
                MissingElementException.class,
                () -> {
                    iter2.remove();
                });
        iter2.next();
        iter2.next();
        iter2.remove();
        s = SLLTest.verifySLL(list, a);
        Assert.assertTrue("AC^.remove->A" + s, s.equals(""));
        SLL_Iterator<String> iter3 = new SLL_Iterator<>(list);
        iter3.next();
        iter3.remove();
        s = SLLTest.verifySLL(list, empty);
        Assert.assertTrue("A^.remove->empty" + s, s.equals(""));
    }
}
