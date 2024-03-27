class Solution {
    public int solution(int n, int count) {
        long[][] arr = new long[n + 1][n + 1];
		long preventOutOfRange = 1000000007;
		long temp = 0;
		
		arr[1][1] = 1;
		
		for (int x = 2; x <= n; x++) {
			
			for (int y = 1; y <= x; y++) {
				
				if (y == 1) {
					temp = arr[x - 1][y] * (2 * (x - 1));
				} else if (y > 1 && y < x) { 
					temp = arr[x - 1][y] * (2 * (x - 1)) + arr[x - 1][y - 1];
				} else { 
					temp = arr[x - 1][y - 1];
				}
				
				arr[x][y] = temp % preventOutOfRange;
			}
		}
		
		return (int) arr[n][count];
    }
}