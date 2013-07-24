package com.csc202.androidcalculator;

import infix.InFixEvaluator;
import postfix.PostFixEvaluator;
import prefix.PreFixEvaluator;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	Button plusBtn, minusBtn, multiplyBtn, divideBtn, sevenBtn, eightBtn,
			nineBtn, openBtn, fourBtn, fiveBtn, sixBtn, closeBtn, oneBtn,
			twoBtn, threeBtn, spaceBtn, zeroBtn, clearBtn, dotBtn, equalsBtn;
    EditText etInput;
    String expression="0";
    double fResult =0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialMethod();
//        computeIt();
    }

    private void initialMethod() {
    	plusBtn = (Button) findViewById(R.id.plusBtn);
    	plusBtn.setOnClickListener(this);
    	minusBtn = (Button) findViewById(R.id.minusBtn);
    	minusBtn.setOnClickListener(this);
    	multiplyBtn = (Button) findViewById(R.id.multiplyBtn);
    	multiplyBtn.setOnClickListener(this);
    	divideBtn = (Button) findViewById(R.id.divideBtn);
    	divideBtn.setOnClickListener(this);
    	sevenBtn = (Button) findViewById(R.id.sevenBtn);
    	sevenBtn.setOnClickListener(this);
    	eightBtn = (Button) findViewById(R.id.eightBtn);
    	eightBtn.setOnClickListener(this);
    	nineBtn = (Button) findViewById(R.id.nineBtn);
    	nineBtn.setOnClickListener(this);
    	openBtn = (Button) findViewById(R.id.openBtn);
    	openBtn.setOnClickListener(this);
    	fourBtn = (Button) findViewById(R.id.fourBtn);
    	fourBtn.setOnClickListener(this);
    	fiveBtn = (Button) findViewById(R.id.fiveBtn);
    	fiveBtn.setOnClickListener(this);
    	sixBtn = (Button) findViewById(R.id.sixBtn);
    	sixBtn.setOnClickListener(this);
    	closeBtn = (Button) findViewById(R.id.closeBtn);
    	closeBtn.setOnClickListener(this);
    	oneBtn = (Button) findViewById(R.id.oneBtn);
    	oneBtn.setOnClickListener(this);
    	twoBtn = (Button) findViewById(R.id.twoBtn);
    	twoBtn.setOnClickListener(this);
    	threeBtn = (Button) findViewById(R.id.threeBtn);
    	threeBtn.setOnClickListener(this);
    	spaceBtn = (Button) findViewById(R.id.spaceBtn);
    	spaceBtn.setOnClickListener(this);
    	zeroBtn = (Button) findViewById(R.id.zeroBtn);
    	zeroBtn.setOnClickListener(this);
    	clearBtn = (Button) findViewById(R.id.clearBtn);
    	clearBtn.setOnClickListener(this);
    	dotBtn = (Button) findViewById(R.id.dotBtn);
    	dotBtn.setOnClickListener(this);
    	equalsBtn = (Button) findViewById(R.id.equalsBtn);
    	equalsBtn.setOnClickListener(this);
        etInput = (EditText) findViewById(R.id.etInput);
        etInput.setText("0");
        etInput.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform compute
                	compute();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.oneBtn:
			case R.id.twoBtn:
			case R.id.threeBtn:
			case R.id.fourBtn:
			case R.id.fiveBtn:
			case R.id.sixBtn:
			case R.id.sevenBtn:
			case R.id.eightBtn:
			case R.id.nineBtn:
			case R.id.zeroBtn:
			case R.id.dotBtn:
			case R.id.spaceBtn:
			case R.id.plusBtn:
			case R.id.minusBtn:
			case R.id.multiplyBtn:
			case R.id.divideBtn:
			case R.id.openBtn:
			case R.id.closeBtn:

				String tempExpression;
				if (((Button)v).getText().toString().equals("_")){
					tempExpression = " ";
				} else {
					tempExpression = ((Button)v).getText().toString();
					System.out.println(tempExpression);
					System.out.println(etInput.getText());
				}
	            if(((etInput.getText().equals("0"))&&(!tempExpression.equals(".")))||(etInput.getText().equals(""+fResult))){
	            	etInput.setText(tempExpression);  // no leading zero
	            }else{
	            	etInput.setText(etInput.getText()+tempExpression);  // accumulate input digit
	            }
                break;        
            //Operator Button
            case R.id.equalsBtn: // Equals button is hit
				compute();
                break;    
            case R.id.clearBtn: //Clear button is hit
            	fResult=0;
                expression="0";
                etInput.setText("0");
                break;                
        }
    }
    
    
    private void compute() {
		try {
			// Precondition, must be in either postfix, prefix, or infix
			int indexSpace = etInput.getText().toString().indexOf(' ') + 1;
			if ((etInput.getText().toString().contains(")") || ((etInput
					.getText().toString().substring(0, 1).matches("[0-9]")) && (!(etInput
					.getText().toString().substring(indexSpace, indexSpace + 1)
					.matches("[0-9]")))))) {
				fResult = Double.parseDouble(InFixEvaluator.evaluate(etInput
						.getText().toString())); // InFix calculator
			} else if (etInput.getText().toString().substring(0, 1)
					.matches("[0-9]")) {
				fResult = PostFixEvaluator.evaluate(etInput.getText()
						.toString()); // PostFix Calculator
			} else
				fResult = PreFixEvaluator
						.evaluate(etInput.getText().toString()); // PreFix Calculator

			// Output result.
			System.out.println();
			System.out.println("Result = " + fResult);
			etInput.setText("" + fResult);

		} catch (Exception error) {
			// Output error message.
			String errMessage = error.getMessage();
			etInput.setText("Error: " + errMessage);
		}
		expression = "0";
    }    

    //DONT THINK I NEED THIS EITHER
//    private void computeIt() {
//        int iNum = Integer.parseInt(expression);
//        expression="0";
//        if(myOperator==' '){
//            fResult = iNum;
//        }else if(myOperator=='+'){
//            fResult+=iNum;
//        }else if(myOperator=='-'){
//            fResult-=iNum;
//        }else if(myOperator=='*'){
//            fResult*=iNum;
//        }else if(myOperator=='/'){
//            fResult/=iNum;
//        }else if(myOperator=='='){
//
//        }
//        
//        etInput.setText(""+fResult);
//    }
}
