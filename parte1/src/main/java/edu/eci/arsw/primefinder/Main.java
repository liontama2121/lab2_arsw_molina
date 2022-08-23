package edu.eci.arsw.primefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) {
		
		PrimeFinderThread Thread1=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread Thread2=new PrimeFinderThread(10000000, 20000000);
		PrimeFinderThread Thread3=new PrimeFinderThread(20000000, 30000000);
		Thread1.start();
		Thread2.start();
		Thread3.start();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					int amount = Thread1.getQuantityprimes() +Thread2.getQuantityprimes()+Thread3.getQuantityprimes();
					System.out.println(amount+" prime numbers have been found");
					Thread1.suspend();
					Thread2.suspend();
					Thread3.suspend();

					while (bufferedReader.read() != '\n'){
						bufferedReader.read();
					}
					Thread1.resume();
					Thread2.resume();
					Thread3.resume();


				}
				catch (IOException e){
					e.printStackTrace();
				}

			}
		},5000);

	}
	
}
