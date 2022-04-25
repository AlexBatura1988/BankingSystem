package useCases;

import java.util.Scanner;

import Db.Constants;

import static Db.Constants.*;

public class StartScreen {
	 public static int show() {
	        while (true) {
	            System.out.println("1. login(username, password)");
	            System.out.println("2. login(phone Number)");
	            System.out.println("3. Open Account");
	            Scanner scanner = new Scanner(System.in);
	            String s = scanner.nextLine();
	            if (s.equals("1")) {
	                return LOGIN_NAME;
	            } else if (s.equals("2")) {
	                return LOGIN_PHONE;
	            } else if (s.equals("3")) {
	                return CREATE_ACC;
	            } else {
	                System.out.println("wrong input");
	            }
	        }
	    }

}
