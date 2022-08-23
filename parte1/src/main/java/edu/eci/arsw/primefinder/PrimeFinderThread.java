package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	
	private List<Integer> primes=new LinkedList<Integer>();
	private int quantityprimes;

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getQuantityprimes() {
		return quantityprimes;
	}

	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
		this.quantityprimes=0;

	}

	public void run(){
		for (int i=a;i<=b;i++){						
			if (isPrime(i)){
				primes.add(i);
				System.out.println(i);
			}
		}
		
		
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
		quantityprimes+=1;
	    return true;
	}


	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	
	
}
