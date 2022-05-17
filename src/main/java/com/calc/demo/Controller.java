package com.calc.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class Controller {

    @FXML private Button btnDivide;
    @FXML private Button btnMultiply;
    @FXML private Button btnSubtract;
    @FXML private Button btnAddition;
    @FXML private Button btnCalculate;
    @FXML private Button btnClear;
    @FXML private Button btnNegate;
    @FXML private Button btnPercent;
    @FXML private Button btnSeven;
    @FXML private Button btnEight;
    @FXML private Button btnNine;
    @FXML private Button btnSix;
    @FXML private Button btnFive;
    @FXML private Button btnFour;
    @FXML private Button btnThree;
    @FXML private Button btnTwo;
    @FXML private Button btnOne;
    @FXML private Button btnDecimal;
    @FXML private Button btnZero;
    @FXML private TextField txtInput;
    // values for holding numbers
    private double number1;
    private double number2;
    private double number3;
    // placeholder for holding calculated output
    private String placeHolder = "";
    // value for switch statement
    private int caseValue=0;
    // decimal formatters for two decimal places or none
    private static DecimalFormat df1 = new DecimalFormat("#.##");
    private static DecimalFormat df2 = new DecimalFormat("#");


    // event listener for any numeric value buttons 0-9 & "."
    @FXML void buttonAction(ActionEvent event) {
        // method call for display passing object button pressed
        display(event.getSource());
    }// end of buttonAction

    // event listener for AC, %, or +/- buttons
    @FXML void buttonManipulation(ActionEvent event) {
        // method call for btnManipulate passing object button pressed
        btnManipulate(event.getSource());
    }// buttonManipulation

    // event listener for +, -, /, X, or = buttons
    @FXML void buttonOperator(ActionEvent event) {
        // method call for btnOperators passing object button pressed
        extendedOperators(event.getSource());
    }// end of buttonOperator

    //-----------------------------------------------------------------------------------
    // this method sets the txtInput equal to numbers the user types in by concat them
    // with what is already given in the txtInput field
    public void display(Object source){
        if(placeHolder != ""){placeHolder = ""; txtInput.setText("");}
        // if button pressed is button btnOne set text equal to text concat 1
        if(source.equals(btnOne)){txtInput.setText(txtInput.getText() + "1"); }
        // if button pressed is button btnTwo set text equal to text concat 2
        else if(source.equals(btnTwo)){txtInput.setText(txtInput.getText() + "2"); }
        // if button pressed is button btnThree set text equal to text concat 3
        else if(source.equals(btnThree)){txtInput.setText(txtInput.getText() + "3"); }
        // if button pressed is button btnFour set text equal to text concat 4
        else if(source.equals(btnFour)){txtInput.setText(txtInput.getText() + "4"); }
        // if button pressed is button btnFive set text equal to text concat 5
        else if(source.equals(btnFive)){txtInput.setText(txtInput.getText() + "5"); }
        // if button pressed is button btnSix set text equal to text concat 6
        else if(source.equals(btnSix)){txtInput.setText(txtInput.getText() + "6"); }
        // if button pressed is button btnSeven set text equal to text concat 7
        else if(source.equals(btnSeven)){txtInput.setText(txtInput.getText() + "7"); }
        // if button pressed is button btnEight set text equal to text concat 8
        else if(source.equals(btnEight)){txtInput.setText(txtInput.getText() + "8"); }
        // if button pressed is button btnNine set text equal to text concat 9
        else if(source.equals(btnNine)){txtInput.setText(txtInput.getText() + "9"); }
        // if button pressed is button btnZero set text equal to text concat 0
        else if(source.equals(btnZero)){txtInput.setText(txtInput.getText() + "0"); }
        // if button pressed is button btnDecimal set text equal to text concat "."
        else if(source.equals(btnDecimal)){txtInput.setText(txtInput.getText() + "."); }

    }// end of method display
    //-----------------------------------------------------------------------------------
    //Method extendedOperators checks if another operator like +, -, /, X was already
    // used by checking the case value.
    public void extendedOperators(Object source){
        // checks if another operator has been used previously without hitting AC
        if(caseValue != 0 && source != btnCalculate){
            // calculate output before moving onto next input.
            caseChoice(caseValue);
        }
        btnOperators(source);
    }// end of extendedOperators
    //-----------------------------------------------------------------------------------
    public void btnOperators(Object source){
        // checks if button pressed is the Calculate "=" button, if not call valueOne() method.
        if(source != btnCalculate){ valueOne();}

        // if button pressed is button btnAddition
        if(source.equals(btnAddition)){ caseValue = 1;/* set caseValue */ }

        // if button pressed is button btnSubtract "-"
        else if(source.equals(btnSubtract)){
            // if the value of txtInput equal nothing add - to value of txtInput
            if(txtInput.getText().equals("")){
                //adds "-" to string txtInput for negative numbers
                txtInput.setText(txtInput.getText() + "-");
            }
            else {caseValue = 2;/* set caseValue */}
        }
        // if button pressed is button btnMultiply "X"
        else if(source.equals(btnMultiply)){caseValue = 3;/* set caseValue */ }

        // if button pressed is button btnDivide "/"
        else if(source.equals(btnDivide)){caseValue =4; /* set caseValue */ }

        // if button pressed is button btnCalculate "="
        else if(source.equals(btnCalculate)){
            // Method call for caseChoice passing value caseValue
            caseChoice(caseValue);
            caseValue = 0;
        }

    }//end of method btnOperators

    //-----------------------------------------------------------------------------------
    // if the button pressed is equal to btnNegate "+/-", btnClear "AC" or btnPercent "%" do something
    public void btnManipulate(Object source){
        // if button pressed is button btnNegate
        if(source.equals(btnNegate)){
            // Method call for negate()
            negate();
        }
        // if button pressed is button btnClear "AC" set text equal to empty and reset all values to 0
        else if(source.equals(btnClear)){
            txtInput.setText("");
            number1 = 0;
            number2 = 0;
            number3 = 0;
            caseValue = 0;
        }
        // if button pressed is button btnPercent "%"
        else if(source.equals(btnPercent)){
            numberToPercent(); // Method call
        }
    }// end of btnManipulate

    //-----------------------------------------------------------------------------------
    // this method takes in the value caseValue and uses a enhanced switch statement
    // to switch based on the value of caseValue
    public void caseChoice(int caseValue){
        // parsing txtInput into number2
        number2 = numberParse();
        switch (caseValue) {
            // if case caseValue equal 1 call method addition()
            case 1 -> addition();
            // if case caseValue equal 2 call method subtraction()
            case 2 -> subtraction();
            // if case caseValue equal 3 call method multiplication()
            case 3 -> multiplication();
            // if case caseValue equal 4 call method division()
            case 4 -> division();
        }// end of switch

    }// end of caseChoice

    //-----------------------------------------------------------------------------------
    // Method addition simply adds number1 bto number2 stores value into number3
    // parses number3 into string and stores parsed value into txtInput
    public void addition(){
        number3 = number1 + number2;
        outputFormat(number3);
    }//end of method addition

    //-----------------------------------------------------------------------------------
    // Method subtraction simply subtracts number1 by number2 stores value into number3
    // parses number3 into string and stores parsed value into txtInput
    public void subtraction(){
        number3 = number1 - number2;
        outputFormat(number3);
    }// end of method subtractions

    //-----------------------------------------------------------------------------------
    // Method multiplication simply multiplies number1 by number2 stores value into number3
    // parses number3 into string and stores parsed value into txtInput
    public void multiplication(){
        number3 = number1 * number2;
        outputFormat(number3);
    }// end of method multiplication

    //-----------------------------------------------------------------------------------
    // Method division simply divides number1 by number2 stores value into number3
    // parses number3 into string and stores parsed value into txtInput
    public void division(){
        number3 = number1 / number2;
        outputFormat(number3);
    }

    //-----------------------------------------------------------------------------------
    // takes the number and makes it a percentage
    public void numberToPercent(){
        // parsing value of txtInput to temp1
        double temp1 = numberParse();
        // dividing temp1 by 100 to get percentage value of number
        temp1 = temp1/100;
        // parsing temp1 to string and setting to txtInput
        txtInput.setText(stringParse(temp1));
    }// end of numberToPercent()

    //-----------------------------------------------------------------------------------
    // this method parses value of txtInput into double multiplies value by -1, parses that
    // value into a string then saves it to txtInput to display.
    public void negate(){
        // parsing value of txtInput to temp1
        double temp1 = numberParse();
        // multiplying temp1 by -1
        temp1 = temp1 * -1;
        // parsing temp1 to string and setting to txtInput
        //txtInput.setText(stringParse(temp1));
        outputFormat(temp1);
    }// end of negate()

    //-----------------------------------------------------------------------------------
    // This method parses any TextField passed to and returns it as a double
    public double numberParse(){
        //parses the value of txtInput into a double and returns it
        return Double.parseDouble(txtInput.getText());
    }// end of method numberParse

    //-----------------------------------------------------------------------------------
    // this method simply takes a double, makes it a string and returns it
    public String stringParse(double number){
        // parse the value of number to a string
        return Double.toString(number);
    }//end of stringParse

    //-----------------------------------------------------------------------------------
    // This method outputFormat formats the output for two decimal places if its a decimal
    // if the number is a whole number then output is not presented as a decimal.
    public void outputFormat(double number){

        // if value is not a decimal format to show nothing but whole number using DecimalFormatter df2
        if (number % 1 ==0){ placeHolder = df2.format(number); }
        // value is a decimal and is only shown to 2 decimal places using DecimalFormatter df1
        else{placeHolder = df1.format(number); }
        // set the value of txtInput
        txtInput.setText(placeHolder);
    }// end of outputFormat

    //-----------------------------------------------------------------------------------
    // Method valueOne simply takes the txtInput value parses the number then clears
    // the value of txtInput.
    public void valueOne(){
        placeHolder = txtInput.getText();
        // Method call to parse the number in the txtInput and store in value
        number1 = numberParse();
        // clear the value stores in txtInput
        txtInput.setText(placeHolder);
    }// end of valueOne
}// end of Controller