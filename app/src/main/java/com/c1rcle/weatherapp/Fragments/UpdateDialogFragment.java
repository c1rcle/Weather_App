package com.c1rcle.weatherapp.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.c1rcle.weatherapp.R;

public class UpdateDialogFragment extends DialogFragment
{
    private DialogInterface.OnClickListener cancelledListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {

        }
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_loading, null))
                .setNegativeButton(R.string.dialog_loading_button, cancelledListener);


        return builder.create();
    }
}
