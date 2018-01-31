package jayantb95.firebasedatabase.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import jayantb95.firebasedatabase.R;
import jayantb95.firebasedatabase.dataModel.PersonModel;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDB;
    DatabaseReference dbRef;

    private Button btnSubmit;

    private EditText edtAge;
    private EditText edtContact;
    private EditText edtName;

    private TextView txtAge;
    private TextView txtContact;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        firebaseDB = FirebaseDatabase.getInstance();
        dbRef = firebaseDB.getReference("FirebaseDatabaseExample");

        btnSubmit = findViewById(R.id.btn_submit);

        edtAge = findViewById(R.id.edt_age);
        edtContact = findViewById(R.id.edt_contact);
        edtName = findViewById(R.id.edt_name);

        listener();
        getData();
    }

    private void listener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptPushData();
            }
        });
    }

    private void attemptPushData() {
        PersonModel person = new PersonModel(edtAge.getText().toString(),
                edtContact.getText().toString(),
                edtName.getText().toString());
        dbRef.push().setValue(person);

    }

    private void getData() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    PersonModel person = new PersonModel();
                    person.setAge(ds.child("age").getValue(String.class));
                    person.setContact(ds.child("contact").getValue(String.class));
                    person.setName(ds.child("name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
