import java.io.*;
import java.net.*;

class TCPServer {
	public static int servernum = 1080;

	public static void main(String argv[]) throws Exception {
		Movie BD1 = new Movie("Titanic", 1, "2020/10/23");
		Movie AD1 = new Movie("Kingkong", 1, "2020/10/23");
		Movie CD1 = new Movie("Avatar", 1, "2020/10/23");
		Movie BT1 = new Movie("Titanic", 1, "2020/10/24");
		Movie AT1 = new Movie("Kingkong", 1, "2020/10/24");
		Movie CT1 = new Movie("Avatar", 1, "2020/10/24");

		Movie BD6 = new Movie("Titanic", 6, "2020/10/23");
		Movie AD6 = new Movie("Kingkong", 6, "2020/10/23");
		Movie CD6 = new Movie("Avatar", 6, "2020/10/23");
		Movie BT6 = new Movie("Titanic", 6, "2020/10/24");
		Movie AT6 = new Movie("Kingkong", 6, "2020/10/24");
		Movie CT6 = new Movie("Avatar", 6, "2020/10/24");

		Movie BD9 = new Movie("Titanic", 9, "2020/10/23");
		Movie AD9 = new Movie("Kingkong", 9, "2020/10/23");
		Movie CD9 = new Movie("Avatar", 9, "2020/10/23");
		Movie BT9 = new Movie("Titanic", 9, "2020/10/24");
		Movie AT9 = new Movie("Kingkong", 9, "2020/10/24");
		Movie CT9 = new Movie("Avatar", 9, "2020/10/24");

		theater onePm = new theater(1, 1);
		theater sixPm = new theater(6, 6);
		theater ninePm = new theater(9, 9);

		sixPm.insert(BD6);
		sixPm.insert(AD6);
		sixPm.insert(CD6);
		sixPm.insert(BT6);
		sixPm.insert(AT6);
		sixPm.insert(CT6);

		onePm.insert(AD1);
		onePm.insert(BD1);
		onePm.insert(CD1);
		onePm.insert(AT1);
		onePm.insert(BT1);
		onePm.insert(CT1);

		ninePm.insert(AD9);
		ninePm.insert(CD9);
		ninePm.insert(BD9);
		ninePm.insert(AT9);
		ninePm.insert(CT9);
		ninePm.insert(BT9);

		String DATE[] = { BD1.getDate(), BT1.getDate() };
		String Names[] = { BD1.getMovie(), AD6.getMovie(), CD9.getMovie() };
		int shows[] = { BD1.getTime(), BD6.getTime(), BD9.getTime() };
		KsuCinema ksu = new KsuCinema();

		ksu.insert(sixPm.theaterNum, sixPm);
		ksu.insert(onePm.theaterNum, onePm);
		ksu.insert(ninePm.theaterNum, ninePm);

		ServerSocket welcomeSocket = new ServerSocket(servernum);

		String name;
		boolean kind;
		int time;
		int numOfTicket;
		String info;
		String date;
		while (true) {

			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			//////

			date = inFromClient.readLine(); ////Storing the date
			if (ksu.checkDate(date, DATE)) {
				outToClient.writeBytes("true" + '\n');

////////  List all the movies 
				outToClient.writeBytes(ksu.allMovies(date) + '\n');

/////////////

				name = inFromClient.readLine(); /////storing the name

				while(!ksu.checkName(name,Names)){
					outToClient.writeBytes("false" +'\n');
					outToClient.writeBytes(ksu.allMovies(date) + '\n');
					name = inFromClient.readLine();
					
				}
				outToClient.writeBytes("true" + '\n');
				outToClient.writeBytes(ksu.availableShows(name, date) + '\n');

				int t = Integer.valueOf(inFromClient.readLine());/////storing the time

				while (!ksu.checkTime(t, shows)) {
					outToClient.writeBytes("false" + '\n');
					outToClient.writeBytes(ksu.availableShows(name, date) + '\n');
					t = Integer.valueOf(inFromClient.readLine());
				}
				outToClient.writeBytes("true" + '\n');
				info = inFromClient.readLine();

				String[] Info = info.split(",");/////storing the seats type and number

				numOfTicket = Integer.valueOf(Info[0]);/////storing the seats

				if (Info[1].equalsIgnoreCase("VIP"))/// type
					kind = false;
				else
					kind = true;
				String in;
				int cost;

				time = Integer.valueOf(Info[2]);

				if (ksu.choseMovie(name, kind, time, numOfTicket, date)) {   ///Processing  to find the correct movie 
					outToClient.writeBytes("yes" + '\n');
					cost = ksu.getcost(numOfTicket, kind);  ///// calculate the cost
					in = ksu.details(time, kind, name, cost, numOfTicket, date);/// returning the booking information
					outToClient.writeBytes(in + '\n');

					info = "Your reservation are complete ";
					outToClient.writeBytes(info + '\n');
				}

				else {
					outToClient.writeBytes("no" + '\n');
					info = "Sorry the movie is fully booked ";
					outToClient.writeBytes(info + '\n');

				}

			}
			else
				outToClient.writeBytes("no" + '\n');
			
		}
	}
}