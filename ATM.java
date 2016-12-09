/*
    Author - Nate Rupsis
    File - ATM.java
    Desc - The view class, designed to be the wrapper around the control class and prvide the user with a GUI
*/
// our import statments
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ATM extends JFrame implements ActionListener{

    // Log in Card
    private JPanel pnlContainer = new JPanel();
    private JPanel pnlLogin = new JPanel(); // main login panel
    private JPanel pnlAtmMain = new JPanel(); // main atm panel
    private CardLayout card = new CardLayout(); // cardLayout
    private ImageIcon ecorpImg = new ImageIcon("C:\\Users\\Nate\\OneDrive\\school\\IUPUI\\Fall 2016\\CS240\\Final\\src\\img\\ECorp.png", "ECorp"); // little fancy logo
    private JTextField userID = new JTextField();
    private JPasswordField userPIN = new JPasswordField(4);
    private JButton btnLogin = new JButton("Log In →");


    // ATM Card
    private JButton btn1 = new JButton("1");
    private JButton btn2 = new JButton("2");
    private JButton btn3 = new JButton("3");
    private JButton btn4 = new JButton("4");
    private JButton btn5 = new JButton("5");
    private JButton btn6 = new JButton("6");
    private JButton btn7 = new JButton("7");
    private JButton btn8 = new JButton("8");
    private JButton btn9 = new JButton("9");
    private JButton btn0 = new JButton("0");
    private JButton btnCancel = new JButton("Cancel");
    private JButton btnClear = new JButton("Clear");
    private JButton btnEnter = new JButton("Enter");

    private JButton btnViewBal = new JButton("Balance");
    private JButton btnDeposit = new JButton("Deposit");
    private JButton btnWithdraw = new JButton("Withdraw");
    private JButton btnEmail = new JButton("Report");
    private JButton btnDonate = new JButton("Donate");
    private JButton btnLogOut = new JButton("Log out");
    private JTextArea screen = new JTextArea();
    private  JTextArea amount = new JTextArea();
    private JLabel lblDS = new JLabel("$");


    private ATM_CTRL atmCtrl;



    public ATM(){
        super("ECorp ATM"); // calling the super constructor to set the title of the window

        // Card container
            pnlContainer.setLayout(card);

        // Login panel
            pnlLogin.setLayout(null);

        // adding the Ecorp logo to the atm
        JLabel imgLabel = new JLabel("", ecorpImg, JLabel.CENTER);
            imgLabel.setBounds(150, 10, 200, 150);
        // userID Field
        JLabel lblUserID = new JLabel("User ID: ");
            lblUserID.setBounds(100, 175, 80, 25);
            userID.setBounds(180,175,160, 25);
        // userPIN Field
        JLabel lblUserPIN = new JLabel("User PIN: ");
            lblUserPIN.setBounds(100, 225, 80, 25);
            userPIN.setBounds(180,225,160, 25);
        // Login button
            btnLogin.setBounds(200,275,100, 25);
            btnLogin.addActionListener(this);


            pnlLogin.add(imgLabel);
            pnlLogin.add(lblUserID);
            pnlLogin.add(lblUserPIN);
            pnlLogin.add(userID);
            pnlLogin.add(userPIN);
            pnlLogin.add(btnLogin);
        // End login Panel

        // atmMain

            pnlAtmMain.setLayout(null);


            screen.setBounds(125, 20, 250, 180);
            amount.setBounds(175, 210, 200, 20);
            lblDS.setBounds(160, 210, 20, 20);
            btnViewBal.setBounds(15,50,100,30);
            btnDeposit.setBounds(15,100,100,30);
            btnWithdraw.setBounds(15,150,100,30);
            btnEmail.setBounds(385,50,100,30);
            btnDonate.setBounds(385,100,100,30);
            btnLogOut.setBounds(385,150,100,30);

        //screen.setBackground(Color.green);
        // First Row buttons
            btn1.setBounds(125,250,50,25);
            btn2.setBounds(185,250,50,25);
            btn3.setBounds(245,250,50,25);
            btnCancel.setBounds(300,250,75,25);
            btnCancel.setForeground(Color.RED);
        // Second Row buttons
            btn4.setBounds(125,280,50,25);
            btn5.setBounds(185,280,50,25);
            btn6.setBounds(245,280,50,25);
            btnClear.setBounds(300,280,75,25);
            btnClear.setForeground(Color.ORANGE);
        // Thrid Row buttons
            btn7.setBounds(125,310,50,25);
            btn8.setBounds(185,310,50,25);
            btn9.setBounds(245,310,50,25);
        // Fourth Row buttons
            btn0.setBounds(185,340,50,25);
            btnEnter.setBounds(300,340,75,25);
            btnEnter.setForeground(Color.GREEN);

            pnlAtmMain.add(screen);
            pnlAtmMain.add(amount);
            pnlAtmMain.add(lblDS);
            pnlAtmMain.add(btnViewBal);
            pnlAtmMain.add(btnDeposit);
            pnlAtmMain.add(btnWithdraw);
            pnlAtmMain.add(btnEmail);
            pnlAtmMain.add(btnDonate);
            pnlAtmMain.add(btnLogOut);
            pnlAtmMain.add(btn1);
            pnlAtmMain.add(btn2);
            pnlAtmMain.add(btn3);
            pnlAtmMain.add(btnCancel);
            pnlAtmMain.add(btn4);
            pnlAtmMain.add(btn5);
            pnlAtmMain.add(btn6);
            pnlAtmMain.add(btn7);
            pnlAtmMain.add(btnClear);
            pnlAtmMain.add(btn8);
            pnlAtmMain.add(btn9);
            pnlAtmMain.add(btn0);
            pnlAtmMain.add(btnEnter);


            pnlContainer.add(pnlLogin, "1");
            pnlContainer.add(pnlAtmMain, "2");
            card.show(pnlContainer, "1");


            btnLogOut.addActionListener(this);
            btnViewBal.addActionListener(this);
            btnDeposit.addActionListener(this);
            btnWithdraw.addActionListener(this);
            btnDonate.addActionListener(this);
            btnEmail.addActionListener(this);
            btnCancel.addActionListener(this);
            btnClear.addActionListener(this);
            btnEnter.addActionListener(this);
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            btn3.addActionListener(this);
            btn4.addActionListener(this);
            btn5.addActionListener(this);
            btn6.addActionListener(this);
            btn7.addActionListener(this);
            btn8.addActionListener(this);
            btn9.addActionListener(this);
            btn0.addActionListener(this);

            // adding to our main jframe
            this.add(pnlContainer);
            this.setResizable(false);
            this.setSize(500, 400); // setting the default size of the frame
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the application on exit
            this.setVisible(true);
            // new ATM_CTRL object
            this.atmCtrl = new ATM_CTRL();

    }

    // action listeners for our buttons
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnLogin){
            // pass the arguments of the ID and PIN to ATM_CTRL
            // Because our JTextField returns a string object, we need to convert it to Integer value.
            // The JPasswordField returns a character array, to convert a character array to a string. pass the array into the string object constructor
            int ID = Integer.parseInt(userID.getText());
            int PIN = Integer.parseInt(new String(userPIN.getPassword()));
            if(atmCtrl.userLogin(ID,PIN)){
                card.show(pnlContainer, "2");
                screen.setText("Hello: " + atmCtrl.getFormatedName()); // show welcome message
            }else{
                // clear the input
                userID.setText("");
                userPIN.setText("");
                screen.setText("");
                // display a very unhelpful error message
                JOptionPane.showMessageDialog(pnlContainer, "You did something wrong, what ever it was, don't do it again.");
            }// end login
        }else if(e.getSource() == btnLogOut){
            // clear the input
            userID.setText("");
            userPIN.setText("");
            atmCtrl.saveUser(); // save the user
            card.show(pnlContainer, "1"); // switch to the login panel
        }else if(e.getSource() == btn0){
            amount.setText(amount.getText() + "0");
        } else if(e.getSource() == btn1){
            amount.setText(amount.getText() + "1");
        }else if(e.getSource() == btn2){
            amount.setText(amount.getText() + "2");
        }else if(e.getSource() == btn3){
            amount.setText(amount.getText() + "3");
        }else if(e.getSource() == btn4){
            amount.setText(amount.getText() + "4");
        }else if(e.getSource() == btn5){
            amount.setText(amount.getText() + "5");
        }else if(e.getSource() == btn6){
            amount.setText(amount.getText() + "6");
        }else if(e.getSource() == btn7){
            amount.setText(amount.getText() + "7");
        }else if(e.getSource() == btn8){
            amount.setText(amount.getText() + "8");
        }else if(e.getSource() == btn9){
            amount.setText(amount.getText() + "9"); // event handlers for our key pad
        }else if(e.getSource() == btnViewBal){
            clearScree();
            screen.setText("Your Balance is: " + atmCtrl.getUserBalance());
        }else if(e.getSource() == btnDeposit){
            clearScree();
            screen.setText("Enter Amount to deposit:\n ");
            screen.append("(There is a standard $6 charge \nfor all transactions)");
            atmCtrl.setTask("deposit");
        }else if(e.getSource() == btnWithdraw){
            clearScree();
            screen.setText("Enter Amount to withdraw:\n ");
            screen.append("(There is a standard $6 charge \nfor all transactions)");
            atmCtrl.setTask("withdraw");
        }else if(e.getSource() == btnEmail){
            if(atmCtrl.emailUser()){
                atmCtrl.userWithdraw(this.atmCtrl.atmFee);
                clearScree();
                screen.setText("Transaction list have been emailed");
            }else {
                // error message if the email doesn't display properly
                JOptionPane.showMessageDialog(pnlContainer, "Hmm, it appears as though the email didn't send... Time to go flog the code monkeys");
            }
        }else if(e.getSource() == btnDonate){
            clearScree();
            screen.setText("Enter Amount to Donate:\n ");
            screen.append("(There is a standard $6 charge \nfor all transactions)");
            atmCtrl.setTask("donate");
        }else if(e.getSource() == btnClear){
            amount.setText(""); // clears the amount console
        }else if(e.getSource() == btnCancel){
            amount.setText(""); //
            screen.setText("Please select an action");
            atmCtrl.setTask("");// no current task
        }else if(e.getSource() == btnEnter){
            // Deposite Function
            if(atmCtrl.getTask() == "deposit") {
                double depositAmount = (double)(Integer.parseInt(amount.getText()) - atmCtrl.atmFee);
                atmCtrl.userDeposit(depositAmount);
                atmCtrl.addUserTransaction(atmCtrl.currentDate(), "Deposit", depositAmount, atmCtrl.getUserBalance());
                screen.setText("Deposited.\nYour new balance is: " + atmCtrl.getUserBalance());
            }else if(atmCtrl.getTask() == "withdraw") {
                double withdrawAmount = (double)(Integer.parseInt(amount.getText()) + atmCtrl.atmFee);
                if(atmCtrl.userWithdraw(withdrawAmount) != true){
                    JOptionPane.showMessageDialog(pnlContainer, "Hey, you don't have that much money. Stop being poor.");
                }else{
                    atmCtrl.addUserTransaction(atmCtrl.currentDate(), "Withdrawl", withdrawAmount, atmCtrl.getUserBalance());
                    screen.setText("Withdrawn.\nYour new balance is: " + atmCtrl.getUserBalance());
                    atmCtrl.setTask("");
                }

            }else if(atmCtrl.getTask() == "donate"){
                // yes, the standard $6 charage applies to donating to Ecorp....
                double donateAmount = (double)(Integer.parseInt(amount.getText()) + atmCtrl.atmFee);
                if(atmCtrl.userWithdraw(donateAmount) != true){
                    JOptionPane.showMessageDialog(pnlContainer, "Hey, you don't have that much money. Stop being poor.");
                }else{
                    atmCtrl.addUserTransaction(atmCtrl.currentDate(), "Donated", donateAmount, atmCtrl.getUserBalance());
                    screen.setText("Thank you for donating to E-Corp");
                    screen.setText("\nYour new balance is: " + atmCtrl.getUserBalance());
                    atmCtrl.setTask("");
                }
            }

            amount.setText("");
        }

    }

    // simple method to clear screen
    public void clearScree(){
        screen.setText("");
    }

    // Main method.
    public static void main(String[] args){
        new ATM(); // calling the ATM GUI
        System.out.println("Thank you for Choosing Ecorp.");
    }
}