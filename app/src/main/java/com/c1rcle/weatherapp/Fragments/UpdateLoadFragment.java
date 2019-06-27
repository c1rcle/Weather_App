package com.c1rcle.weatherapp.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.c1rcle.weatherapp.BuildConfig;
import com.c1rcle.weatherapp.Listeners.UpdateListener;
import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Update.UpdateTask;

import java.util.Locale;

public class UpdateLoadFragment extends DialogFragment implements UpdateListener
{
    private DialogInterface.OnClickListener cancelledListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {

            UpdateResultFragment resultFragment = new UpdateResultFragment();
            resultFragment.setUpdateStatus(true);
            resultFragment.show(getActivity().getFragmentManager(), "result");
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

    @Override
    public void onStart()
    {
        super.onStart();
        UpdateTask task = new UpdateTask(Locale.getDefault().getLanguage(), this);
        task.execute(getString(R.string.api_url));
    }

    @Override
    public void onUpdateCompleted(String version, String description, String status)
    {
        this.dismiss();
        switch (status)
        {
            case "correct":
                UpdateResultFragment resultFragment = new UpdateResultFragment();
                resultFragment.setUpdateStatus(!BuildConfig.VERSION_NAME.equals(version));
                resultFragment.setVersion(version);
                resultFragment.setDescription(description);
                resultFragment.show(getFragmentManager(), "result");
                break;

            case "corrupt":
                Toast.makeText(getActivity(), R.string.error_corrupted, Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getActivity(), R.string.error_data, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
