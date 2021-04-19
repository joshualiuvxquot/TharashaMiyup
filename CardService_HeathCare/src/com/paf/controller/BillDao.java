package com.paf.controller;
//package bill_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.paf.model.CardClass;
import com.paf.util.DBConnection;

public class BillDao {

	
//--------------------insert card---------------------------------------------------------
	public String insertCard(String cusNo,String caNo,String caName,String caType)
 {

		String output = "";

		
		try {
			// Connection con = connect();
			Connection con = DBConnection.getConnection();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into cardtable (`id`,`customerID`,`cardNo`,`cardName`,`cardType`) values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(cusNo));
			preparedStmt.setInt(3, Integer.parseInt(caNo));
			preparedStmt.setString(4, caName);
			preparedStmt.setString(5, caType);
			
			System.out.println(cusNo);
			System.out.println(caNo);
			System.out.println(caName);
			System.out.println( caType);
			
			preparedStmt.execute();
			con.close();


			String newCards = readcardDetail();
			output = "{\"status\":\"success\", \"data\": \"" + newCards + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting your card detail.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

//--------------------------------UPDATE  card-----------------------------------------

	public String updateCard(String id, String customerID, String cardNo, String cardName, String cardType) {
		String output = "";

		try {
			// Connection con = connect();
			Connection con = DBConnection.getConnection();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE cardtable SET customerID=?,cardNo=?,cardName=?,cardType=?      WHERE id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setInt(1, Integer.parseInt(customerID));
			preparedStmt.setInt(2, Integer.parseInt(cardNo));
			preparedStmt.setString(3, cardName);
			preparedStmt.setString(4, cardType);
			preparedStmt.setInt(5, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newCards = readcardDetail();
			output = "{\"status\":\"success\", \"data\": \"" + newCards + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}


		return output;
	}

	// ----------DELETE card-------------------------------------

	public String deleteCard(String id) {
		String output = "";

		try {
			// Connection con = connect();
			Connection con = DBConnection.getConnection();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from cardtable where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			// execute the statement
						preparedStmt.execute();
						con.close();

						String newCards = readcardDetail();
						output = "{\"status\":\"success\", \"data\": \"" + newCards + "\"}";
					} catch (Exception e) {
						output = "{\"status\":\"error\", \"data\": \"Error while deleting the card.\"}";
						System.err.println(e.getMessage());
					}

		return output;
	}

	// --------------READ card---------------------------------------------------

	public String readcardDetail() {
		String output = "";

		try {
			// Connection con = connect();
			Connection con = DBConnection.getConnection();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
						output = "<table border='1'>"
								+ "<tr>"
								+ "<th>Customer ID </th> "
								+ "<th>Card number</th>"
								+ "<th>Card Name</th>"
								+ "<th>Bank Type</th> "
								+ "<th>Update</th>"
								+ "<th>Remove</th>"
								+ "</tr>";

			String query = "select * from cardtable";
			
			PreparedStatement sts = con.prepareStatement(query);

			ResultSet rss = sts.executeQuery();

			while (rss.next()) {

			

				String id = Integer.toString(rss.getInt("id"));
				String cid = Integer.toString(rss.getInt("customerID"));
				String cardNo = Integer.toString(rss.getInt("cardNo"));
				String cardName = rss.getString("cardName");
				String cardType = rss.getString("cardType");

		

				System.out.println("card name : " + cardName);


				// Add into the html table
				output += "<tr><td><input id='hidCardIDUpdate' name='hidCardIDUpdate' type='hidden' value='" + id + "'>" + cid + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cardName+ "</td>";
				output += "<td>" + cardType  + "</td>";

				// buttons
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-cardid='"	+ id + "'>" + "</td>"
						+ "</tr>";
			}

			con.close();

			// Complete the html table
			output += "</table><br>";
		} catch (Exception e) {
			output = "Error while reading the cards.";
			System.err.println(e.getMessage());

		}

		return output;

	}

	
}