package com.example.iamjiangtian.myresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iamjiangtian.myresume.model.Education;
import com.example.iamjiangtian.myresume.utils.DateHandler;

import java.util.Date;

/**
 * Created by iamjiangtian on 12/9/16.
 */

public class EducationActivity extends AppCompatActivity {
    public static final String KEY_EDU = "KEY_EDU";
    public Education oldEducation;
    private Boolean isEdit = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        oldEducation = getIntent().getParcelableExtra(KEY_EDU);
        if(oldEducation != null){
            isEdit = true;
            initEditForm(oldEducation);
        }

    }
    private void initEditForm(Education oldEdu){
        ((EditText) findViewById(R.id.new_school)).setText(oldEdu.school);
        DateHandler dateHandler = new DateHandler();
        String[] dateRange =  dateHandler.dateToString(oldEdu.startDate, oldEdu.endDate);
        ((EditText) findViewById(R.id.new_start)).setText(dateRange[0]);
        ((EditText) findViewById(R.id.new_end)).setText(dateRange[1]);
        ((EditText) findViewById(R.id.new_course)).setText(oldEdu.courses);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit_save:
                saveEdu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void saveEdu(){
        Education education = new Education();
        Log.d("actnewID", education.id);
    //    Log.d("actoldID", oldEducation.id);

        if(isEdit) education.id = oldEducation.id;
        education.school = ((EditText)findViewById(R.id.new_school)).getText().toString();
        education.courses = ((EditText)findViewById(R.id.new_course)).getText().toString();

        DateHandler dateHandler = new DateHandler();
        String newStart = ((EditText)findViewById(R.id.new_start)).getText().toString();
        String newEnd = ((EditText)findViewById(R.id.new_end)).getText().toString();
        Date[] dateRange = dateHandler.stringToDate(newStart, newEnd);
        education.startDate = dateRange[0];
        education.endDate = dateRange[1];

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDU, education);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
