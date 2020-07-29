/*This is the Menu Program of the Bank. The data is represented in an Abstract view over here*/
import java.util.Scanner;
public class BankMenu
{
    public static void main( String[] args)
    {
        Scanner scannerint = new Scanner(System.in);                                    //scannerint is used for the input of integral values in the Program
        Scanner scanner = new Scanner(System.in);                                       //scanner is used for the input of string in the Program
        String number = "";                                                             //empty String used for Account number
        BankStruct XDZC = new BankStruct(null,null,0,null,null,0);      //used for intializing the BankStruct class
        int option = 0;                                                                 //choice of input user wanna give
        System.out.println("    Welcome to XDZC Bank    ");
        while(option != 5)                                                              //this Displays the menu until User tells to quit
        {
            System.out.println("Avail Our Services : Please Press the Number ");
            System.out.println(" 1. Open Bank Account \n 2. See Account Details \n 3. Withdraw \n 4. Deposit \n 5. Quit");    //The Menu
            option = scannerint.nextInt();
            switch(option)
            {
                case 1:
                {
                    XDZC.OpenBankAccount();
                    break;
                }
                case 2:
                {
                    System.out.print("Enter Bank Account Number : ");
                    number = scanner.next();
                    String[] details = XDZC.getAccountDetails(number);        //it calls the getAccountDetails Method of BankStruct
                    if (details == null)
                        System.out.println("Acount Not Found");
                    else {
                        int bal = XDZC.getbalance(XDZC.scanaccount(details[4]));
                        System.out.println("Account Name : "+details[0]+"\n Account Number"+details[4]+"\n Father Name : "+details[1]+"\n Address : "+details[2]+"\n Age : "+details[3]+"\n Balane : "+bal);
                    }
                    break;
                }
                case 3:
                {
                    System.out.print("Enter Account Number ");
                    number = scanner.next();
                    int balanceindex = XDZC.scanaccount(number);       //scanning bankdata for index number of account
                    if (balanceindex == 0)
                    {
                        System.out.println("Account not Found!!!");
                    }
                    else
                    {
                        int balance = XDZC.getbalance(balanceindex);            //calling the getbalance function of BankStruct
                        if (balance<0 || balance == 0)
                        {
                            System.out.println("Unable to Withdraw, Below Minimum Balance!!!");
                        }
                        else
                        {
                            System.out.println("Enter Amount To be Withdrawn");
                            int withdraw = scannerint.nextInt();
                            if(withdraw>balance)
                            {
                                System.out.println("Insufficient Funds!!! Try Again");
                            }
                            else
                            {
                                XDZC.setbalance(withdraw,balanceindex,3);               //Updating the Balance
                            }
                        }
                    }
                    break;
                }
                case 4:
                {
                    System.out.print("Enter Account Number ");
                    number = scanner.next();
                    int balanceindex = XDZC.scanaccount(number);           //Scanning bank data for index number of account
                    if (balanceindex == 0)
                    {
                        System.out.println("Account not Found!!!");
                    }
                    else
                    {
                        System.out.println("Enter Amount To be Deposited?");
                        int depamount = scannerint.nextInt();
                        XDZC.setbalance(depamount,balanceindex,4);
                    }
                    break;
                }
                case 5:
                {
                    System.out.println("Thank You for Availing Our Services. Have a Good Day");     //exiting
                    break;
                }
                default:
                    System.out.println("Wrong input. Please Choose the Value Again!!!");
            }
        }

    }
}
