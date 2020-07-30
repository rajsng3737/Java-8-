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
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicInteger;

public class BankGUI extends Application
{
    @Override
    public void start(Stage primaryStage) {
        Button open_bank_account = new Button("Open Bank Account");
        Button account_details = new Button("Account Details");
        Button withdraw = new Button("Withdraw");
        Button deposit = new Button("Deposit");
        Button quit = new Button("QUIT");
        AtomicInteger count = new AtomicInteger();
        Background buttonBackG = new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY));
        open_bank_account.setMaxWidth(200);
        open_bank_account.setOnAction(e ->
        {
            BankStructGUI XDZC = new BankStructGUI(null,null,0,null,null,0);
            Text title = new Text();
            title.setFill(Color.ROYALBLUE);
            title.setStroke(Color.RED);
            title.setFont(Font.font("veranda",FontWeight.BOLD,50));
            title.setText("Welcome TO XDZC Bank");

            Label account_Name = new Label("Account Name :");
            Label age = new Label("Age");
            Label father_Name = new Label("Father Name :");
            Label address = new Label("Address :");
            Label error = new Label("");
            Text status = new Text();

            TextField account_Name_field = new TextField("Enter Your Name");
            TextField age_Field = new TextField("Example - 19");
            TextField father_Name_Field = new TextField("Enter Your Father's Name");
            TextField address_Field = new TextField("Example - 12,Street columbia,Los Angeles,76587");

            Button open_Account = new Button("Open Account");

            open_Account.setOnAction(f ->
            {
                try {
                    Button cont = new Button("Continue");
                    cont.setAlignment(Pos.CENTER);
                    String XDZC_name = account_Name_field.getText();
                    int XDZC_age = Integer.parseInt(age_Field.getText());
                    String XDZC_fatherName = father_Name_Field.getText();
                    String XDZC_address = address_Field.getText();
                    status.setText(XDZC.openBankAccount(XDZC_name,XDZC_age,XDZC_fatherName,XDZC_address));
                    VBox cont_VBox = new VBox(20,status,cont);
                    cont_VBox.setAlignment(Pos.CENTER);
                    Scene cont_Screen = new Scene(cont_VBox,1280,720);
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

            VBox combined_HBox = new VBox(20,account_Name_HBOX,age_HBOX,father_Name_HBOX,address_HBOX,open_Account,status);
            VBox padd = new VBox(100,title,combined_HBox);
            combined_HBox.setAlignment(Pos.BOTTOM_CENTER);
            padd.setAlignment(Pos.TOP_CENTER);
            Scene openAccount = new Scene(padd,1280,720);
            primaryStage.setScene(openAccount);
            primaryStage.show();
            count.addAndGet(1);
        });
        account_details.setMaxWidth(200);
        withdraw.setMaxWidth(200);
        deposit.setMaxWidth(200);
        quit.setMaxWidth(200);
        quit.setBackground(buttonBackG);
        Text title = new Text();
        title.setFill(Color.ROYALBLUE);
        title.setStroke(Color.RED);
        title.setFont(Font.font("veranda",FontWeight.BOLD,50));
        title.setText("Welcome TO XDZC Bank");
        VBox home = new VBox(20,open_bank_account,account_details,withdraw,deposit,quit);
        home.setAlignment(Pos.CENTER);
        VBox padd = new VBox(150,title,home);
        padd.setAlignment(Pos.TOP_CENTER);
        if (count.get() == 0) {
            Scene homescreen = new Scene(padd, 800, 600);
            primaryStage.setScene(homescreen);
            primaryStage.show();
        }
        else if(count.get()==1)
        {
            Scene homescreen = new Scene(home, 800, 600);
            primaryStage.setScene(homescreen);
            primaryStage.show();
        }

    }

}