import java.lang.reflect.Array;

public class ArrayHeap<V>
{
    int size;
    int lastElement;
    public Entry<V>[] heap;

    private boolean isMinHeap;

    public ArrayHeap()
    {
        this.size = 5;
        this.isMinHeap = true;
        heap = new Entry[size];
        //heap = heapify(heap);
    }

    public ArrayHeap(int size, boolean isMinHeap)
    {
        this.size = size;
        this.isMinHeap = isMinHeap;
        heap = new Entry[size];
        //heap = heapify(heap);
    }

    public boolean getState()
    {
        return isMinHeap;
    }

    public void setState(boolean isMinHeap)
    {
        this.isMinHeap = isMinHeap;
    }

    public void toggle()
    {
        this.isMinHeap = !isMinHeap;
        heapify(this.heap);
    }

    public boolean isFull() { return (size-1 == lastElement);}
    public boolean isEmpty(){ return (lastElement == -1);}

    public Entry<V> top()
    {
        if (isEmpty())
            return null;
        else
            return heap[0];
    }

    public Entry<V>[] increaseHeapSize()
    {
        size *= size;
        Entry<V>[] newHeap = new Entry[size];
        for (int i = 0; i < heap.length;i++)
        {
            newHeap[i] = heap[i];
        }
        return newHeap;
    }
    public int size()
    {
        return lastElement+1;
    }

    public int heapComparator(Entry<V> e1, Entry<V> e2, boolean isMinHeap)
    {
        int key1 = e1.getKey();
        int key2 = e1.getKey();

        if (isMinHeap)
        {
            if(key1 < key2)
                return 1;
            if (key1 == key2)
                return 0;

            return -1;
        }
        else
        {
            if (key1 < key2)
                return -1;
            if (key1 == key2)
                return 0;

            return 1;
        }
    }

    protected int parent(Entry<V> e) { return (e.getPosition()-1)/2;}
    protected int left(Entry<V> e) { return 2*e.getPosition()+1;}
    protected int right(Entry<V> e) { return 2*e.getPosition()+2;}
    protected boolean hasLeft(Entry<V> e) { return left(e) < size();}
    protected boolean hasRight(Entry<V> e) { return right(e) < size();}

    protected void swap(Entry<V> e1, Entry<V> e2)
    {
        Entry<V> temp = e1;
        e1 = new Entry<V>(e2);
        e2 = new Entry<V>(temp);

    }

    protected void upHeap(Entry<V> e)
    {
        while (e.getPosition() > 0)
        {
            Entry<V> p = heap[parent(e)];
            if (heapComparator(e, p, getState()) >= 0) break;
            swap(e, p);
            //e.setPosition(p.getPosition());
        }
    }

    protected void downHeap(Entry<V> e)
    {
        while (hasLeft(e))
        {
            int leftIndex = left(e);
            int childToSwapIndex = leftIndex;
            if (hasRight(e))
            {
                int rightIndex = right(e);
                if (heapComparator(heap[leftIndex], heap[rightIndex], isMinHeap) > 0)
                    childToSwapIndex = rightIndex;
            }
            if (heapComparator(heap[childToSwapIndex], heap[e.getPosition()], isMinHeap) >=0) break;
            swap(e,heap[childToSwapIndex]);
            //e.setPosition(heap[childToSwapIndex].getPosition());
        }
    }

    public Entry<V> insert(int key, V value)
    {
        if(isFull())
            heap = increaseHeapSize();

        lastElement++;
        Entry<V> newest = new Entry<>(key,value,lastElement);
        heap[lastElement] = newest;
        return newest;
    }

    public Entry<V> min()
    {
        if(isEmpty()) return null;
        return heap[0];
    }
    protected Entry<V>[] heapify(Entry<V>[] heap)
    {
        int startingIndex = parent(heap[size()-1]);
        for (int i = startingIndex; i >= 0; i--)
            downHeap(heap[i]);
        return heap;
    }

    @Override
    public String toString()
    {
        for (int i = 0; i <= lastElement;i++)
        {
            System.out.println("Key: " + heap[i].getKey() + "\nValue: " + heap[i].getValue() + "\nPosition: " + heap[i].getPosition() + "\n" );
        }
        return null;
    }

}
