package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Products;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Products> items = new ArrayList<>();

		System.out.println("Enter the file path: ");
		String path = sc.nextLine();
		// "C:\\temp\\ws-eclipse\\in\\source.csv";

		File sourceFile = new File(path);
		String sourceFolder = sourceFile.getParent();

		boolean sucess = new File(sourceFolder + "\\out").mkdir();

		String targetFile = sourceFolder + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				String array[] = line.split(",");
				items.add(new Products(array[0], Double.parseDouble(array[1]), Integer.parseInt(array[2])));
				line = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {

				for (Products item : items) {
					bw.write(item.getName() + "," + String.format("%.2f", item.calcPrice()));
					bw.newLine();
				}

				System.out.println("File created!");

			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
