package com.example.mtking.diolog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mtking.R;
import com.example.mtking.activity.GioHangActivity;
import com.example.mtking.activity.product_detailsActivity;
import com.example.mtking.object.DetailDrinks;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogThemTP extends AppCompatDialogFragment{
    private dialogThemTP listenner;
    String keyDh;
    View view;
    Button btThem;
    DatabaseReference reference;
    RatingBar ratingBar;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_themthanhphan, null);
        builder.setView(view);
        Bundle bundle = getArguments();
       /* keyDh = bundle.getString("keydh");*/
       /* Log.e("thien","thien" );*/
      reference = FirebaseDatabase.getInstance().getReference("DetailDrinks").child(keyDh);
        btThem = view.findViewById(R.id.btThem);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), product_detailsActivity.class);
                /*intent.putExtra("thien","thien");*/
                /*reference.child("TenCT").setValue((ratingBar.getRating()));
                reference.child("Giatien").setValue((ratingBar.getRating()));*/
                /*listenner.themTP();*/
                dismiss();
                startActivity(intent);
            }

        });
        return builder.create();
    }


    public interface dialogThemTP {
        void themTP  ();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listenner = (dialogThemTP) context;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
            +"chỉ implement DialogXacNhanListener");

        }
    }
}
