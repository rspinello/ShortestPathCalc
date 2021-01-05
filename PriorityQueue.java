public class PriorityQueue<E extends Comparable>
{ //  A MINIMUM  Priority Queue
    E[] A;
    int size;
    public PriorityQueue() //default constructor
    {
        A = (E[]) new Comparable[1000];
        size = 0;
    }

    public PriorityQueue(int n)
    {
        A = (E[]) new Comparable[n];
        size = 0;
    }

    public void heapafy(int i)
    {
        int left = 2*i+1;
        int right = 2*i+2;
        int smallest;
        if ( left < size && A[left].compareTo( A[i]) < 0)
            smallest = left;
        else
            smallest = i;


        if ( right < size && A[right].compareTo(A[smallest])< 0)
            smallest = right;

        if (smallest != i)
        {
            E temp = A[i];
            A[i] = A[smallest];
            A[smallest] = temp;
            heapafy(smallest);
        }
    }

    public void insert(E x)
    {

        A[size] = x;
        size++;
        buildHeap();
    }

    public E remove()
    {
        if (size == 0)
            return null;
        E temp = A[0];
        A[0] = A[size-1];
        heapafy(0);
        size--;
        return temp;
    }

    private void buildHeap()
    {
        for (int i = (size -2)/2; i >= 0; i--) // from the first node with children to root
            heapafy(i);
    }

    public boolean empty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public E top()
    {
        if (size == 0)
            return null;
        E temp = A[0];
        return temp;
    }
    public void print()
    {
        for (int i = 0; i <size; i++)
            System.out.println(A[i]);
    }
}