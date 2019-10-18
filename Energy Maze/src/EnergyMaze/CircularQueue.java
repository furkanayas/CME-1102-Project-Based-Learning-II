package EnergyMaze;

//sıradan circularqueue
public class CircularQueue {
	private int rear, front;
	private Item [] elements;
	int size;

	public CircularQueue(int capacity)
	{
		elements = new Item[capacity];
		rear=-1;
		front=0;
		size = capacity;

	}

	public void enqueue(Item data)
	{
	   if(isFull())
		   System.out.println("Queue is owerflow");
	   else 
	   { rear = (rear + 1) % elements.length;
	     elements[rear]= data;
	   }
	}

	public Item dequeue()
	{		
		if(isEmpty())
		{
			System.out.println("Queue is empty");
			return null;
		}
		
		else 
		{
			Item retData = elements[front];
			elements[front]= null;
			front = (front + 1) % elements.length;
			return retData;
		}
		
	}

	public Item peek()
	{
		
		if(isEmpty())
		{
			System.out.println("Queue is empty");
			return null;
		}
		
		else
		{
			return elements[front];
		}
		

	}

	public boolean isEmpty()
	{
			
	return elements[front] == null;

	}

	public boolean isFull()
	{

		return (front == (rear + 1) % elements.length && elements[front] != null && elements[rear] != null);

	}

	public int size()
	{
		int count = 0;
		for (int i = 0; i < size; i++){
			if (elements[i] != null)
				count++;
		}
		return count;
	}

	
	 //Girdi dolana kadar circular queue yu itemlerle dolduruyor ve dolu kuyruğu yansıtıyor
	 
	
	public static CircularQueue girdial(){
		CircularQueue input = new CircularQueue(10);//10 luk q
		while (!input.isFull()){			//dolana kadar
			input.enqueue(new Item());	//yeni itemi al
		}
		return input; //yansıt
	}
	
	//İtem eksildiğinde yeni girdi almak için , tamamlamak için var ,full olunca çeviriyor
	public static CircularQueue girditazele(CircularQueue queue){
		while (!queue.isFull()){
			queue.enqueue(new Item());
		}
		return queue;
	}
	
	

}
