package com.example.tarika.myfirebase;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public void saveRecord(View view) {
        EditText inputUser = (EditText) findViewById(R.id.et_user);
        EditText inputPass = (EditText) findViewById(R.id.et_password);

        String stringUser = inputUser.getText().toString();
        String stringPass = inputPass.getText().toString();

        if(stringUser.isEmpty() || stringPass.isEmpty()){
            Toast.makeText(MainActivity.this,"555",Toast.LENGTH_LONG).show();
            return;
        }

        DatabaseReference mNameRef = mRootRef.child("Username").child("user");
        DatabaseReference mMessageRef = mRootRef.child("Password").child("pass");
        mNameRef.setValue(stringUser);
        mMessageRef.setValue(stringPass);


    }

    public void disPlay(View view){
        final TextView showUser = (TextView)findViewById(R.id.tv_display);

        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strUser = dataSnapshot.child("Username").child("user").getValue().toString();
                String strPass = dataSnapshot.child("Password").child("pass").getValue().toString();

                showUser.setText( "user =" + strUser + "---" + "pass =" + strPass);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



}
