package hr.fer.sbp.lab2;

import java.sql.*;
import java.util.Scanner;

public class ZoviMontirajUzTrans {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);

		System.out.println("Upišite šifru alata");
		final int toolId = scanner.nextInt();

		scanner.nextLine();

		System.out.println("Upišite šifru stroja");
		final String machineId = scanner.nextLine().trim();

		scanner.close();

		zoviMontirajUzTrans(toolId, machineId);
	}

	public static void zoviMontirajUzTrans(final int toolId, final String machineId) {
		final Connection connection = openConnection();

		try {
			final CallableStatement cstmt = connection.prepareCall("{call dbo.montirajUzTrans(?, ?)}");

			connection.setAutoCommit(false);

			cstmt.setInt(1, toolId);
			cstmt.setString(2, machineId);

			try {
				cstmt.execute();

				cstmt.getMoreResults();
				cstmt.getMoreResults();
				cstmt.getMoreResults();
				cstmt.getMoreResults();
				cstmt.getMoreResults();

				System.out.println("Alat je montiran");
				cstmt.close();
				connection.close();
			} catch (SQLException exception) {
				printError(exception);
				System.exit(-1);
			}
		} catch (SQLException exception) {
			printError(exception);
		}
	}

	private static void printError(SQLException exception) {
		System.out.println(exception.getErrorCode() + "; "
				+ exception.getMessage() + "; "
				+ "State=" + exception.getSQLState());
	}


	private static Connection openConnection() {
		String url =
				"jdbc:sqlserver://localhost:3467;"
						+ "databaseName=labprof6;"
						+ "user=sa;"
						+ "password=passzabazu;";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("SQL Server JDBC driver je u�itan i registriran.");
		} catch (ClassNotFoundException exception) {
			System.out.println("Pogre�ka: nije uspjelo u�itavanje JDBC driver-a.");
			System.out.println(exception.getMessage());
			System.exit(-1);
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Konekcija je uspostavljena.");
		} catch (SQLException exception) {
			System.out.println("Pogre�ka: nije uspjelo uspostavljanje konekcije.");
			System.out.println(exception.getErrorCode() + " " + exception.getMessage());
			System.exit(-1);
		}
		return conn;
	}
}
