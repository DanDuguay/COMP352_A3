public class Main
{
    static ArrayHeap<String> arrayHeap = new ArrayHeap<>();
    public static void main(String[] args)
    {
        Entry<String> e1 = new Entry<>(1, "A", 0);
        Entry<String> e2 = new Entry<>(25,"B",0);
        Entry<String> e3 = new Entry<>(2, "C",0);
        Entry<String> e4 = new Entry<>(45,"D",0);
        Entry<String> e5 = new Entry<>(51,"E",0);
        Entry<String> e6 = new Entry<>(6,"F",0);
        Entry<String> e7 = new Entry<>(6,"G",0);

        Entry<String>[] entryArray = new Entry[]{e1,e2,e3,e4,e5,e6,e7};

        System.out.println("current State: " +arrayHeap.getState());
        arrayHeap.insert(e1);
        arrayHeap.insert(e3);
        arrayHeap.insert(e2);
        arrayHeap.insert(e6);
        arrayHeap.insert(e2);
        arrayHeap.insert(e4);
        arrayHeap.insert(e5);
        arrayHeap.insert(e7);
        arrayHeap.insert(e4);

        arrayHeap.toString();
        System.out.println("===========================================================");
        arrayHeap.toggle();
        arrayHeap.toString();
    }
}