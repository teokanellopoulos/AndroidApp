package com.example.eoe;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class Appointment_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointment_fragment,container,false);
        EditText fullname, email, amka, phone, email2, amka2, phone2;
        Button insert, update, delete, see;
        DBHelper dbHelper;
        ConstraintLayout insertForm = (ConstraintLayout) view.findViewById(R.id.insertForm);
        ConstraintLayout updateDeleteForm = (ConstraintLayout) view.findViewById(R.id.updateDeleteForm);

        fullname = (EditText) view.findViewById(R.id.fullname);
        email = (EditText) view.findViewById(R.id.email);
        amka = (EditText) view.findViewById(R.id.amka);
        phone = (EditText) view.findViewById(R.id.phone);
        email2 = (EditText) view.findViewById(R.id.email2);
        amka2 = (EditText) view.findViewById(R.id.amka2);
        phone2 = (EditText) view.findViewById(R.id.phone2);

        insert = view.findViewById(R.id.appoint);
        update = view.findViewById(R.id.update);
        delete = view.findViewById(R.id.delete);
        see = view.findViewById(R.id.view);


        dbHelper = new DBHelper(getActivity());
        Cursor cursor = dbHelper.getData();

        //an uparxoun dedomena na emfanistoun ta pedia enhmerwshs diagrafhs ktl
        if(cursor.getCount() > 0){
            insertForm.setVisibility(View.GONE);
            updateDeleteForm.setVisibility(View.VISIBLE);
        }else {
            //an oxi na emfanistoun ths eisagwghs
            insertForm.setVisibility(View.VISIBLE);
            updateDeleteForm.setVisibility(View.GONE);
        }


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full = fullname.getText().toString();
                String em = email.getText().toString();
                String am = amka.getText().toString();
                String ph = phone.getText().toString();

                if(full.isEmpty() || em.isEmpty() || am.isEmpty() || ph.isEmpty()){
                    Toast.makeText(getActivity(),R.string.toast4, Toast.LENGTH_SHORT).show();
                }else{
                    Boolean insertData = dbHelper.insert(full,am,em,ph);

                    if(insertData == true){
                        fullname.getText().clear();
                        amka.getText().clear();
                        email.getText().clear();
                        phone.getText().clear();

                        insertForm.setVisibility(View.GONE);
                        updateDeleteForm.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(),R.string.toast1, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor2 = dbHelper.getData();
                String fullname2 = "";

                if(amka2.getText().toString().isEmpty() || email2.getText().toString().isEmpty() || phone2.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),R.string.toast4, Toast.LENGTH_SHORT).show();
                }else {
                    if(cursor2.moveToFirst()){
                        fullname2 = cursor2.getString(cursor2.getColumnIndex("fullname"));
                    }

                    dbHelper.update(fullname2, amka2.getText().toString(),email2.getText().toString(), phone2.getText().toString());

                    amka2.getText().clear();
                    email2.getText().clear();
                    phone2.getText().clear();

                    Toast.makeText(getActivity(),R.string.toast2, Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete();
                insertForm.setVisibility(View.VISIBLE);
                updateDeleteForm.setVisibility(View.GONE);
                Toast.makeText(getActivity(),R.string.toast3, Toast.LENGTH_SHORT).show();
            }
        });

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = dbHelper.getData();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle(getString(R.string.appoin_data));
                String info = "";

                if(cursor.moveToFirst()){
                     info = getString(R.string.fullname) + ": " + cursor.getString(cursor.getColumnIndex("fullname"))
                     + "\n" + "AMKA: " + cursor.getString(cursor.getColumnIndex("amka"))
                     + "\n" + "Email: " + cursor.getString(cursor.getColumnIndex("email"))
                     + "\n" + getString(R.string.phone) + ": " + cursor.getString(cursor.getColumnIndex("phone"));
                     builder.setMessage(info);
                }

                builder.show();

            }
        });

        return view;
    }
}
