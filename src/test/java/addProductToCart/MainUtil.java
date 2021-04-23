package addProductToCart;

import org.openqa.selenium.WebDriver;

public class MainUtil {
	public static WebDriver driver;

	public synchronized static void compareRiceQuantity(String actual, String expected) {
        try {
     if (actual.replaceAll("\\s+", "").equalsIgnoreCase(expected.replaceAll("\\s+", ""))) {
              
            	System.out.println("comparision is successful - actual : expected -> " + actual + " : " + expected);
            } else {
             
            	System.out.println("comparision is Unsuccessful - actual : expected -> " + actual + " : " + expected);
            }
        } catch (Exception e) {
         
        }
    }
	
	public synchronized static void compareCartQuantity(String actual, String expected) {
        try {
     if (actual.replaceAll("\\s+", "").equalsIgnoreCase(expected.replaceAll("\\s+", ""))) {
              
            	System.out.println("comparision is successful - actual : expected -> " + actual + " : " + expected);
            } else {
             
            	System.out.println("comparision is Unsuccessful - actual : expected -> " + actual + " : " + expected);
            }
        } catch (Exception e) {
         
        }
    }
	

}
