import java.util.ArrayList;

public class KsuCinema {

	private BSTNode root, current;

	public KsuCinema() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public String details(int t, boolean k, String n, int cost, int ticket, String date) {

		String info = "";

		if (k)
			info = " Movie name: " + n + "\n Class type: Regular Seat \n" + " Show time : " + t + "\n Date: " + date
					+ "\n Number of ticket: " + ticket + "\n Total cost: " + cost + "SR";

		else
			info = " Movie name: " + n + "\n Class type: VIP Seat \n" + " Show time : " + t + "\n Date: " + date
					+ "\n Number of ticket: " + ticket + "\n Total cost: " + cost + "SR";
		return info;
	}

	public boolean checkDate(String date, String Date[]) {

		for (int i = 0; i < Date.length; i++) {
			if (Date[i].equalsIgnoreCase(date))
				return true;
		}
		return false;

	}

	public boolean checkTime(int time, int Time[]) {

		for (int i = 0; i < Time.length; i++) {
			if (Time[i] == time)
				return true;
		}
		return false;

	}

	public boolean checkName(String name, String Name[]) {

		for (int i = 0; i < Name.length; i++) {
			if (Name[i].equalsIgnoreCase(name))
				return true;
		}
		return false;

	}

	public String allMovies(String date) {
		String movies = "";

		theater th = root.data;

		th.findFirst();

		while (!th.last()) {
			if (th.retrieve().getDate().equalsIgnoreCase(date))
				movies = movies.concat(th.retrieve().getMovie() + " , ");

			th.findNext();
		}
		if (th.retrieve().getDate().equalsIgnoreCase(date))
			movies = movies.concat(th.retrieve().getMovie());

		return movies;
	}

	public int getcost(int ticket, boolean kind) {
		theater th = root.data;
		return th.getcost(ticket, kind);
	}

	public String availableShows(String movie, String date) {

		ArrayList<Integer> available = new ArrayList<Integer>();
		available = AvailableShows(root, movie, available, date);

		String shows = "";
		int l = available.size();
		int j = 0;
		int lastTime = 0; ////// just to remove the comma for the last elmente
		for (int i = 0; i < l; i++) {
			j = available.remove(0);

			lastTime += i;
			if (lastTime != l)
				shows = shows.concat(String.valueOf(j) + "Pm , ");
			else
				shows = shows.concat(String.valueOf(j) + "Pm");

		}

		return shows;
	}

	private ArrayList<Integer> AvailableShows(BSTNode t, String name, ArrayList<Integer> available, String date){
		if (t == null)
			return available;

		t.data.findFirst();
		while (!t.data.last()) {
			if (t.data.retrieve().getMovie().equalsIgnoreCase(name)
					&& t.data.retrieve().getDate().equalsIgnoreCase(date)) {
				if (!t.data.retrieve().noSeatsAvailable())
					available.add(t.data.theaterNum);
			}
			t.data.findNext();
		}
		if (t.data.retrieve().getMovie().equalsIgnoreCase(name) && t.data.retrieve().getDate().equalsIgnoreCase(date)){
			if (!t.data.retrieve().noSeatsAvailable())
				available.add(t.data.theaterNum);
		}

		AvailableShows(t.left, name, available, date);
		AvailableShows(t.right, name, available, date);

		return available;
	}

	public boolean choseMovie(String name, boolean kind, int time, int numOfTicket, String date) {

		theater th = theater(time);

		if (th == null)
			return false;

		else {
			return th.lockingForTheater(name, kind, numOfTicket, date);

		}

	}

	private theater theater(int time) {

		if (findkey(time))
			return retrieve();

		else
			return null;
	}

	public theater retrieve() {
		return current.data;
	}

	public boolean findkey(int tkey) {
		BSTNode p = root, q = root;

		if (empty())
			return false;

		while (p != null) {
			q = p;
			if (p.keyTime == tkey) {
				current = p;
				return true;
			} else if (tkey < p.keyTime)
				p = p.left;
			else
				p = p.right;
		}

		current = q;
		return false;
	}

	public boolean insert(int k, theater val) {
		BSTNode p, q = current;

		if (findkey(k)) {
			current = q; // findkey() modified current
			return false; // key already in the BST
		}

		p = new BSTNode(k, val);
		if (empty()) {
			root = current = p;
			return true;
		} else {
			// current is pointing to parent of the new key
			if (k < current.keyTime)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	///////////////////////////////////////// class
	///////////////////////////////////////// node/////////////////////////////////
	class BSTNode {
		public int keyTime;
		public theater data;
		public BSTNode left, right;

		/** Creates a new instance of BSTNode */
		public BSTNode(int k, theater val) {
			keyTime = val.theaterNum;
			data = val;
			left = right = null;
		}

		@Override
		public String toString() {
			return " [keyTime=" + keyTime + ", data=" + data + "]";
		}

	}

	///////////////////////////////////////// end of class
	///////////////////////////////////////// node/////////////////////////////////

}
