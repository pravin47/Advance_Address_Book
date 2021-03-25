package com.advance.address.book;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookData {

	static Scanner sc = new Scanner(System.in);
	static boolean exit = false;
	static Connection conneection;
	static Statement statement;
	static String jdbcUrl = "jdbc:mysql://localhost:3306/addressbook?useSSL=false";
	static String username = "root";
	static String password = "123456";

	static List<Book> bookStore = new ArrayList<>();

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			System.out.println("Connecting to database " + jdbcUrl);
			conneection = DriverManager.getConnection(jdbcUrl, username, password);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return conneection;
	}

	public static List<Book> display() throws SQLException {

		try {

			conneection = getConnection();
			statement = conneection.createStatement();
			ResultSet result = statement.executeQuery("select * from book");
			while (result.next()) {
				Book b = new Book();
				b.setId(result.getInt(1));
				b.setFirstname(result.getString(2));
				b.setLastname(result.getString(3));
				b.setTypename(result.getString(4));
				b.setState(result.getString(5));
				b.setCity(result.getString(6));
				b.setZip_code(result.getString(7));
				b.setPhone_no(result.getString(8));
				b.setEmail(result.getString(9));

				bookStore.add(b);

				Path path = Paths.get("D://pravin.csv");

				try {

					FileWriter outputfile = new FileWriter(path.toFile(), true);
					CSVWriter writer = new CSVWriter(outputfile);
					ResultSetMetaData mdata = result.getMetaData();
					mdata.getColumnName(1);

					String line[] = { mdata.getColumnName(1), mdata.getColumnName(2), mdata.getColumnName(3),
							mdata.getColumnName(4), mdata.getColumnName(5), mdata.getColumnName(6),
							mdata.getColumnName(7), mdata.getColumnName(8), mdata.getColumnName(9) };

					writer.writeNext(line);

					String[] data = new String[9];

					while (result.next()) {
						data[0] = new Integer(result.getInt("id")).toString();
						data[1] = result.getString("first_name");
						data[2] = result.getString("last_name");
						data[3] = result.getString("type_name");
						data[4] = result.getString("state");
						data[5] = result.getString("city");
						data[6] = result.getString("zip_code");
						data[7] = result.getString("phone_no");
						data[8] = result.getString("email");
						writer.writeNext(data);

					}

					writer.flush();
					System.out.println("data entered in csv file");

				}

				catch (IOException e) {

					e.printStackTrace();
				}

			}
			conneection.close();
			System.out.println("your address book represents");

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return bookStore;

	}

	public static int insertValues() throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement statement1;
		int i = 0;

		try {
			System.out.println("inserting into the table ");
			conneection = DriverManager.getConnection(jdbcUrl, username, password);
			statement1 = conneection.prepareStatement(
					"insert into book(first_name,last_name,type_name,state,city,zip_code,phone_no,email)"
							+ "              value(?,?,?,?,?,?,?,?)");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do {

				System.out.println("enter the first_name");
				String first_name = br.readLine();
				System.out.println("enter the last_name ");
				String last_name = br.readLine();
				System.out.println("enter the type_name ");
				String type_name = br.readLine();
				System.out.println("enter the state");
				String state = br.readLine();
				System.out.println("enter the city");
				String city = br.readLine();
				System.out.println("enter zip_code");
				String zip_code = br.readLine();
				System.out.println("enter phone number ");
				String phone_no = br.readLine();
				System.out.println("enter email ");
				String email = br.readLine();

				statement1.setString(1, first_name);
				statement1.setString(2, last_name);
				statement1.setString(3, type_name);
				statement1.setString(4, state);
				statement1.setString(5, city);
				statement1.setString(6, zip_code);
				statement1.setString(7, phone_no);
				statement1.setString(8, email);

				i = statement1.executeUpdate();

				System.out.println();
				System.out.println("inserted sucessfully " + i + "rows affected\n");

				System.out.println("do you want to continue Y[yes]/N[no]");

				String option = br.readLine();
				if (option.startsWith("N")) {
					break;
				}

			} while (true);

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		conneection.close();
		return i;
	}

	public static void DeleteContent() throws SQLException, NumberFormatException, IOException {
		PreparedStatement statement1;

		try {
			conneection = getConnection();
			System.out.println("Alteration  of Table");

			statement1 = conneection.prepareStatement("DELETE from book where first_name=?");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("enter the first name");

			String first_name = br.readLine();

			statement1.setString(1, first_name);
			int i = statement1.executeUpdate();

			if (i > 0) {
				System.out.println(i + " contains deleted  from table");

			}

			else {
				System.out.println("the content is already not presnt in table");

			}
			conneection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateValues() throws SQLException, IOException {
		PreparedStatement statement1 = null;

		try {
			conneection = getConnection();
			System.out.println("updating in table");
			String sql = " UPDATE book set zip_code=? where first_name=?";
			statement1 = conneection.prepareStatement(sql);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("enter the zip_code");
			String zip_code = br.readLine();
			System.out.println("enter the first name");
			String first_name = br.readLine();

			statement1.setString(1, zip_code);
			statement1.setString(2, first_name);
			int i = statement1.executeUpdate();

			if (i > 0) {
				System.out.println();
				System.out.println(i + " updated successfully");

			} else {
				System.out.println("given data is incorrect");
			}

			conneection.close();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

	}

}