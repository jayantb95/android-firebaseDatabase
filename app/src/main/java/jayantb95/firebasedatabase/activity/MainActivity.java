package jayantb95.firebasedatabase.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import jayantb95.firebasedatabase.R;
import jayantb95.firebasedatabase.adapter.RecyclerPersonAdapter;
import jayantb95.firebasedatabase.adapter.RecyclerTouchListener;
import jayantb95.firebasedatabase.dataModel.PersonModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FirebaseDatabase firebaseDB;
    DatabaseReference dbRef;

    private Button btnSubmit;

    private EditText edtAge;
    private EditText edtContact;
    private EditText edtName;

    private TextView txtAge;
    private TextView txtContact;
    private TextView txtName;

    private RecyclerView recyclerViewPerson;
    private List<PersonModel> personList = new ArrayList<>();

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

        recyclerViewPerson = findViewById(R.id.recycler_view_person);

        listener();
        attemptGetData();
    }

    private void listener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptPushData();
            }
        });

        recyclerViewPerson.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this,
                recyclerViewPerson, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "item clicked: " + position);
                Log.d(TAG, "clicked contact: " + personList.get(position).getContact());
                String contact = personList.get(position).getContact();
                attemptDeleteData(contact);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void attemptPushData() {
        PersonModel person = new PersonModel();
        String age = edtAge.getText().toString().trim();
        String contact = edtContact.getText().toString().trim();
        String name = edtName.getText().toString().trim();

        if (age.isEmpty()) {
            person.setAge("no age specified");
        }
        if (contact.isEmpty()) {
            person.setContact("no contact specified");
        }
        if (name.isEmpty()) {
            person.setName("no name specified");
        }
        person.setAge(age);
        person.setContact(contact);
        person.setName(name);

        dbRef.push().setValue(person);
    }

    private void attemptGetData() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                personList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    personList.add(new PersonModel(ds.child("age").getValue(String.class),
                            ds.child("contact").getValue(String.class),
                            ds.child("name").getValue(String.class))
                    );
                }
                if (personList.size() > 0) {
                    recyclerViewPerson.setHasFixedSize(false);
                    recyclerViewPerson.setAdapter(new RecyclerPersonAdapter(personList));
                    recyclerViewPerson.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewPerson.setItemAnimator(new DefaultItemAnimator());

                } else {
                    Toast.makeText(MainActivity.this, "no data saved previously",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void attemptDeleteData(String contact) {
        Query deleteQuery = dbRef.orderByChild("contact").equalTo(contact);
        Log.d(TAG, deleteQuery.toString());
        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot deleteSnapshot : dataSnapshot.getChildren()) {
                    deleteSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
