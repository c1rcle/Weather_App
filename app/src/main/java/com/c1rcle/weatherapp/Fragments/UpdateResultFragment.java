package com.c1rcle.weatherapp.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.c1rcle.weatherapp.Activity.MainActivity;
import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.TinyDB;

public class UpdateResultFragment extends DialogFragment
{
    public static int PERMISSIONS_REQUEST = 0;

    private boolean updateStatus = false;

    private String version;

    private String description;

    public DialogInterface.OnClickListener approvedListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            TinyDB database = new TinyDB(getContext());
            database.putString("update_version", version);

            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                String[] permissions = new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE };
                getActivity().requestPermissions(permissions, PERMISSIONS_REQUEST);
            }
            else ((MainActivity) getActivity()).downloadApk();
        }
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        TextView updateDescription;

        if (updateStatus)
        {
            View view = inflater.inflate(R.layout.dialog_version, null);
            updateDescription = view.findViewById(R.id.updateDescription);
            TinyDB database = new TinyDB(getActivity());
            database.putString("version", version);

            Spanned text = Html.fromHtml(description);
            updateDescription.setText(text.subSequence(0, text.toString().trim().length()));
            builder.setView(view)
                    .setPositiveButton(R.string.dialog_version_download, approvedListener)
                    .setNegativeButton(R.string.dialog_version_cancel, null);

        }
        else
        {
            View view = inflater.inflate(R.layout.dialog_version, null);
            TextView updateTitle = view.findViewById(R.id.updateResult);
            updateTitle.setText(getString(R.string.dialog_version_title_up));
            updateDescription = view.findViewById(R.id.updateDescription);
            Spanned text = Html.fromHtml(description);
            updateDescription.setText(text.subSequence(0, text.toString().trim().length()));
            builder.setView(view)
                    .setNegativeButton(R.string.dialog_version_cancel, null);
        }
        return builder.create();
    }

    public void setUpdateStatus(boolean updateStatus)
    {
        this.updateStatus = updateStatus;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
