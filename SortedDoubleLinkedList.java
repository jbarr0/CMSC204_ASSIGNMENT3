import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	private Comparator<T> compareTool;

	SortedDoubleLinkedList(Comparator<T> compareTool) {
		super();
		this.compareTool = compareTool;
	}

	@Override
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}

	@Override
	public Node<T> remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}

	public void add(T data) {
		Node<T> tracker = firstNode;
		if (size == 0) {
			Node newNode = new Node(data);
			firstNode = newNode;
			lastNode = newNode;
			size++;
			return;
		} else {
			Node newNode = new Node(data);
			while (tracker != null) {

				if (compareTool.compare(data, tracker.data) <= 0) //
				{
					if (tracker.prevNode == null) // adding to head
					{
						tracker.prevNode = newNode;
						newNode.nextNode = tracker;
						firstNode = newNode;
						size++;
						return;
					} else {
						tracker.prevNode.nextNode = newNode;
						newNode.prevNode = tracker.prevNode;
						newNode.nextNode = tracker;
						tracker.prevNode = newNode;
						size++;
						return;
					}
				}
				if (tracker.nextNode == null) {
					lastNode.nextNode = newNode;
					newNode.prevNode = lastNode;
					lastNode = newNode;
					size++;
					return;
				}
				tracker = tracker.nextNode;
			}
		}
	}
}

