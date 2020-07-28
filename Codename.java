import java.util.Scanner;
public class agent {
    public static void main(String[] args)
    {
        Scanner key = new Scanner(System.in);
        String codename = "0", confirm = "0";
        System.out.println("Enter Codename(minimum 6 char,starts with agent,ends with 'x')");
        /* startswith() and endswith() reutrns Boolean values if matches then true else false */
        while (codename.length() < 6 || !codename.startsWith("agent") || !codename.endsWith("X"))
        {
            codename = key.next(); /*it won't take a space in between */
        }
        /*checks for the codename if equals, the while become false and exits and if
         not equals, the while becomes true and asks for the codename again*/
        while (!codename.equals(confirm))
        {
            System.out.println("Please Re-enter Your Codename");
            confirm = key.next();
            if (codename.equals(confirm))
            {
                System.out.println("Codename Successfully Registered");
                /* after here the while becomes false and exits */
            }
        }
    }
}
