package com.megabot.winner.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations
{
	/**
	 * Sample: String[] arr = { "1", "2", "3", "4", "5" }; generate(arr, 3).stream().map(a ->
	 * Arrays.toString(a)).forEach(System.out::println);
	 *
	 * Output: [1, 2, 3] [1, 2, 4] [1, 2, 5] [1, 3, 4] [1, 3, 5] [1, 4, 5] [2, 3, 4] [2, 3, 5] [2, 4, 5] [3, 4, 5]
	 *
	 * @param input
	 *            All input values
	 * @param n
	 *            Amount elements per group
	 * @return
	 */
	public static List<String[]> generate(final String[] input, final int n)
	{
		List<String[]> allResults = new ArrayList<>();
		generateCombinations(input, n, 0, new String[n], allResults);
		return allResults;
	}

	private static void generateCombinations(final String[] arr, final int len, final int startPosition, final String[] result, final List<String[]> allResults)
	{
		if (len == 0)
		{
			allResults.add(Arrays.copyOf(result, result.length));
			return;
		}
		for (int i = startPosition; i <= arr.length - len; i++)
		{
			result[result.length - len] = arr[i];
			generateCombinations(arr, len - 1, i + 1, result, allResults);
		}
	}

}
