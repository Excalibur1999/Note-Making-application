package com.hrishi.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteLayout extends AppCompatActivity {
    public static final String EXTRA_TITLE="EXTRA_TITLE";
    public static final String EXTRA_ID="EXTRA_ID";
    public static final String EXTRA_DESCRIPTION="EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="EXTRA_PRIORITY";


EditText editTextTitle,editTextDescription;
NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_layout);
        editTextTitle=findViewById(R.id.edit_text_title);
        editTextDescription=findViewById(R.id.edit_text_description);
        numberPicker=findViewById(R.id.num_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        Intent intent=getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");

            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));

        }else {
            setTitle("ADD NOTE");
        }


    }

    private void saveNote(){

        String title=editTextTitle.getText().toString();
        String description=editTextDescription.getText().toString();
        int priority=numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){

            Toast.makeText(this,"Please provide values in fields",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent=new Intent();
            intent.putExtra(EXTRA_TITLE,title);
            intent.putExtra(EXTRA_DESCRIPTION,description);
            intent.putExtra(EXTRA_PRIORITY,priority);
            int id=getIntent().getIntExtra(EXTRA_ID,-1);


            if (id!=-1){
                intent.putExtra(EXTRA_ID,id);

            }

            setResult(RESULT_OK,intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_save:
                saveNote();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
      return true;
    }
}
