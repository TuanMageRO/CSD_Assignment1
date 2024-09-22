
package Validation;

import java.util.Scanner;
import Implementation.linkedList;
import Object.Node;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class checkInput {
    private final Scanner sc = new Scanner(System.in);
    private final String nameFormat = "[A-Za-z\\s]+";
    private final String idFormat = "[A-Z0-9]+";
    
    
    public int getValidInteger(String mess, int min, int max) {
        int n;
        String err = "Invalid input. Program only permits Integer in range " + min + " to " + max;
        while(true) {
            try{
                System.out.println(mess);
                n = Integer.parseInt(sc.nextLine());
                if(n < min || n > max) {
                    System.err.println(err);
                    continue;
                }
                return n;
            }
            catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }
    
    public String getValidName (String mess) {
        String name;
        String err = "Invalid input. Program only permits letters and space for Train-Name/ID/Place.";
        while(true) {
            System.out.println(mess);
            name = sc.nextLine().trim();
            if(name.matches(nameFormat)) {
                return name;
            }
            else {
                System.err.println(err);
            }
        }
    }
    
    public boolean checkExistID (String id, linkedList trainList) {
        Node current = trainList.head;
        while(current != null) {
            if(current.data.getTCode().equals(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public String getValidID (String mess, linkedList trainList) {
        String id;
        String err = "Invalid input. Program only permits uppercase letters and digits for ID.";
        while(true) {
            System.out.println(mess);
            id = sc.nextLine().trim();
            if(id.matches(idFormat)) {
                if(!checkExistID(id, trainList)) {
                    return id;
                }
                else 
                    System.err.println("The Train Code is used (uniqe).");
            }
            else {
                System.err.println(err);
            }
        }
    }
    
    public String getValidTime (String mess) {
        String time;
        while(true) {
            try{
                System.out.println(mess);
                time = sc.nextLine().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = sdf.parse(time);
                if(time.equals(sdf.format(d))) {
                    return time;
                }
                else {
                    System.err.println("Wrong Time input format (Year-Month-Day HH:mm:ss");
                }
            }
            catch (ParseException e) {
                System.err.println("Invalid Day or Time (Month, day, hour, minute and seconds must cosist of 2 digits.)");
            }
        }
    }
}
