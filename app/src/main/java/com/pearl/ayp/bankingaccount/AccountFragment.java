package com.pearl.ayp.bankingaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nutdanai on 10/26/2016.
 */

public class AccountFragment extends Fragment {
    private static final String TAG = "AccountFragment";
    private TextView mTxtTotalMoney;

    private int tempTotalMoney;
    private int availableBalance;

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        mTxtTotalMoney = (TextView) view.findViewById(R.id.txt_account_inquiry_num);

        availableBalance = AccountSharedPref.getAvailableBalance(getActivity());
        mTxtTotalMoney.setText(Integer.toString(availableBalance) + "  THB");

        return view;
    }


}
