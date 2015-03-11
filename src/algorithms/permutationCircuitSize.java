package algorithms;

/**
 * Calculates the number of components need to create a permutation circuit.
*/

public class permutationCircuitSize{
	public static int n = 8;
	public static void main(String[] args) {
		System.out.println(getCircuitSize(n));
		System.out.println(getDepth(n));
	}
	public static int getCircuitSize(int p){
		if(p == 2){
			return 1;
		}
		return (2 * getCircuitSize(p / 2) + p - 1);
	}
	public static int getDepth(int p){
		if(p == 2){
			return 1;
		}
		return (getDepth(n/2) + 2);
	}
}
