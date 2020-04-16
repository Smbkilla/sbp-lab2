package hr.fer.sbp.lab2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ZoviMontirajBezTrans {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);

		System.out.println("Upišite šifru alata");
		final int toolId = scanner.nextInt();

		scanner.nextLine();

		System.out.println("Upišite šifru stroja");
		final String machineId = scanner.nextLine().trim();

		scanner.close();
		zoviMontirajBezTrans(toolId, machineId);
	}

	public static void zoviMontirajBezTrans(final int toolId, final String machineId) {
		final Connection connection = openConnection();

		try {
			final CallableStatement cstmt = connection.prepareCall("{call dbo.montirajBezTrans(?, ?)}");

			cstmt.setInt(1, toolId);
			cstmt.setString(2, machineId);

			try {
				cstmt.executeQuery();
			} catch (SQLException exception) {
				if (exception.getErrorCode() == 0) {
					System.out.println("Alat je montiran");
				} else {
					printError(exception);
					System.exit(-1);
				}
			} finally {
				cstmt.close();
				connection.close();
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
