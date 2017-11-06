package dao;
import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class AuthDAO {
	
	
	public static int checkUserPass(String userName, String password ,String role){
		//if match return userId else return -1
		Connection conn = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("driver is not working");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			System.out.println("can not connect to database");
		}
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {
				String d_name = rs.getString("name");
				String d_password = rs.getString("password");
				String d_role = rs.getString("role");
				int d_id=rs.getInt("id");
				if(userName.equals(d_name)&&password.equals(d_password)&&role.equals(d_role)){
					return d_id;
				}
				//System.out.println(d_name + " " + d_password+" "+d_role);
			}
		} catch (SQLException e) {
			System.out.println("can not search in the database");
		}
		
		return -1;
		}

	public static User getUserById(int userId){
		//return a User Object (with all attributes set) of user with Id == userId
		String d_name=null;
		String d_password=null;
		String d_role=null;
		int d_id=0;
		String d_firstName=null;
		String d_lastName=null;
		int d_id2=0;
		Connection conn = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("driver is not working");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			System.out.println("can not connect to database");
		}
		try {
			//enterNewUser("1","1","customer","hh","jj");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {
				d_name = rs.getString("name");
				d_password = rs.getString("password");
				d_role = rs.getString("role");
				d_id=rs.getInt("id");
				if(d_id==userId){
					try{
						Statement stmt2 = conn.createStatement();
						ResultSet rs2 = stmt2.executeQuery("select * from user_profile");
						while (rs2.next()){
							d_firstName = rs2.getString("firstName");
							d_lastName = rs2.getString("lastName");
							d_id2=rs2.getInt("userid");
							if(d_id2==userId){
								break;
							}
						}	
					}catch (SQLException e) {
						System.out.println("can not search");
					}
					break;
				}
				
			}
		} catch (SQLException e) {
			System.out.println("can not search");
		}
		
		
		
		User user=new User(d_id,d_name,d_password ,d_role, d_firstName,d_lastName);
		return user;
		}

	public static boolean enterNewUser(String userName, String password ,String role, String firstName, String lastName){
		//Enters new user in DB. This includes entries in both User and UserProfile table of Database
		//if entry successful return true else return false
		Connection conn = null;
		String sql;
		int result;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("driver is not working");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			System.out.println("can not connect to database");
		}
		try {
	/*
			sql = "insert into user(name,password,role,id) values('?','?','?','?')";
			PreparedStatement preStmt=conn.prepareStatement(sql);
			preStmt.setString(1,userName);
			preStmt.setString(2,password);
			preStmt.setString(3,role);
			preStmt.setInt(4, 1);
			result = preStmt.executeUpdate(sql);
	*/
			int id=1;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()){
				id++;
			}
			
			sql="insert into user(name,password,role,id) values('"+userName+"','"+password+"','"+role+"','"+id+"')";
			result = stmt.executeUpdate(sql);
			sql="insert into user_profile(userid,firstName,lastName) values('"+id+"','"+firstName+"','"+lastName+"')";
			result = stmt.executeUpdate(sql);
			if(result!=1){
	        	return false;
	        }
			
		} catch (SQLException e) {
			System.out.println("can not insert in the database");
		}
		
		return true;
		}

	public static boolean checkUserNameAvailable(String userName){
		//if available return true else return false
		Connection conn = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("driver is not working");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			System.out.println("can not connect to database");
		}
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {
				String d_name = rs.getString("name");
				if(userName.equals(d_name)) return false;
			}
		} catch (SQLException e) {
			System.out.println("can not search");
		}
		return true;
		}
/*
	public static void main(String[] args) {
		
		
		Connection conn = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("driver is not working");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			System.out.println("can not connect to database");
		}
		try {
			//enterNewUser("1","1","customer","hh","jj");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {
				String x = rs.getString("name");
				String y = rs.getString("password");
				String z = rs.getString("role");
				int a=rs.getInt("id");
				System.out.println(a+" "+x + " " + y+" "+z);
			}
		} catch (SQLException e) {
			System.out.println("can not search");
		}
		
		User user1=new User();
		user1=getUserById(1);
		user1.printAll();
		
	}
	*/
}
