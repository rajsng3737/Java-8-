import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BankGUI extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Bank XDZC");
        BankStructGUI XDZC = new BankStructGUI(null,null,0,null,null,0);

        Text title = new Text();

        Button open_bank_account = new Button("Open Bank Account");
        Button account_details = new Button("Account Details");
        Button withdraw = new Button("Withdraw");
        Button deposit = new Button("Deposit");
        Button quit = new Button("QUIT");
        Button back =new Button("Back");

        title.setFill(Color.ROYALBLUE);
        title.setStroke(Color.RED);
        title.setFont(Font.font("veranda",FontWeight.BOLD,50));
        title.setText("Welcome TO XDZC Bank");

        open_bank_account.setMaxWidth(200);
        account_details.setMaxWidth(200);
        withdraw.setMaxWidth(200);
        deposit.setMaxWidth(200);
        quit.setMaxWidth(200);

        Background buttonBackG = new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY));
        quit.setBackground(buttonBackG);

        VBox home = new VBox(20,open_bank_account,account_details,withdraw,deposit,quit);
        VBox padd = new VBox(150,title,home);

        home.setAlignment(Pos.CENTER);
        padd.setAlignment(Pos.TOP_CENTER);

        Scene homescreen = new Scene(padd, 800, 600);

        primaryStage.setScene(homescreen);
        primaryStage.show();

        open_bank_account.setOnAction(askDetails ->
            {
                primaryStage.setTitle("Open Bank Account");
                Text titleopen = new Text();
                titleopen.setFill(Color.ROYALBLUE);
                titleopen.setStroke(Color.RED);
                titleopen.setFont(Font.font("veranda",FontWeight.BOLD,50));
                titleopen.setText("Open Bank Account");

                Label error = new Label("");
                Label account_Name = new Label("Account Name :");
                Label age = new Label("Age");
                Label father_Name = new Label("Father Name :");
                Label address = new Label("Address :");
                Text status = new Text();

                TextField account_Name_field = new TextField("Enter Your Name");
                TextField age_Field = new TextField("Example - 19");
                TextField father_Name_Field = new TextField("Enter Your Father's Name");
                TextField address_Field = new TextField("Example - 12,Street columbia,Los Angeles,76587");

                Button open_Account = new Button("Open Account");

                open_Account.setOnAction(inauguration ->
                {
                    try {
                        Button cont = new Button("Continue");
                        cont.setOnAction(backToHome -> {
                            primaryStage.setScene(homescreen);
                            primaryStage.setTitle("BankXDZC");
                            primaryStage.show();
                        });
                        cont.setAlignment(Pos.CENTER);
                        String XDZC_name = account_Name_field.getText();
                        int XDZC_age = Integer.parseInt(age_Field.getText());
                        String XDZC_fatherName = father_Name_Field.getText();
                        String XDZC_address = address_Field.getText();
                        status.setText(XDZC.openBankAccount(XDZC_name,XDZC_age,XDZC_fatherName,XDZC_address));
                        VBox cont_VBox = new VBox(20,status,cont);
                        cont_VBox.setAlignment(Pos.CENTER);
                        Scene cont_Screen = new Scene(cont_VBox,800,600);
                        primaryStage.setScene(cont_Screen);
                        primaryStage.show();
                    }
                    catch (Exception ie)
                    {
                        error.setText("Please enter Age as a Number");
                        error.setTextFill(Color.RED);
                    }
                });
                HBox account_Name_HBOX = new HBox(10,account_Name,account_Name_field);
                HBox age_HBOX = new HBox(10,age,age_Field,error);
                HBox father_Name_HBOX = new HBox(10,father_Name,father_Name_Field);
                HBox address_HBOX = new HBox(10,address,address_Field);

                account_Name.setMinWidth(100);
                age.setMinWidth(100);
                father_Name.setMinWidth(100);
                address.setMinWidth(100);
                error.setMaxWidth(200);

                account_Name_HBOX.setAlignment(Pos.CENTER);
                age_HBOX.setAlignment(Pos.CENTER);
                father_Name_HBOX.setAlignment(Pos.CENTER);
                address_HBOX.setAlignment(Pos.CENTER);

                back.setOnAction(back2menu ->
                {
                    primaryStage.setScene(homescreen);
                    primaryStage.setTitle("BankXDZC");
                    primaryStage.show();
                });

                VBox combined_HBox = new VBox(20,account_Name_HBOX,age_HBOX,father_Name_HBOX,address_HBOX,open_Account,status,back);
                VBox open_Bank_Account_padd = new VBox(100,titleopen,combined_HBox);

                combined_HBox.setAlignment(Pos.BOTTOM_CENTER);
                open_Bank_Account_padd.setAlignment(Pos.TOP_CENTER);

                Scene openAccount = new Scene(open_Bank_Account_padd,800,600);
                primaryStage.setScene(openAccount);
                primaryStage.setTitle("Open Account");
                primaryStage.show(); 
            });

        account_details.setOnAction(showDetails -> 
            {
                Insets paddin =new Insets(20,0,0,0);
    
                TextField acNum_Field = new TextField("Enter Account Number");
                acNum_Field.setMaxWidth(250);
    
                Button show_Result = new Button("Show");
    
                Text headingText = new Text("Account Details");
                Text detailsText = new Text();
    
                headingText.setFill(Color.rgb(190,100,50));
                headingText.setFont(Font.font(Font.getFontNames().get(3),FontWeight.BOLD,50));
                headingText.setStroke(Color.BLACK);
                headingText.setStrokeWidth(2);
                show_Result.setTextAlignment(TextAlignment.CENTER);
    
                VBox coll = new VBox(20,headingText,acNum_Field,show_Result,detailsText);
                coll.setAlignment(Pos.TOP_CENTER);
                coll.setPadding(paddin);
                Scene showData =new Scene(coll,800,600);
                primaryStage.setScene(showData);
                primaryStage.setTitle("Account Details");
                primaryStage.show();
                show_Result.setAlignment(Pos.CENTER);
    
                show_Result.setOnAction(g -> 
                {
                    Label error =new Label("");

                    back.setAlignment(Pos.BOTTOM_CENTER);
                    back.setPrefSize(60,10);
                    
                    HBox backContainer = new HBox(back);
                    
                    backContainer.setPadding(new Insets(20,0,0,0));
                    backContainer.setAlignment(Pos.CENTER);
                    
                    back.setOnAction(back2menu -> 
                    {
                        primaryStage.setScene(homescreen);
                        primaryStage.setTitle("BankXDZC");
                        primaryStage.show();
                    });
                    try 
                    {
                        Label details_Name = new Label("Name : ");
                        Label details_Fathername = new Label("Father Name : ");
                        Label details_Address = new Label("Address : ");
                        Label details_Age = new Label("Age : ");
                        Label details_Accountnumber = new Label("Account Number : ");
                        Label details_Balance = new Label("Balance : ");
                        Label returnedDetails_name = new Label();
                        Label returnedDetails_Fathername = new Label();
                        Label returnedDetails_Address = new Label();
                        Label returnedDetails_Age = new Label();
                        Label returnedDetails_AccountNumber = new Label();
                        Label returnedDetails_Balance = new Label();
                        
                        String[] getting = XDZC.getAccountDetails(acNum_Field.getText());
                        
                        returnedDetails_name.setText(getting[0]);
                        returnedDetails_Fathername.setText(getting[1]);
                        returnedDetails_Address.setText(getting[2]);
                        returnedDetails_Age.setText(getting[3]);
                        returnedDetails_AccountNumber.setText(getting[4]);
                        returnedDetails_Balance.setText(String.valueOf(XDZC.getbalance(XDZC.scanaccount(acNum_Field.getText()))));
                        
                        HBox rd_Name = new HBox(25,details_Name,returnedDetails_name);
                        HBox rd_FatherName = new HBox(25,details_Fathername,returnedDetails_Fathername);
                        HBox rd_Address = new HBox(25,details_Address,returnedDetails_Address);
                        HBox rd_Age = new HBox(25,details_Age,returnedDetails_Age);
                        HBox rd_Account_number = new HBox(25,details_Accountnumber,returnedDetails_AccountNumber);
                        HBox rd_balance = new HBox(25,details_Balance,returnedDetails_Balance);
    
                        rd_Name.setPadding(new Insets(25,0,0,0));
                        rd_Name.setAlignment(Pos.CENTER);
                        rd_FatherName.setAlignment(Pos.CENTER);
                        rd_Address.setAlignment(Pos.CENTER);
                        rd_Age.setAlignment(Pos.CENTER);
                        rd_Account_number.setAlignment(Pos.CENTER);
                        rd_balance.setAlignment(Pos.CENTER);
    
                        VBox onscreen = new VBox(20,coll,rd_Name,rd_Account_number,rd_FatherName,rd_Address,rd_Age,rd_balance,backContainer);
                        
                        onscreen.setAlignment(Pos.TOP_CENTER);
                        onscreen.setMinHeight(720);
                        
                        coll.setPadding(paddin);
    
                        Scene details =new Scene(onscreen,800,600);
                        primaryStage.setScene(details);
                        primaryStage.setTitle("Account Details");
                        primaryStage.show();
                    }
                    catch (RuntimeException ie)
                    {
                        error.setText("Account Not Found");
                        VBox onscreen = new VBox(20, coll, error, backContainer);
                        onscreen.setAlignment(Pos.TOP_CENTER);
                        onscreen.setMinHeight(720);
                        coll.setPadding(paddin);
    
                        Scene details = new Scene(onscreen, 800, 600);
                        primaryStage.setScene(details);
                        primaryStage.setTitle("Account Not Found");
                        primaryStage.show();
                    }
                    
                });
        });
        
        

    }

}
