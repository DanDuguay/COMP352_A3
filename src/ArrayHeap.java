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
        this.lastElement = -1;
        //heap = heapify(heap);
    }

    public ArrayHeap(int size, boolean isMinHeap)
    {
        this.size = size;
        this.isMinHeap = isMinHeap;
        heap = new Entry[size];
        this.lastElement = -1;
        //heap = heapify(heap);
    }

    public  int getLastElement() { return this.lastElement;}
    public void setLastElement(int newValue) { this.lastElement = newValue;}

    public String getState()
    {
        if (isMinHeap)
            return "minHeap";
        return "maxHeap";
    }

    public void setState(boolean isMinHeap)
    {
        this.isMinHeap = isMinHeap;
    }

    public void toggle()
    {
        this.isMinHeap = !isMinHeap;
        heapify();
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
        int key2 = e2.getKey();

        if (isMinHeap)
        {
            if(key1 > key2)
                return 1;
            if (key1 == key2)
                return 0;

            return -1;
        }
        else
        {
            if (key1 > key2)
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

    protected void swap(int e1Position, int e2Position)
    {
        Entry<V> temp = new Entry<>(heap[e1Position]);
        heap[e1Position] = new Entry<>(heap[e2Position]);
        heap[e2Position] = new Entry<>(temp);

    }

    protected void upHeap(int ePosition)
    {
        while (ePosition > 0)
        {
            int pPosition = parent(heap[ePosition]);
            if (heapComparator(heap[ePosition], heap[pPosition], isMinHeap) >= 0) break;
            swap(ePosition, pPosition);
            //e.setPosition(p.getPosition());
        }
    }

    protected void downHeap(int ePosition)
    {
        while (hasLeft(heap[ePosition]))
        {
            int leftIndex = left(heap[ePosition]);
            int childToSwapIndex = leftIndex;
            if (hasRight(heap[ePosition]))
            {
                int rightIndex = right(heap[ePosition]);
                if (heapComparator(heap[leftIndex], heap[rightIndex], isMinHeap) > 0)
                    childToSwapIndex = rightIndex;
            }
            if (heapComparator(heap[childToSwapIndex], heap[ePosition], isMinHeap) >=0) break;
            swap(ePosition,childToSwapIndex);
            //heap[ePosition].setPosition(heap[childToSwapIndex].getPosition());
        }
    }

    public Entry<V> insert(int key, V value)
    {
        if(isFull())
            heap = increaseHeapSize();

        lastElement++;
        Entry<V> newest = new Entry<>(key,value,lastElement);
        heap[lastElement] = newest;
        upHeap(newest.getPosition());
        return newest;
    }
    public Entry<V> insert(Entry e)
    {
        if(isFull())
            heap= increaseHeapSize();

        lastElement++;
        e.setPosition(lastElement);
        Entry<V> newest = new Entry<>(e);
        heap[lastElement] = newest;
        upHeap(newest.getPosition());
        return newest;
    }

    public Entry<V> min()
    {
        if(isEmpty()) return null;
        return heap[0];
    }
    protected void heapify()
    {
        int startingIndex = parent(heap[lastElement]);
        for (int i = startingIndex; i >= 0; i--)
            downHeap(i);
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
