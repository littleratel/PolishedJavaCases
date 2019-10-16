package cn.intv.sort;

public class PkgAnswer {
	/**
	 * 01背包问题
	 * 每件物品重量weight, 价值price, pkgTotalWeight包能放下物品总重量；
	 * 求能放如的物品的总价值最大。
	 * 
	 * w[i]: 第i个物体的重量；
	 * p[i]: 第i个物体的价值；
	 * c[i][m]: 前i个物体放入容量为m的背包的最大价值；
	 * c[i-1][m]: 前i-1个物体放入容量为m的背包的最大价值；
	 * c[i-1][m-w[i]]: 前i-1个物体放入容量为m-w[i]的背包的最大价值；
	 * 
	 * 状态转移方程:
	 * c[i][m]=max{c[i-1][m-w[i]]+pi , c[i-1][m]}
	 * 
	 * 1)不放第i件物品，所以c[i][j] = c[i-1][j]的值
	 * 2)放第i件物品，所以c[i][j] = c[i-1][j-w[i-1]] + p[i-1]
	 * */
	public static void main(String[] args) {
		int[] weight = { 0, 6, 1, 5, 2, 1 };
		int[] price= { 0, 48, 7, 40, 12, 8 };
		int wLength = weight.length;
		int pkgTotalWeight = 11;
		int arr[][] = new int[wLength][pkgTotalWeight+1];

		for (int i = 1; i < wLength; i++) {
			for (int j = 1; j <= pkgTotalWeight; j++) {
				if (j - weight[i] < 0)
					arr[i][j] = arr[i - 1][j];
				else
					arr[i][j] = Math.max(arr[i - 1][j], arr[i - 1][j - weight[i]] + price[i]);
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(arr[wLength - 1][pkgTotalWeight]);
	}
	
}
