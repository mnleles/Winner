package com.megabot.winner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class MegaTest {

	public static void main(final String[] args) throws IOException {
		String file = "D:/Marcos/Desktop/mega.txt";
		FileUtils.readLines(new File(file), Charset.defaultCharset()).stream()
				.map(l -> Arrays.asList(l.split(" ")).stream().sorted().collect(Collectors.toList()))
				.forEach(System.out::println);
	}

}
