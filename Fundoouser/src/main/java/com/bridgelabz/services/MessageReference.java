/******************************************************************************
 *  Compilation:  javac -d bin Response.java
 *  Execution:    
 *               
 *  
 *  Purpose:     create message reference for user understanding
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.services;

public class MessageReference {
	
	//   create message reference for user understanding
	
	public static final String EMAIL_ALREADY_REGISTERED = "Email id already Registered";
	public static final String EMAIL_CHECK="Email is not found";
	public static final String REGISTRATION_MAIL_TEXT ="\t validate your email\n"+"http://localhost:8080/validate?token=";
	public static final String Verfiy_MAIL_TEXT ="\t Verficiaton email \n"+"http://localhost:8080/verfiy?token=";
    public static final String EMAIL_FAIL="Invalid username ";
    public static final String NOT_ACTIVE="Kindly active your account link send your email id";
    public static final String USER_ADD_SUCCESSFULLY="new User Registered Successfully ";
    public static final String NOT_VERFIY_EMAIL="kindly verfiy email your account";
    public static final String EMAIL_VERFIY="User Email verfiy successfully";
    public static final String LOGIN_SUCCESSFULLY="Login successfully";
    public static final String LOGIN_NOT_SUCCESSFULLY="Login failed";
    public static final String USER_DELETE_SUCCESSFULLY="User delete Succssfully";
    public static final String USER_UPDATE_SUCCESSFULLY="User update Succssfully";
    public static final String USER_ID_NOT_FOUND="Person could not be found with id ";
    public static final String USER_NOT_EXISTING=" user not existing ";
    public static final String PASSWORD_CHANGE_SUCCESSFULLY="password Change  successfully";
    public static final String PASSWORD_NOT_CHANGE_SUCCESSFULLY="password not Change  successfully";
    public static final String MAIL_SEND=" Mail send ";
    public static final String PASSWORD_IS_NOT_MATCHING =" Both password are not matching";
    public static final String 	INVALID_TOKEN=" invalid token ";
   

}
 