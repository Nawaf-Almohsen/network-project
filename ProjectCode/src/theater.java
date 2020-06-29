
public class theater {

	public int theaterNum;

	@Override
	public String toString() {
		return "theater [theaterNum=" + theaterNum + ", time=" + time + "]";
	}

	private int time;
	private node head;
	private node current;

	public theater(int theaterNum, int time) {
		this.theaterNum = theaterNum;
		head = current = null;
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	public int getTheaterNum() {

		return theaterNum;
	}

	public int getcost(int ticket , boolean kind) {
		int cost=0;
		if(kind)
			cost=head.data.getTotalRegCost(ticket);
		else
			cost=head.data.getTotalVipCost(ticket);
		
		return cost;
	}
	
	public boolean availablete (int ticket , boolean kind,String name) {
		
		current = head;

		boolean flag = false;
		while (current != null) {

			if (current.data.getMovie().equalsIgnoreCase(name)) {
				flag = true;
				break;
			}
			current = current.next;

		}

		if (flag) 
			return current.data.canReserve(ticket, kind);

		
		return false;
	}
	public boolean lockingForTheater(String name, boolean kind, int ticket,String date) {

		current = head;

		boolean flag = false;
		while (current != null) {

			if (current.data.getMovie().equalsIgnoreCase(name)&&current.data.getDate().equalsIgnoreCase(date)) {
				flag = true;
				break;
			}
			current = current.next;

		}

		if (flag) {
			return current.data.reserveSeat(ticket, kind);
		}
		return false;
	}

	//////////////////////// linkedlist methods//////////////////////////
	public boolean empty() {
		return head == null;
	}

	public boolean last() {
		return current.next == null;
	}

	public boolean full() {
		return false;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		current = current.next;
	}

	public Movie retrieve() {
		return current.data;
	}

	public void update(Movie val) {
		current.data = val;
	}

	public void insert(Movie val) {
		node tmp;

		if (empty())
			current = head = new node(val);
		else {
			tmp = current.next;
			current.next = new node(val);
			current = current.next;
			current.next = tmp;
		}
	}

	public void remove() {
		if (current == head)
			head = head.next;
		else {
			node tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	}

	//////////////////////// end of linkedlist methods//////////////////////////

	///////////////////////// class node////////////////////////////////////////////
	class node {
		public Movie data;
		public node next;

		public node() {
			data = null;
			next = null;
		}

		public node(Movie val) {
			data = val;
			next = null;
		}

		public Movie getData() {
			return data;
		}

		public void setData(Movie data) {
			this.data = data;
		}

		public node getNext() {
			return next;
		}

		public void setNext(node next) {
			this.next = next;
		}
	}
	///////////////////////// end of class
	///////////////////////// node////////////////////////////////////////////

}
