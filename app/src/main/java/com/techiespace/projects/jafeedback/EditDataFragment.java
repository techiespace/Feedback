package com.techiespace.projects.jafeedback;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.techiespace.projects.jafeedback.db.DescriptionDao;
import com.techiespace.projects.jafeedback.db.EmailDao;
import com.techiespace.projects.jafeedback.db.OrgDatabase;
import com.techiespace.projects.jafeedback.db.OrgListDao;
import com.techiespace.projects.jafeedback.db.PhoneDao;
import com.techiespace.projects.jafeedback.db.SocialDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


//TODO: A edit icon in the navbar which opens a new activity to edit all details on the page(with old values pre-populated)
public class EditDataFragment extends DialogFragment {
    private static final String ORG_ID = "orgId";
    private static final String FIELD_NAME = "fieldName";
    private static final String OLD_VAL = "oldVal";
    private static final Executor executor = Executors.newFixedThreadPool(2);
    private Context context;
    private String field;
    private int orgId;
    private String oldVal;

    public static EditDataFragment newInstance(int org, String fieldType, String oldVal) {
        EditDataFragment fragment = new EditDataFragment();
        Bundle args = new Bundle();
        args.putInt(ORG_ID, org);
        args.putString(FIELD_NAME, fieldType);
        args.putString(OLD_VAL, oldVal);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        orgId = args.getInt(ORG_ID);
        field = args.getString(FIELD_NAME);
        oldVal = args.getString(OLD_VAL);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstances) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//        https://www.bignerdranch.com/blog/understanding-androids-layoutinflater-inflate/
        @SuppressLint("InflateParams") View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit, null);
        final EditText valEditText = view.findViewById(R.id.etFiled);
        valEditText.setText(oldVal);
        valEditText.setSelection(0, oldVal.length());
        alertDialogBuilder.setView(view)
                .setTitle("Edit Field")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveEdit(valEditText.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return alertDialogBuilder.create();
    }

    private void saveEdit(final String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                switch (field) {
                    case "fb": {
                        final SocialDao socialDao = OrgDatabase.getDatabase(context).socialDao();
                        socialDao.updateFb(value, orgId);
                        break;
                    }
                    case "tw": {
                        final SocialDao socialDao = OrgDatabase.getDatabase(context).socialDao();
                        socialDao.updateTw(value, orgId);
                        break;
                    }
                    case "yt": {
                        final SocialDao socialDao = OrgDatabase.getDatabase(context).socialDao();   //will this optimise the code?
                        socialDao.updateYt(value, orgId);
                        break;
                    }
                    case "phone": {
                        final PhoneDao phoneDao = OrgDatabase.getDatabase(context).phoneDao();   //will this optimise the code?
                        final OrgListDao orgListDao = OrgDatabase.getDatabase(context).orgListDao();
                        orgListDao.updatePhone(value, orgId);
                        phoneDao.updatePhone(value, orgId);  //might fail when same org has multiple phones
                        break;
                    }
                    case "email": {
                        final EmailDao emailDao = OrgDatabase.getDatabase(context).emailDao();   //will this optimise the code?
                        final OrgListDao orgListDao = OrgDatabase.getDatabase(context).orgListDao();
                        orgListDao.updateEmail(value, orgId);
                        emailDao.updateEmail(value, orgId);  //might fail when same org has multiple emails
                        break;
                    }
                    case "web_link": {
                        final DescriptionDao descriptionDao = OrgDatabase.getDatabase(context).descriptionDao();   //will this optimise the code?
                        descriptionDao.updateWebLink(value, orgId);  //might fail when same org has multiple websites
                        break;
                    }
                }
            }
        });

    }
}
