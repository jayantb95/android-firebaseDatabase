package jayantb95.firebasedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
    }

    private void initialize() {
        btnSubmit = findViewById(R.id.btn_submit);

        edtAge = findViewById(R.id.edt_age);
        edtContact = findViewById(R.id.edt_contact);
        edtName = findViewById(R.id.edt_name);
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

    }
}
