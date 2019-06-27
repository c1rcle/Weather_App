package com.c1rcle.weatherapp.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.c1rcle.weatherapp.R;

public class UpdateResultFragment extends DialogFragment
{
    private boolean updateStatus = false;

    private String version;

    private String description;

    private Uri path;

    public DialogInterface.OnClickListener approvedListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            Intent picker = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            picker.addCategory(Intent.CATEGORY_DEFAULT);
            startActivityForResult(Intent.createChooser(picker, getString(R.string.dialog_version_directory)), 0);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 0)
        {
            DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        }
    }

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
