package com.wordpress.grayfaces.freakingmath.app;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpress.grayfaces.freakingmath.R;

/**
 * Project FreakingMath
 * Created by Gray on 10/20/2017.
 */

public class QuitDialog extends DialogFragment {


    // interface to handle the dialog click back to the Activity
    public interface OnDialogFragmentClickListener {
        void onOkClicked(QuitDialog dialog);
        void onCancelClicked(QuitDialog dialog);
    }
    OnDialogFragmentClickListener mOnDialogFrmClickListener;
    public void setOnDialogFrmCilckListener(OnDialogFragmentClickListener listener){
        mOnDialogFrmClickListener = listener;
    }

    // Create a Dialog using default AlertDialog builder , if not inflate custom view in onCreateView
    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle(getArguments().getString("title"))
                .setMessage(getArguments().getString("message"))
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Positive button clicked
                                getActivityInstance().onOkClicked(dialogThemDanhMuc.this);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // negative button clicked
                                getActivityInstance().onCancelClicked(dialogThemDanhMuc.this);
                            }
                        }
                )
                .create();
    }*/
    //Create a dialog using custom layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_quit, container, false);
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        final View btnNo = view.findViewById(R.id.dialog_quit_txtNo);
        View btnYes = view.findViewById(R.id.dialog_quit_txtYes);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDialogFrmClickListener.onOkClicked(QuitDialog.this);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDialogFrmClickListener.onCancelClicked(QuitDialog.this);
            }
        });

        return view;
    }

}

