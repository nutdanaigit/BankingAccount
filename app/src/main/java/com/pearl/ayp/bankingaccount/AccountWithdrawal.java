package com.pearl.ayp.bankingaccount;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nutdanai on 10/26/2016.
 */

public class AccountWithdrawal extends Fragment implements View.OnClickListener {
    private static final String TAG = "AccountWithdrawal";
    private int withdrawalAmount;
    private int noteThousand;
    private int noteFiveHundred;
    private int noteHundred;
    private int tempTotalMoney;
    private Button mWithdrawal;
    private CheckBox mThousand;
    private CheckBox mFiveHundred;
    private CheckBox mHundred;

    private EditText editThousand;
    private EditText editFiveHundred;
    private EditText editHundred;
    private TextView mTxtWithdrawalAmount;
    private TextView mTxtWithdrawalAmountTypeMoney;

    public static AccountWithdrawal newInstance() {

        Bundle args = new Bundle();

        AccountWithdrawal fragment = new AccountWithdrawal();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_withdrawal,container,false);
        mWithdrawal = (Button) view.findViewById(R.id.btn_withdrawal);
        mThousand = (CheckBox) view.findViewById(R.id.check_withdrawal_thousand);
        mFiveHundred = (CheckBox) view.findViewById(R.id.check_withdrawal_five_hundred);
        mHundred = (CheckBox) view.findViewById(R.id.check_withdrawal_hundred);

        editThousand = (EditText) view.findViewById(R.id.edit_withdrawal_amount_thousand);
        editFiveHundred = (EditText) view.findViewById(R.id.edit_withdrawal_amount_five_hundred);
        editHundred = (EditText) view.findViewById(R.id.edit_withdrawal_amount_hundred);

        mTxtWithdrawalAmount = (TextView) view.findViewById(R.id.txt_withdrawal_amount_money);
        mTxtWithdrawalAmountTypeMoney = (TextView) view.findViewById(R.id.txt_withdrawal_amount_type);

        mWithdrawal.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_withdrawal:
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setCancelable(true);
                final EditText txtPin = (EditText) dialog.findViewById(R.id.edit_pin);
                TextView txtCancel = (TextView) dialog.findViewById(R.id.txt_cancel);
                TextView txtEnter = (TextView) dialog.findViewById(R.id.txt_enter);

                txtCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity()
                                , "Close dialog", Toast.LENGTH_SHORT);
                        dialog.cancel();
                    }
                });
                txtEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(txtPin.getText().toString().equals("1234")) {
                            if (!editThousand.getText().toString().equals("")
                                    || !editFiveHundred.getText().toString().equals("")
                                    || !editHundred.getText().toString().equals("")) {
                                calculate();
                                dialog.cancel();
                            } else {
                                Log.d(TAG, "onClick: Please Check your amount of money");
                                dialog.cancel();
                            }
                        }else{
                            mTxtWithdrawalAmount.setText("PIN WRONG !!!");
                            dialog.cancel();
                        }
                    }
                });
                dialog.show();
                break;
        }
    }

    public void calculate() {
        withdrawalAmount = 0;
        if (mThousand.isChecked()) {
            noteThousand = Integer.valueOf(editThousand.getText().toString());
            withdrawalAmount = withdrawalAmount + (1000 * noteThousand);
            mThousand.toggle();
            editThousand.getText().clear();

        }
        if (mFiveHundred.isChecked()) {
            noteFiveHundred = Integer.valueOf(editFiveHundred.getText().toString());
            withdrawalAmount = withdrawalAmount + (500 * noteFiveHundred);
            mFiveHundred.toggle();
            editFiveHundred.getText().clear();
        }
        if (mHundred.isChecked()) {
            noteHundred = Integer.valueOf(editHundred.getText().toString());
            withdrawalAmount = withdrawalAmount + (100 * noteHundred);
            mHundred.toggle();
            editHundred.getText().clear();
        }

        if(AccountSharedPref.getAvailableBalance(getActivity())>= withdrawalAmount ) {
            tempTotalMoney = AccountSharedPref.getAvailableBalance(getActivity()) - withdrawalAmount;
            AccountSharedPref.setAvailableBalance(getActivity(),tempTotalMoney);

            mTxtWithdrawalAmountTypeMoney.setText("Thousand : " + noteThousand
                    + "\nFive Hundred : " + noteFiveHundred
                    + "\nHundred : "+noteHundred);
            mTxtWithdrawalAmount.setText("DepositAmount : "+String.valueOf(withdrawalAmount));
        }else{
            mTxtWithdrawalAmount.setText("Available balance not enough");
        }

    }
}
