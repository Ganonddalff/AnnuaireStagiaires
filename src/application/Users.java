package application;

public class Users {

	private String userName;
	private String pswd;
	private Boolean admin;
	public Users[]user;
	final public static int nbUsers = 3; 
	public Boolean userCurrent;
	
	

	public Boolean getUserCurrent() {
		return userCurrent;
	}
	public void setUserCurrent(Boolean userCurrent) {
		this.userCurrent = userCurrent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	//	public void Users[](Users usr) {
	//		
	//	}


	public Users[] getUser() {
		return user;
	}
	public void setUser(Users[] user) {
		this.user = user;
	}
	public static int getNbusers() {
		return nbUsers;
	}
	public  Users(String userName,String pswd,Boolean admin) {
		this.userName = userName;
		this.pswd=pswd;
		this.admin=admin;
	}

	public  Users(String userName,String pswd) {
		this.userName = userName;
		this.pswd=pswd;
	}

	public Boolean compareUsers(Users user) {
		if(this.userName.trim().equals(user.getUserName().trim())) {
			if(this.pswd.trim().equals(user.getPswd().trim())) {
				
				return true;
			}	
			else {
				System.out.println("Nom saisi : "+this.userName+"\n"
						+"pswd : "+this.pswd+
						"\n"+user.getUserName()+"\n"+user.getPswd());
				return false;
			}
		}
		else {
			System.out.println("Nom saisi : "+this.userName+"\n"
					+"pswd : "+this.pswd+
					"\n"+user.getUserName()+"\n"+user.getPswd());
			return false;
		}
	}

	//	public Boolean compareUserToUserList(Users userCo, Users user[]) {
	//		int userIndex=0;
	//		while(userIndex<nbUsers) {
	//			userCo.compareUsers(userCo, user);
	//		}
	//		
	//	}


}
