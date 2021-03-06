package com.example.depresol;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Activity_settings extends Fragment {
    FirebaseAuth mFirebaseAuth;
    TextView log_out , change_data;
    File myInternalFile;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_settings,container , false);
        ContextWrapper contextWrapper = new ContextWrapper(requireActivity().getApplicationContext());
        String filepath = "Super_mystery_folder";
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        String filename = "Storage.txt";
        myInternalFile = new File(directory, filename);

        mFirebaseAuth = FirebaseAuth.getInstance();

        log_out = view.findViewById(R.id.log_out);
        change_data = view.findViewById(R.id.thaydoithongtin);
        change_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent);

            }
        });
        log_out.setOnClickListener(v -> {
            mFirebaseAuth.signOut();
            try {
                String data = "0";
                FileOutputStream fos = new FileOutputStream(myInternalFile);
                fos.write(data.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(requireActivity().getApplication(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        return view;
    }
}