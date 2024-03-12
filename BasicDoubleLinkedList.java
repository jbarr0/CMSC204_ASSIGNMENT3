import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected Node firstNode, lastNode;
	int size;

	BasicDoubleLinkedList() {
		firstNode = lastNode = null;
		size = 0;
	}

	public void addToEnd(T data) {

		if (size == 0) // if there is no entries in the chain
		{
			Node newNode = new Node(data);
			size++;
			firstNode = newNode;
			lastNode = newNode;
		} else //
		{
			Node newNode = new Node(lastNode, data, null); // point back to prev node
			size++;
			lastNode.nextNode = newNode; //
			lastNode = newNode;
		}
	}

	public void addToFront(T data) {
		if (size == 0) // if there is no entries in the chain
		{
			Node newNode = new Node(data);
			size++;
			firstNode = newNode;
			lastNode = newNode;
		} else //
		{
			Node newNode = new Node(null, data, firstNode); // point back to prev node
			size++;
			firstNode.prevNode = newNode;
			firstNode = newNode;
		}
	}

	public T getFirst() {
		T r = (T) firstNode.data;
		return r;
	}

	public T getLast() {
		T r = (T) lastNode.data;
		return r;
	}

	public int getSize() {
		return size;
	}

	protected class Node<T> {
		T data;
		Node<T> prevNode;
		Node<T> nextNode;

		protected Node(T dataPortion) {
			this(null, dataPortion, null);
		}

		public Node(Node prevNode, T dataPortion, Node nextNode) {
			this.data = dataPortion;
			this.prevNode = prevNode;
			this.nextNode = nextNode;
		}
	}

	protected class DoubleLinkedListIterator<T> implements ListIterator<T> {
		Node<T> pointer;

		DoubleLinkedListIterator() {
			pointer = firstNode;

		}

		@Override
		public boolean hasNext() {
			return !(pointer == null);
		}

		@Override
		public T next() throws NoSuchElementException {
			if (!(hasNext()))
				throw new NoSuchElementException();
			else {
				T r = pointer.data;
				pointer = pointer.nextNode;
				return r;
			}
		}

		@Override
		public boolean hasPrevious() {
			if (pointer == firstNode) // only the firstNode will not have previous
				return false;
			else
				return true;
		}

		@Override
		public T previous() throws NoSuchElementException {
			if (!(hasPrevious()))
				throw new NoSuchElementException();
			if (pointer == null) {
				pointer = lastNode;
				return pointer.data;
			} else {
				pointer = pointer.prevNode;
				return pointer.data;
			}
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}

	public Node<T> remove(T target, Comparator<T> comparator) {

		ListIterator<T> it = iterator();
		Node<T> returnNode, tracker = firstNode;
		while (it.hasNext()) {

			if (comparator.compare(it.next(), target) == 0) // they are the same
			{
				if (tracker.prevNode == null) // if its the first element
				{
					returnNode = tracker;
					firstNode = tracker.nextNode;
					size--;
					return returnNode;
				}

				else if (tracker.nextNode == null) // if its the last element
				{
					returnNode = tracker;
					lastNode = tracker.prevNode;
					size--;
					return returnNode;
				} else

				{
					returnNode = tracker;
					tracker.prevNode.nextNode = tracker.nextNode;
					tracker.nextNode.prevNode = tracker.prevNode;
					size--;
					return returnNode;
				}
			}

			tracker = tracker.nextNode;

		}
		return null;
	}

	public T retrieveFirstElement() {
		T r = (T) firstNode.data;
		firstNode = firstNode.nextNode;
		size--;
		return r;
	}

	public T retrieveLastElement() {
		T r = (T) lastNode.data;
		lastNode = lastNode.prevNode;
		size--;
		return r;
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> returnArrayList = new ArrayList<>();
		ListIterator<T> it = iterator();
		while (it.hasNext()) {
			returnArrayList.add(it.next());
		}
		return returnArrayList;
	}

}
