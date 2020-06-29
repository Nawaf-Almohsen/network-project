import java.io.*;
import java.net.*;
import java.util.*;
class TCPClient {
	public static int servernum = 1080;

	public static void main(String argv[]) throws Exception {
		
		while (true) {
		
		boolean flag = true;
	

	
		Socket clientSocket = new Socket("localhost", servernum);
		
	
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		Scanner client = new Scanner(System.in);
		
	////// 
		
		String name; 
		String time="6"; 
	
		System.out.print("----------------------------------------");	
		System.out.println("\n Welcome to Ksu Cinema");
		System.out.println("----------------------------------------");	
		System.out.println("Ticket Price: VIP = 50SR , Regular = 25R");	
		System.out.println("----------------------------------------");	
		System.out.println("Please enter your booking date for your movie with this format(2020/3/12)");	
		String date= client.next();	
		outToServer.writeBytes(date +'\n');

		if(inFromServer.readLine().equalsIgnoreCase("true")) {
	////////  List all the movies 
		
			while(flag) {
		System.out.print("----------------------------------------");	
		System.out.println('\n'+"Available movies "+'\n');

		System.out.println(inFromServer.readLine());
		System.out.println("");
		System.out.println("----------------------------------------");	

		name=client.next();
		outToServer.writeBytes(name +'\n');   //// we must send the movie name to return the  available  show time//////
		if(inFromServer.readLine().equalsIgnoreCase("true"))
			flag=false;
		else {
			System.out.println("");
			System.out.println("An vaild movie name try again");
		
		}
			}
			flag=true;
		while(flag) {
		System.out.println("----------------------------------------");	
		System.out.println('\n'+"Please pick your show time with this format (6)or(3)");
		System.out.println("");
		System.out.println(inFromServer.readLine());
		System.out.println("");

		time=client.next();
		outToServer.writeBytes(time +'\n');
		if(inFromServer.readLine().equalsIgnoreCase("true"))
			flag=false;
		else {
			System.out.println("");
			System.out.println("An vaild show time try again");
		
		}

		}
		System.out.println("----------------------------------------");	
		System.out.println('\n'+"Please enter how many ticket you wnat and the class type with this format (3,vip) or (3,reg) ");
		System.out.println("");


		String information = client.next();
		information = information.concat(","+time);
		outToServer.writeBytes(information +'\n');


		
		System.out.println("----------------------------------------");	
		System.out.println("");
		System.out.println("----------------------------------------");	

		String status = inFromServer.readLine();
		if(status.equalsIgnoreCase("yes")) {
		System.out.println(" Your booking information ");
		System.out.println("");
		System.out.println(inFromServer.readLine());
		System.out.println(inFromServer.readLine());
		System.out.println(inFromServer.readLine());
		System.out.println(inFromServer.readLine());
		System.out.println(inFromServer.readLine());
		System.out.println(inFromServer.readLine());

		System.out.println("");
		
		}
		
		
		System.out.println(inFromServer.readLine());
		System.out.println("----------------------------------------");	
		System.out.println("");
		System.out.println(" serving you is our priority see you soon");
		System.out.println("--------------------------------------------------------------");	
		System.out.println("--------------------------------------------------------------");	

		}
		else {
		System.out.println("");
		System.out.println("Sorry there are no movies to display on this date");
		System.out.println("");
		System.out.println("serving you is our priority see you soon");
		System.out.println("--------------------------------------------------------------");	
		System.out.println("--------------------------------------------------------------");	
		}
	
		clientSocket.close();
		}
	}
}