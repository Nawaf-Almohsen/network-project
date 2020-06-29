
public class Movie {

	@Override
	public String toString() {
		System.out.println(" [movie Name=" + movieName + ", time=" + time + ", VIP Seats lift="
				+ (nbvipTicket - counterVip) + ", regular Seats lift=" + (nbregTicket - counterReg) + " ]");
		return "Movie [movieName=" + movieName + ", time=" + time + "]";
	}

	private String movieName;
	private int time;
	private final int nbvipTicket = 15;
	private final int nbregTicket = 50;
	private final int vipCost = 50;
	private final int regCoast=25;
	private String date;
	
	public int getTotalVipCost( int ticket) {
		int total = vipCost*ticket;
		
		return total;
	}

	public int getTotalRegCost(int ticket) {
		
		int total = regCoast*ticket;
		
		return total;
	}

	private int counterVip;
	private int counterReg;

	public Movie(String movieName, int time,String d) {

		this.time = time;
		this.movieName = movieName;
		counterVip = 0;
		counterReg = 0;
		date=d;

	}
	
	public boolean noSeatsAvailable() {
		if(fullVipSeats()&&fullRegSeats())
			return true;
		
		return false;
		
	}

	public boolean canReserve(int ticket , boolean kind) {
		
		if(kind) {
			if (ticket > nbregTicket || fullRegSeats())
				return false;

			else {

				int check = counterReg + ticket;

				if (check > nbregTicket)
					return false;

				return true;
			}
		}
		else {
			if (ticket > nbvipTicket || fullVipSeats())
				return false;

			else {
				int check = counterVip + ticket;

				if (check > nbvipTicket)
					return false;


				return true;
			}
		}
				
	}
	public boolean fullVipSeats() {
		return nbvipTicket == counterReg;
	}

	public boolean fullRegSeats() {
		return nbregTicket == counterVip;
	}

	// reserve seat

	public boolean reserveSeat(int ticket, boolean kind) { // if the kind equals true it means that the ticket is
															// reqular else it will proccess as a vip ticket

		if (ticket < 1)
			return false;

		if (kind) {
			return reserveSeatReg(ticket);

		}

		else {

			return reserveSeatVip(ticket);
		}
	}

	private boolean reserveSeatReg(int ticket) {

		if (ticket > nbregTicket || fullRegSeats())
			return false;

		else {

			int check = counterReg + ticket;

			if (check > nbregTicket)
				return false;

			counterReg += ticket;

			return true;
		}
	}

	private boolean reserveSeatVip(int ticket) {

		if (ticket > nbvipTicket || fullVipSeats())
			return false;

		else {
			int check = counterVip + ticket;

			if (check > nbvipTicket)
				return false;

			counterVip += ticket;

			return true;
		}
	}

	public String getMovie() {
		return movieName;
	}

	public int getTime() {
		return time;
	}
	public String getDate() {
		
		return date;
	}

}
