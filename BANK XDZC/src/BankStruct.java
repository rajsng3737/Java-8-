import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.io.RandomAccessFile;
public class BankStruct
{
    /*BankStruct constructor accepts 6 values , Account information is stored in bankdata.txt and balance is
    stored in balance.txt
     */
    public BankStruct(String accountNumber,String accountName,int bal,String fathern,String address,int age)
    {
        if(accountNumber!=null)
        {
            try
            {
                FileWriter account = new FileWriter("bankdata.txt",true);
                FileWriter balance = new FileWriter("balance.txt",true);
                PrintWriter accountwriter = new PrintWriter(account);
                PrintWriter balancewriter = new PrintWriter(balance);
                accountwriter.println(accountName+":"+fathern+":"+address+":"+age+":"+accountNumber);
                balancewriter.println(bal);
                accountwriter.close();
                balancewriter.close();
            }
            catch(IOException ie){
                System.out.println("Something Went Wrong");
            }

        }
    }
    /*scanaccount method tells us the index of a particular account in a bankdata.txt */
    public int scanaccount(String acnum)
    {
        String search;
        int count = 1;
        try {
            FileReader account = new FileReader("bankdata.txt");
            BufferedReader accountreader = new BufferedReader(account);
            int endindex = eof();
            while (endindex>count)                  /* Searches till the account is not found */
                {
                search = accountreader.readLine();  /*compares for every readline() ending with the provided argument or not */
                if (search.endsWith(acnum)) {
                    return count;
                }
                count += 1;
            }
            accountreader.close();
        } catch (IOException ie) {
            System.out.println("Something Went Wrong");
        }
        return 0;
    }
    /*This function tells us the index of the last account in the file*/
    public int eof()
    {
        int count=1;
        try
        {
            FileReader account = new FileReader("bankdata.txt");
            BufferedReader accountreader = new BufferedReader(account);
            while (accountreader.readLine() != null)            /*iterates till the readline doesn't give null which is at the end of the file*/
            {
                count += 1;                                     /*Index Increases*/
            }
            accountreader.close();
        }
        catch(IOException ie)
        {
            System.out.println("Something Went Wrong");
        }
        return count;
    }
    public boolean stringcheck(String[] check)              /*checks the string for empty or not*/
    {
        for (String s : check) {
            if (s != null) {
                return true;
            }
        }
        return false;
    }
    /*This method Displays the Details of a Given Account Number*/
    public String[] getAccountDetails(String acnum)
    {
        try
        {
            FileReader file = new FileReader("bankdata.txt");                                           //Reading File Bankdata
            RandomAccessFile position = new RandomAccessFile("balance.txt","r");                    //Reading file balance
            BufferedReader reading = new BufferedReader(file);
            String[] searching={"testing"};
            int index=scanaccount(acnum);                                                                       //get the index of the account number given
            position.seek((index-1)*4);                                                                    //moving to the index
            while(stringcheck(searching))
            {
                searching = reading.readLine().split(":");
                if(searching[4].equals(acnum))                                                                  //comparing the account number given and Stored in Database
                {
                    reading.close();
                    position.close();
                    return searching;                                                                           //passing the string for displaying the results
                }
            }
        }
        catch(IOException ie)
            {
                System.out.println("Something Went Wrong");
            }
        return null;
    }
    /*This Function Reads and returns the balance of a given account number*/
    public int getbalance(int index)
    {
        int num=1;
        try
        {
            FileReader balance = new FileReader("Balance.txt");
            BufferedReader balancereader = new BufferedReader(balance);
            while(num != index)                                                                          //itereatin to the target account
            {
                balancereader.readLine();                                                               //parsing through the file
                num+=1;
            }
            int bal = Integer.parseInt(balancereader.readLine());                                       //reading the balance
            balancereader.close();
            return bal;
        }
        catch(IOException ie)
        {
            System.out.println("Something Went Wrong");
        }
        return 0;
    }
    /*This Function Updates the balance of a particular account in the Database
    * the arguments passed are amount to be deposit or withdrawn and index of the account and option 3 for withdrawn and 4 for deposit*/
    public void setbalance(int amount,int index,int option)
    {
        try {
            int count=1;
            RandomAccessFile modifyposition = new RandomAccessFile("balance.txt", "r");
            BufferedReader recoverbal = new BufferedReader(new FileReader("balance.txt"));
                if (option == 3)    /*this is for withdrawal*/
                {
                    modifyposition.seek((index - 1) * 4);           //moves the pointer to the index
                    String currentbal = modifyposition.readLine();      //storing the current balance of the account
                    System.out.println(currentbal);
                    int bal = Integer.parseInt(currentbal) - amount;    //Storing the balance after withdrawal
                    modifyposition.seek(0);                         //moving cursor back to the start of the file
                    modifyposition.close();
                    StringBuilder data = new StringBuilder();
                    //this is for the updated balance database
                    String datarecieved;                       //this is for reading the old database
                    while (count<index)                                 //reading the balance till the target account
                    {
                        datarecieved = recoverbal.readLine();
                        data.append(String.format("%s\n", datarecieved));
                        count+=1;
                    }
                    data.append(String.format("%s\n", bal));             //storing the updated balance for the targeted account
                    System.out.println("Amount of Rs "+amount+" has been Withdrawn Successfully. \n Your Balance Remaining is: "+bal);
                    count+=1;                                           //increasing the count by 1 as we skipped the old data
                    recoverbal.readLine();                              //moving the cursor to the next data by which we skipped the old data
                    int endf = eof();
                    while(count<endf)                                  //now all the data after the target account is stored as before
                    {
                        datarecieved = recoverbal.readLine();
                        data.append(String.format("%s\n", datarecieved));
                        count+=1;
                    }
                    recoverbal.close();
                    PrintWriter updatingbal = new PrintWriter(new FileWriter("balance.txt"));  /*we didn't use true for append bcz we will flush the old data and stor
                                                                                                         the new data in the same file balance.txt*/
                    updatingbal.append(data.toString());                                                           /*new data is stored in the balance.txt*/
                    updatingbal.close();
                }
                else if( option == 4)
                {
                    modifyposition.seek((index - 1) * 4);                                                            //moves the pointer to the index
                    String currentbal = modifyposition.readLine();                                                      //storing the current balance of the account
                    int bal = Integer.parseInt(currentbal) + amount;                                                    //Storing the balance after withdrawal
                    modifyposition.seek(0);                                                                         //moving cursor back to the start of the file
                    modifyposition.close();
                    StringBuilder data= new StringBuilder();                                                                                   //this is for the updated balance database
                    String datarecieved;                                                                       //this is for reading the old database
                    while (count<index)                                                                                 //reading the balance till the target account
                    {
                        datarecieved = recoverbal.readLine();
                        data.append(String.format("%s\n", datarecieved));
                        count+=1;
                    }
                    data.append(String.format("%s\n", bal));                                                             //storing the updated balance for the targeted account
                    System.out.println("Amount of Rs "+amount+" has been Deposited Successfully. \n Your Balance is: "+bal);
                    count+=1;                                                                                           //increasing the count by 1 as we skipped the old data
                    recoverbal.readLine();                                                                              //moving the cursor to the next data by which we skipped the old data
                    int endf=eof();
                    while(count<endf)                                                                                  //now all the data after the target account is stored as before
                    {
                        datarecieved = recoverbal.readLine();
                        data.append(String.format("%s\n", datarecieved));
                        count+=1;
                    }
                    recoverbal.close();
                    PrintWriter updatingbal = new PrintWriter(new FileWriter("balance.txt"));               /*we didn't use true for append bcz we will flush the old data and stor
                                                                                                                    the new data in the same file balance.txt*/
                    updatingbal.append(data.toString());                                                           /*new data is stored in the balance.txt*/
                    updatingbal.close();
                }

            }
            catch(IOException ie)
            {
                System.out.println("Something Went Wrong");
            }
    }
    /*This Function opend the bank Account and Store it in the data base*/
    public void OpenBankAccount()
    {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerint = new Scanner(System.in);
        String acname, acnum, father, adress;
        System.out.print("Name : ");
        acname = scanner.nextLine();
        System.out.print("Age : ");
        int age = scannerint.nextInt();
        System.out.print("Fathers Name : ");
        father = scanner.nextLine();
        System.out.print(" Address : ");
        adress = scanner.nextLine();
        if (age>18)
        {
            acnum = String.valueOf(100000000 + new Random().nextInt(900000000));        /*Randomly Generating the Account number of 9 digits*/
            BankStruct bankStruct = new BankStruct(acnum, acname,1000,father,adress,age);
            System.out.println("Registered Successfully.\n Your account No. is: " + acnum);
        }
        else
            System.out.println("You are Not eligible!!!");
        }
}


