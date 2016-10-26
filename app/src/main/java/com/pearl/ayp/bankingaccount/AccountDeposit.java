package com.pearl.ayp.bankingaccount;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Nutdanai on 10/26/2016.
 */

public class AccountDeposit extends Fragment implements View.OnClickListener,View.OnTouchListener  {
    private static final String TAG = "AccountDeposit";
    private int depositAmount;
    private int noteThousand;
    private int noteFiveHundred;
    private int noteHundred;
    private int tempTotalMoney;
    private Button mDeposit;
    private CheckBox mThousand;
    private CheckBox mFiveHundred;
    private CheckBox mHundred;

    private EditText editThousand;
    private EditText editFiveHundred;
    private EditText editHundred;
    private TextView mTxtDepositAmount;
    private TextView mTxtDepositAmountTypeMoney;



    public static AccountDeposit newInstance() {

        Bundle args = new Bundle();

        AccountDeposit fragment = new AccountDeposit();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deposit, container, false);


        mDeposit = (Button) view.findViewById(R.id.btn_deposit);
        mThousand = (CheckBox) view.findViewById(R.id.check_deposit_thousand);
        mFiveHundred = (CheckBox) view.findViewById(R.id.check_deposit_five_hundred);
        mHundred = (CheckBox) view.findViewById(R.id.check_deposit_hundred);

        editThousand = (EditText) view.findViewById(R.id.edit_deposit_amount_thousand);
        editFiveHundred = (EditText) view.findViewById(R.id.edit_deposit_amount_five_hundred);
        editHundred = (EditText) view.findViewById(R.id.edit_deposit_amount_hundred);

        mTxtDepositAmount = (TextView) view.findViewById(R.id.txt_deposit_amount);
        mTxtDepositAmountTypeMoney = (TextView) view.findViewById(R.id.txt_deposit_amount_type);

        mDeposit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_deposit:
                if (!editThousand.getText().toString().equals("")
                        || !editFiveHundred.getText().toString().equals("")
                        || !editHundred.getText().toString().equals("")) {
                    calculate();
                }else{
                    Log.d(TAG, "onClick: Please Check your amount of money");
                }
                break;
        }
    }

    public void calculate() {
        depositAmount = 0;
        if (mThousand.isChecked()) {
            noteThousand = Integer.valueOf(editThousand.getText().toString());
            depositAmount = depositAmount + (1000 * noteThousand);
            mThousand.toggle();
            editThousand.getText().clear();

        }
        if (mFiveHundred.isChecked()) {
            noteFiveHundred = Integer.valueOf(editFiveHundred.getText().toString());
            depositAmount = depositAmount + (500 * noteFiveHundred);
            mFiveHundred.toggle();
            editFiveHundred.getText().clear();
        }
        if (mHundred.isChecked()) {
            noteHundred = Integer.valueOf(editHundred.getText().toString());
            depositAmount = depositAmount + (100 * noteHundred);
            mHundred.toggle();
            editHundred.getText().clear();
        }


        tempTotalMoney = AccountSharedPref.getAvailableBalance(getActivity())+depositAmount;

        AccountSharedPref.setAvailableBalance(getActivity(),tempTotalMoney);
        AccountSharedPref.setDepositAmount(getActivity(),depositAmount);

        mTxtDepositAmountTypeMoney.setText("Thousand : " + noteThousand
                + "\nFive Hundred : " + noteFiveHundred
                + "\nHundred : "+noteHundred);
        mTxtDepositAmount.setText("DepositAmount : "+String.valueOf(depositAmount));

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return false;
    }
}
