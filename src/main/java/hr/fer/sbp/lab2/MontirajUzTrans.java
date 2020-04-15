package hr.fer.sbp.lab2;

import java.sql.*;
import java.util.Scanner;

public class MontirajUzTrans {

	public static void main(String[] args) {
		final Connection connection = openConnection();
		final Scanner scanner = new Scanner(System.in);

		System.out.println("Upišite šifru alata");
		final int toolId = scanner.nextInt();

		scanner.nextLine();

		System.out.println("Upišite šifru stroja");
		final String machineId = scanner.nextLine().trim();

		scanner.close();


		try {
			connection.setAutoCommit(false);

			final PreparedStatement insertMontazaStatement = connection.prepareCall("INSERT INTO montaza VALUES (?, ?)");


			insertMontazaStatement.setInt(1, toolId);
			insertMontazaStatement.setString(2, machineId);

			try {
				insertMontazaStatement.execute();
			}
			catch (SQLException exception) {
				if (exception.getErrorCode() == 2627) {
					System.out.println("Alat je zauzet");
				} else {
					printError(exception);
				}
				connection.rollback();
				System.exit(-1);
			}

			final PreparedStatement updateAlatStatement = connection.prepareCall("UPDATE alat SET montiran = 'D' WHERE sifAlat = ?");
			updateAlatStatement.setInt(1, toolId);
			updateAlatStatement.execute();

			final PreparedStatement updateStrojStatement = connection.prepareCall("UPDATE stroj SET brojMontiranih = brojMontiranih + 1 WHERE oznStroj = ?");
			updateStrojStatement.setString(1, machineId);

			try {
				updateStrojStatement.execute();
			}
			catch (SQLException exception) {
				if (exception.getErrorCode() == 547) {
					System.out.println("Kapacitet stroja je popunjen");
				} else {
					printError(exception);
				}
				connection.rollback();
				System.exit(-1);
			}

			connection.commit();
			System.out.println("Alat je montiran");

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
