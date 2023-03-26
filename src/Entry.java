public class Entry<V>
{
    private int key;
    private V value;

    private int position;

    Entry(int key, V value, int position)
    {
        this.key = key;
        this.value = value;
        this.position = position;
    }

    Entry(Entry<V> e)
    {
        this.key = e.getKey();
        this.value = e.getValue();
        this.position = e.getPosition();
    }

    public <M> Entry(int key, M value)
    {
        this.key = key;
        this.value = (V) value;
    }

    int getKey()
    {
        return key;
    }

    V getValue()
    {
        return value;
    }

    int getPosition()
    {
        return position;
    }

    void setKey(int key)
    {
        this.key = key;
    };

    void setValue(V value)
    {
        this.value = value;
    }

    void setPosition(int position)
    {
        this.position = position;
    }

    int comparator(V value1, V value2)
    {
        if (value1.getClass() != value2.getClass())
            return -1;

        if (value1.getClass() == Integer.class)
            return Integer.compare((int) value1, (int) value2);

        if (value1.getClass() == String.class)
            return ((String) value1).compareTo((String) value2);

        return -1;
    }
}
