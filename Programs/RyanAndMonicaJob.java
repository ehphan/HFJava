public class RyanAndMonicaJob implements Runnable {

	private BankAccount account = new BankAccount();
	//There will be only one instance of the RyanAndMonicaJob. That means only ONE instance of the bank account. Both threads will access this one account.

	public static void main (String[] args) {
		RyanAndMonicaJob theJob = new RyanAndMonicaJob();
		//Instantiate the Runnable(job)
		Thread one = new Thread(theJob);
		Thread two = new Thread(theJob);
		//Make two threads, giving each thread the same Runnable job. That means both threads will be accessing the one account IV in the Runnable class.

		one.setName("Ryan");
		two.setName("Monica");
		one.start();
		two.start();
	}

	public void run() {
		for (int x = 0; x < 10; x++) {
			makeWithdrawl(10);
			if (account.getBalance() < 0) {
				System.out.println("Overdrawn");
			}
		}
	}
	//In the run() method, a thread loops through and tries to make a withdrawal with each iteration. After the withdrawal, it checks the balance once again to see if the account is overdrawn.

}