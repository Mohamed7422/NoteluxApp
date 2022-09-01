package com.mhm.notelux;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import base.BaseActivity;
import database.MyDataBase;
import database.model.Note;

public class AddNoteActivity extends BaseActivity {


    private EditText title;
    private EditText content;
    private TextView time;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initView();
    }

    private void initView() {
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        time = (TextView) findViewById(R.id.time);
        add = (Button) findViewById(R.id.add);
    }

    String choosedTime ="";
    public void onClick(View view) {
        if(view.getId() == R.id.add){
           //get data from user
            String titleS = title.getText().toString();
            String contentS = content.getText().toString();

            //validate if nothing added in fields
            // add them to the data base
            if (!(titleS.equals("") || contentS.equals("") || choosedTime.equals(""))){
                Note noteS = new Note(titleS,contentS,choosedTime);
                MyDataBase.getInstance(this).notesDao().addNote(noteS);
                showMessage(R.string.note_added_successfully,
                        R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        },false) ;
            }

            else {
                //show warning notes if some field is not added yet
                if (titleS.equals("")){
                    Toast.makeText(this, "Title have to be filled.", Toast.LENGTH_SHORT).show();
                }else if (contentS.equals("")){
                    Toast.makeText(this, "Content have to be filled.", Toast.LENGTH_SHORT).show();
                }else if (choosedTime.equals("")){
                    Toast.makeText(this, "Time must be selected.", Toast.LENGTH_SHORT).show();
                }
            }
        }else if (view.getId() == R.id.time){

            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    time.setText(hourOfDay+" : "+minute);
                    choosedTime = hourOfDay + "-" + minute;
                }
            }, Calendar.HOUR_OF_DAY,
               calendar.get(Calendar.MINUTE),false);
            dialog.show();
        }
    }
}