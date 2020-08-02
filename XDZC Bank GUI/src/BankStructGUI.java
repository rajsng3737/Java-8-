import java.io.*;
import java.util.Random;
import java.io.RandomAccessFile;
public class BankStructGUI
{
    /*BankStruct constructor accepts 6 values , Account information is stored in BankData.txt and balance is
    stored in balance.txt */
    public BankStructGUI(String accountNumber,String accountName,int bal,String fatherN,String address,int age)
    {
        if(accountNumber!=null)
        {
            try
            {
                FileWriter account = new FileWriter("BankData.txt",true);
                FileWriter balance = new FileWriter("balance.txt",true);
                PrintWriter accountWriter = new PrintWriter(account);
                PrintWriter balanceWriter = new PrintWriter(balance);
                accountWriter.println(accountName+":"+fatherN+":"+address+":"+age+":"+accountNumber);
                balanceWriter.println(bal);
                accountWriter.close();
                balanceWriter.close();
            }
            catch(IOException ie){
                System.out.println("Something Went Wrong");
            }

        }
    }
    /*scanAccount method tells us the index of a particular account in a BankData.txt */
    public int scanAccount(String acNum)
    {
        String search;
        int count = 1;
        try {
            FileReader account = new FileReader("BankData.txt");
            BufferedReader accountReader = new BufferedReader(account);
            int endIndex = eof();
            while (endIndex>count)                  /* Searches till the account is not found */
            {
                search = accountReader.readLine();  /*compares for every readLine() ending with the provided argument or not */
                if (search.endsWith(acNum)) {
                    return count;
                }
                count += 1;
            }
            accountReader.close();
        } catch (IOException ie) {
            System.out.println("Something Went Wrong");
        }
        return 0;
    }
    /*This function tells us the index of the last account in the file*/
    private int eof()
    {
        int count=1;
        try
        {
            FileReader account = new FileReader("BankData.txt");
            BufferedReader accountReader = new BufferedReader(account);
            while (accountReader.readLine() != null)            /*iterates till the readLine doesn't give null which is at the end of the file*/
            {
                count += 1;                                     /*Index Increases*/
            }
            accountReader.close();
        }
        catch(IOException ie)
        {
            System.out.println("Something Went Wrong");
        }
        return count;
    }
    /*This method Displays the Details of a Given Account Number*/
    public String[] getAccountDetails(String acNum)
    {
        try
        {
            int count =1;
            FileReader file = new FileReader("BankData.txt");                                           //Reading File BankData
            BufferedReader reading = new BufferedReader(file);
            String[] searching= new String[5];
            searching[0] = "Just for Initializing";
            int check = eof();
            while(count<check)
            {
                searching = reading.readLine().split(":");
                if(searching[4].equals(acNum))                                                                  //comparing the account number given and Stored in Database
                {
                    reading.close();
                    return searching;                                                                           //passing the string for displaying the results
                }
                count+=1;
            }
        }
        catch(IOException ie)
        {
            System.out.println("Something Went Wrong");
        }
        return null;
    }
    /*This Function Reads and returns the balance of a given account number*/
    public int getBalance(int index)
    {
        int num=1;
        try
        {
            FileReader balance = new FileReader("Balance.txt");
            BufferedReader balanceReader = new BufferedReader(balance);
            while(num != index)                                                                          //iterating to the target account
            {
                balanceReader.readLine();                                                               //parsing through the file
                num+=1;
            }
            int bal = Integer.parseInt(balanceReader.readLine());                                       //reading the balance
            balanceReader.close();
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
    public void setBalance(int amount,int index,int option)
    {
        try {
            int count=1;
            RandomAccessFile modifyPosition = new RandomAccessFile("balance.txt", "r");
            BufferedReader readBal = new BufferedReader(new FileReader("balance.txt"));
            if (option == 3)    /*this is for withdrawal*/ 
                {
                modifyPosition.seek((index - 1) * 4);//moves the pointer to the index
                while (count < index)
                {
                    readBal.readLine();
                    count += 1;
                }
                String currentBal = readBal.readLine();      //storing the current balance of the account
                int bal = Integer.parseInt(currentBal) - amount;    //Storing the balance after withdrawal
                modifyPosition.seek(0);                         //moving cursor back to the start of the file
                modifyPosition.close();
                updatingBalDatabase(bal,index);
            }
            else if( option == 4)           //this is for deposit
            {
                modifyPosition.seek((index - 1) * 4);//moves the pointer to the index
                while (count < index)
                {
                    readBal.readLine();
                    count += 1;
                }
                String currentBal = readBal.readLine();      //storing the current balance of the account
                int bal = Integer.parseInt(currentBal) + amount;    //Storing the balance after withdrawal
                modifyPosition.seek(0);                         //moving cursor back to the start of the file
                modifyPosition.close();
                updatingBalDatabase(bal,index);
            }
        }
        catch(IOException ie)
        {
            System.out.println("Something Went Wrong");
        }
    }
    private void updatingBalDatabase(int bal,int index) 
    {
        try {
            BufferedReader recoverBal = new BufferedReader(new FileReader("balance.txt"));
            StringBuilder data = new StringBuilder();   //this is for the updated balance database
            String dataReceived;                        //this is for reading the old database
            int count = 1;
            while (count < index)                                 //reading the balance till the target account
            {
                dataReceived = recoverBal.readLine();
                data.append(String.format("%s\n", dataReceived));
                count += 1;
            }
            data.append(String.format("%s\n", bal));             //storing the updated balance for the targeted account
            count += 1;                                           //increasing the count by 1 as we skipped the old data
            recoverBal.readLine();                              //moving the cursor to the next data by which we skipped the old data
            int fileEnd = eof();
            while (count < fileEnd)                                  //now all the data after the target account is stored as before
            {
                dataReceived = recoverBal.readLine();
                data.append(String.format("%s\n", dataReceived));
                count += 1;
            }
            recoverBal.close();
            PrintWriter updatingBal = new PrintWriter(new FileWriter("balance.txt"));  /*we didn't use true for append bcz we will flush the old data and store the new data in the same file balance.txt*/
            updatingBal.append(data.toString());                                                           /*new data is stored in the balance.txt*/
            updatingBal.close();
        }
        catch(IOException ie)
        {
            System.out.println("Something Went Wrong");
        }
    }
    /*This Function opens the bank Account and Store it in the data base*/
    public String openBankAccount(String acName,int age,String father,String address)
    {
        if (age>18)
        {
            String acNum = String.valueOf(100000000 + new Random().nextInt(900000000));        /*Randomly Generating the Account number of 9 digits*/
            BankStructGUI bankStruct = new BankStructGUI(acNum, acName,1000,father,address,age);
            return ("Registered Successfully. Your account No. is: "+acNum);
        }
        else
            return "You are Not eligible!";
    }
}
