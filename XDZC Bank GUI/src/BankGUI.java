import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BankGUI extends Application
{
    Stage otherStage = new Stage();
    Button back = new Button("Back");
    BankStructGUI XDZC = new BankStructGUI(null, null, 0, null, null, 0);

    public void start(Stage primaryStage) {
        this.otherStage=primaryStage;

        primaryStage.setTitle("Bank XDZC");
        Text title = new Text();
        Button openBankAccount = new Button("Open Bank Account");
        Button accountDetails = new Button("Account Details");
        Button withdraw = new Button("Withdraw");
        Button deposit = new Button("Deposit");
        Button quit = new Button("QUIT");

        title.setFill(Color.ROYALBLUE);
        title.setStroke(Color.RED);
        title.setFont(Font.font("veranda", FontWeight.BOLD, 50));
        title.setText("Welcome TO XDZC Bank");

        openBankAccount.setMaxWidth(200);
        accountDetails.setMaxWidth(200);
        withdraw.setMaxWidth(200);
        deposit.setMaxWidth(200);
        quit.setMaxWidth(200);

        Background buttonBackG = new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY));
        quit.setBackground(buttonBackG);
        quit.setOnMouseEntered(color -> quit.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,Insets.EMPTY))));
        quit.setOnMouseExited(color -> quit.setBackground(buttonBackG));

        VBox home = new VBox(20, openBankAccount, accountDetails, withdraw, deposit, quit);
        VBox padding = new VBox(150, title, home);

        home.setAlignment(Pos.CENTER);
        padding.setAlignment(Pos.TOP_CENTER);

        Scene homeScreen = new Scene(padding, 800, 600);

        primaryStage.setScene(homeScreen);
        primaryStage.show();
        back.setOnAction(back2menu ->
        {
            primaryStage.setScene(homeScreen);
            primaryStage.setTitle("BankXDZC");
            primaryStage.show();
        });
        openBankAccount.setOnAction(askDetails ->
        {
            primaryStage.setTitle("Open Bank Account");
            Text titleOpenScreen = new Text();
            titleOpenScreen.setFill(Color.ROYALBLUE);
            titleOpenScreen.setStroke(Color.RED);
            titleOpenScreen.setFont(Font.font("veranda", FontWeight.BOLD, 50));
            titleOpenScreen.setText("Open Bank Account");

            Label error = new Label("");
            Label accountName = new Label("Account Name :");
            Label age = new Label("Age");
            Label fatherName = new Label("Father Name :");
            Label address = new Label("Address :");

            Text status = new Text();

            TextField accountNameField = new TextField("Enter Your Name");
            accountNameField.setOnMouseClicked(empty-> accountNameField.setText(""));
            TextField ageField = new TextField("Example - 19");
            ageField.setOnMouseClicked(empty-> ageField.setText(""));
            TextField fatherNameField = new TextField("Enter Your Father's Name");
            fatherNameField.setOnMouseClicked(empty-> fatherNameField.setText(""));
            TextField addressField = new TextField("Example - 12,Street columbia,Los Angeles,76587");
            addressField.setOnMouseClicked(empty-> addressField.setText(""));

            Button openAccount = new Button("Open Account");

            openAccount.setOnAction(inauguration ->
            {
                try {
                    Button cont = new Button("Continue");
                    cont.setOnAction(backToHome -> {
                        primaryStage.setScene(homeScreen);
                        primaryStage.setTitle("BankXDZC");
                        primaryStage.show();
                    });
                    cont.setAlignment(Pos.CENTER);
                    String XDZCname = accountNameField.getText();
                    int XDZCage = Integer.parseInt(ageField.getText());
                    String FatherName = fatherNameField.getText();
                    String getAddress = addressField.getText();
                    status.setText(XDZC.openBankAccount(XDZCname, XDZCage, FatherName, getAddress));
                    VBox contVBox = new VBox(20, status, cont);
                    contVBox.setAlignment(Pos.CENTER);
                    Scene contScreen = new Scene(contVBox, 800, 600);
                    primaryStage.setScene(contScreen);
                    primaryStage.show();
                } catch (Exception ie) {
                    error.setText("Please enter Age as a Number");
                    error.setTextFill(Color.RED);
                }
            });
            HBox accountNameHBOX = new HBox(10, accountName, accountNameField);
            HBox ageHBox = new HBox(10, age, ageField, error);
            HBox fatherNameHBOX = new HBox(10, fatherName, fatherNameField);
            HBox addressHBOX = new HBox(10, address, addressField);

            accountName.setMinWidth(100);
            age.setMinWidth(100);
            fatherName.setMinWidth(100);
            address.setMinWidth(100);
            error.setMaxWidth(200);

            accountNameHBOX.setAlignment(Pos.CENTER);
            ageHBox.setAlignment(Pos.CENTER);
            fatherNameHBOX.setAlignment(Pos.CENTER);
            addressHBOX.setAlignment(Pos.CENTER);

            back.setOnAction(back2menu ->
            {
                primaryStage.setScene(homeScreen);
                primaryStage.setTitle("BankXDZC");
                primaryStage.show();
            });

            VBox combinedHBox = new VBox(20, accountNameHBOX, ageHBox, fatherNameHBOX, addressHBOX, openAccount, status, back);
            VBox openBankAccountPadding = new VBox(100, titleOpenScreen, combinedHBox);

            combinedHBox.setAlignment(Pos.BOTTOM_CENTER);
            openBankAccountPadding.setAlignment(Pos.TOP_CENTER);

            Scene openAccountScene = new Scene(openBankAccountPadding, 800, 600);
            primaryStage.setScene(openAccountScene);
            primaryStage.setTitle("Open Account");
            primaryStage.show();
        });
        accountDetails.setOnAction(showDetails ->
        {
            TextField acNumField = new TextField("Enter Account Number");
            acNumField.setOnMouseClicked(empty -> acNumField.setText(""));
            acNumField.setMaxWidth(250);

            Button showResult = new Button("Show");

            Text headingText = new Text("Account Details");
            Text detailsText = new Text();

            headingText.setFill(Color.rgb(190, 100, 50));
            headingText.setFont(Font.font(Font.getFontNames().get(3), FontWeight.BOLD, 50));
            headingText.setStroke(Color.BLACK);
            headingText.setStrokeWidth(2);
            showResult.setTextAlignment(TextAlignment.CENTER);

            VBox coll = new VBox(20, headingText, acNumField, showResult, detailsText);
            coll.setAlignment(Pos.TOP_CENTER);
            coll.setPadding(new Insets(20, 0, 0, 0));
            Scene showData = new Scene(coll, 800, 600);
            primaryStage.setScene(showData);
            primaryStage.setTitle("Account Details");
            primaryStage.show();
            showResult.setAlignment(Pos.CENTER);

            showResult.setOnAction(g ->
            {
                Label error = new Label("");

                back.setAlignment(Pos.BOTTOM_CENTER);
                back.setPrefSize(60, 10);

                HBox backContainer = new HBox(back);

                backContainer.setPadding(new Insets(20, 0, 0, 0));
                backContainer.setAlignment(Pos.CENTER);

                try {
                    Label detailsName = new Label("Name : ");
                    Label detailsFatherName = new Label("Father Name : ");
                    Label detailsAddress = new Label("Address : ");
                    Label detailsAge = new Label("Age : ");
                    Label detailsAccountNumber = new Label("Account Number : ");
                    Label detailsBalance = new Label("Balance : ");
                    Label returnedDetailsName = new Label();
                    Label returnedDetailsFatherName = new Label();
                    Label returnedDetailsAddress = new Label();
                    Label returnedDetailsAge = new Label();
                    Label returnedDetailsAccountNumber = new Label();
                    Label returnedDetailsBalance = new Label();

                    String[] getting = XDZC.getAccountDetails(acNumField.getText());

                    returnedDetailsName.setText(getting[0]);
                    returnedDetailsFatherName.setText(getting[1]);
                    returnedDetailsAddress.setText(getting[2]);
                    returnedDetailsAge.setText(getting[3]);
                    returnedDetailsAccountNumber.setText(getting[4]);
                    returnedDetailsBalance.setText(String.valueOf(XDZC.getBalance(XDZC.scanAccount(acNumField.getText()))));

                    HBox rdName = new HBox(25, detailsName, returnedDetailsName);
                    HBox rdFatherName = new HBox(25, detailsFatherName, returnedDetailsFatherName);
                    HBox rdAddress = new HBox(25, detailsAddress, returnedDetailsAddress);
                    HBox rdAge = new HBox(25, detailsAge, returnedDetailsAge);
                    HBox rdAccountNumber = new HBox(25, detailsAccountNumber, returnedDetailsAccountNumber);
                    HBox rdBalance = new HBox(25, detailsBalance, returnedDetailsBalance);

                    rdName.setPadding(new Insets(25, 0, 0, 0));
                    rdName.setAlignment(Pos.CENTER);
                    rdFatherName.setAlignment(Pos.CENTER);
                    rdAddress.setAlignment(Pos.CENTER);
                    rdAge.setAlignment(Pos.CENTER);
                    rdAccountNumber.setAlignment(Pos.CENTER);
                    rdBalance.setAlignment(Pos.CENTER);

                    VBox onscreen = new VBox(20, coll, rdName, rdAccountNumber, rdFatherName, rdAddress, rdAge, rdBalance, backContainer);

                    onscreen.setAlignment(Pos.TOP_CENTER);
                    onscreen.setMinHeight(720);

                    coll.setPadding(new Insets(20, 0, 0, 0));

                    Scene details = new Scene(onscreen, 800, 600);
                    primaryStage.setScene(details);
                    primaryStage.setTitle("Account Details");
                    primaryStage.show();
                } catch (RuntimeException ie) {
                    error.setText("Account Not Found");
                    VBox onscreen = new VBox(20, coll, error, backContainer);
                    onscreen.setAlignment(Pos.TOP_CENTER);
                    onscreen.setMinHeight(720);
                    coll.setPadding(new Insets(20, 0, 0, 0));

                    Scene details = new Scene(onscreen, 800, 600);
                    primaryStage.setScene(details);
                    primaryStage.setTitle("Account Not Found");
                    primaryStage.show();
                }
            });
        });
        withdraw.setOnAction(withdrawMoney -> transactions(3));
        deposit.setOnAction(depositButton -> transactions(4));
        quit.setOnAction(exiting -> primaryStage.close());
    }
         private void transactions (int i)
         {
             TextField accountNumber = new TextField("Enter Account Number : ");
             accountNumber.setOnMouseClicked(empty -> accountNumber.setText(""));
             Label accountNumberLabel = new Label("Account Number : ");
             Button checkNumber = new Button("Show");
             Text inauguration = new Text("Welcome ");
                 checkNumber.setOnAction(checking ->
                 {
                     if (i == 3)
                     {
                         HBox checkingBox = new HBox(20, accountNumberLabel, accountNumber, checkNumber);
                         checkingBox.setAlignment(Pos.CENTER);
                         checkingBox.setPadding(new Insets(0, 0, 72, 0));
                         Scene withdrawScreen = new Scene(checkingBox, 800, 600);
                         otherStage.setScene(withdrawScreen);
                         String[] details = XDZC.getAccountDetails(accountNumber.getText());

                         if (details != null) {
                             Label accountName = new Label(details[0]);
                             TextField amount2Withdraw = new TextField("Enter Amount to Withdraw");
                             amount2Withdraw.setOnMouseClicked(empty -> amount2Withdraw.setText(""));
                             Button Withdraw = new Button("Withdraw");
                             Withdraw.setOnAction(wish -> {
                                 Text wishes;
                                 int index = XDZC.scanAccount(accountNumber.getText());
                                 if ( XDZC.getBalance(index)-Integer.parseInt(amount2Withdraw.getText()) > 0) {
                                     XDZC.setBalance(Integer.parseInt(amount2Withdraw.getText()), index, 3);
                                     wishes = new Text("Amount of Rs." + amount2Withdraw.getText() + " has been Withdrawn Successfully.");
                                     VBox done = new VBox(20, wishes, back);
                                     done.setAlignment(Pos.CENTER);
                                     Scene wishingScene = new Scene(done, 800, 600);
                                     otherStage.setScene(wishingScene);
                                 } else {
                                     wishes = new Text("Insufficient Funds.");
                                     VBox done = new VBox(20, wishes, back);
                                     done.setAlignment(Pos.CENTER);
                                     Scene wishingScene = new Scene(done, 800, 600);
                                     otherStage.setScene(wishingScene);
                                 }
                                 otherStage.show();
                             });
                             HBox welcome = new HBox(inauguration, accountName);
                             welcome.setAlignment(Pos.CENTER);
                             HBox checkedBox = new HBox(20, welcome, amount2Withdraw, Withdraw);
                             checkedBox.setAlignment(Pos.CENTER);
                             VBox allCollect = new VBox(20, welcome, checkedBox);
                             allCollect.setAlignment(Pos.CENTER);
                             Scene checkDetails = new Scene(allCollect, 800, 600);
                             otherStage.setScene(checkDetails);
                         } else {
                             Label notFound = new Label("Account Not Found!");
                             notFound.setPadding(new Insets(20, 0, 0, 0));
                             VBox collectAll = new VBox(checkingBox, notFound, back);
                             collectAll.setAlignment(Pos.CENTER);
                             Scene checkDetailsError = new Scene(collectAll, 800, 600);
                             otherStage.setScene(checkDetailsError);
                         }
                         otherStage.show();
                     }
                    if( i == 4)
                    {
                         String[] details = XDZC.getAccountDetails(accountNumber.getText());
                         if (details != null) {

                             Label accountName = new Label(details[0]);
                             TextField amount2Deposit = new TextField("Enter Amount to Deposit");
                             amount2Deposit.setOnMouseClicked(empty -> amount2Deposit.setText(""));
                             Button Withdraw = new Button("Deposit");
                             Withdraw.setOnAction(wish -> {
                                 Text wishes;
                                 int index = XDZC.scanAccount(accountNumber.getText());
                                     XDZC.setBalance(Integer.parseInt(amount2Deposit.getText()), index, 4);
                                     wishes = new Text("Amount of Rs." + amount2Deposit.getText() + " has been Deposited Successfully.");
                                     VBox done = new VBox(20, wishes, back);
                                     done.setAlignment(Pos.CENTER);
                                     Scene wishingScene = new Scene(done, 800, 600);
                                     otherStage.setScene(wishingScene);
                                 otherStage.show();
                             });
                             HBox welcome = new HBox(inauguration, accountName);
                             welcome.setAlignment(Pos.CENTER);
                             HBox checkedBox = new HBox(20, welcome, amount2Deposit, Withdraw);
                             checkedBox.setAlignment(Pos.CENTER);
                             VBox allCollect = new VBox(20, welcome, checkedBox);
                             allCollect.setAlignment(Pos.CENTER);
                             Scene checkDetails = new Scene(allCollect, 800, 600);
                             otherStage.setScene(checkDetails);
                         } else {
                             Label notFound = new Label("Account Not Found!");
                             notFound.setPadding(new Insets(20, 0, 0, 0));
                             HBox display = new HBox(20, accountNumberLabel, accountNumber, checkNumber);
                             display.setAlignment(Pos.CENTER);
                             VBox collectAll = new VBox(display, notFound, back);
                             collectAll.setAlignment(Pos.CENTER);
                             Scene checkDetailsError = new Scene(collectAll, 800, 600);
                             otherStage.setScene(checkDetailsError);
                         }
                         otherStage.show();
                    }
                });
                 HBox checkingBox = new HBox(20, accountNumberLabel, accountNumber, checkNumber);
                 checkingBox.setAlignment(Pos.CENTER);
                 checkingBox.setPadding(new Insets(0, 0, 72, 0));
                 Scene commonScreen = new Scene(checkingBox, 800, 600);
                 otherStage.setScene(commonScreen);
             }
}
