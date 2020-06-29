
public class test {

	public static void main(String[] args) {

		Movie B = new Movie("B", 6,"2020/10/23");
		Movie A = new Movie("A", 1,"2020/10/23");
		Movie C = new Movie("C", 9,"2020/10/23");


		theater sixPm = new theater(6,6);
		theater onePm = new theater(1,1);
		theater ninePm = new theater(9,9);
		


		sixPm.insert(B);
		sixPm.insert(A);
		sixPm.insert(C);

		onePm.insert(A);
		onePm.insert(B);
		onePm.insert(C);

		ninePm.insert(A);
		ninePm.insert(C);
		ninePm.insert(B);

		
		KsuCinema ksu = new KsuCinema();
		
		ksu.insert(sixPm.theaterNum, sixPm);
		ksu.insert(onePm.theaterNum, onePm);
		ksu.insert(ninePm.theaterNum, ninePm);

		
		
//		ksu.print(ksu.root);	
		

		
		

		
		
		
		
		
	}

}
