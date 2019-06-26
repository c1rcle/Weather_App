package com.c1rcle.weatherapp.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.c1rcle.weatherapp.R;

public class UpdateResultFragment extends DialogFragment
{
    private boolean updateStatus = false;

    private DialogInterface.OnClickListener cancelledListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {

        }
    };

    public DialogInterface.OnClickListener approvedListener = new DialogInterface.OnClickListener()
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

        if (updateStatus)
        {
            builder.setView(inflater.inflate(R.layout.dialog_version, null))
                    .setPositiveButton(R.string.dialog_version_download, approvedListener)
                    .setNegativeButton(R.string.dialog_version_cancel, cancelledListener);

        }
        else
        {
            builder.setView(inflater.inflate(R.layout.dialog_version, null))
                    .setNegativeButton(R.string.dialog_version_cancel, cancelledListener);
        }

        return builder.create();
    }

    public void setUpdateStatus(boolean updateStatus)
    {
        this.updateStatus = updateStatus;
    }
}
