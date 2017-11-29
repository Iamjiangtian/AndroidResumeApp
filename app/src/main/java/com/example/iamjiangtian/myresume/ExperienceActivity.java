package com.example.iamjiangtian.myresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.iamjiangtian.myresume.model.Experience;
import com.example.iamjiangtian.myresume.utils.DateHandler;

import java.util.Date;

/**
 * Created by iamjiangtian on 11/14/16.
 */

public class ExperienceActivity extends AppCompatActivity{
    public static final String KEY_EXP = "KEY_EXP";
    private Experience formExp;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        formExp = getIntent().getParcelableExtra(KEY_EXP);
        if(formExp!=null){
            retrieveForm(formExp);
        }
    }

    private void retrieveForm(Experience formExp){
        ((EditText)findViewById(R.id.new_company)).setText(formExp.company);
        ((EditText)findViewById(R.id.new_position)).setText(formExp.position);
        DateHandler dh = new DateHandler();
        String[] dateRange = dh.dateToString(formExp.startDate, formExp.endDate);
        ((EditText)findViewById(R.id.new_start)).setText(dateRange[0]);
        ((EditText)findViewById(R.id.new_end)).setText(dateRange[1]);
        ((EditText)findViewById(R.id.new_exp_details)).setText(formExp.detail);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.edit_save:
                saveExperience();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }
    public void saveExperience(){

        //get the editText units
        EditText companyName = (EditText) findViewById(R.id.new_company);
        EditText positionName = (EditText) findViewById(R.id.new_position);
        EditText expDetails = (EditText) findViewById(R.id.new_exp_details);
        EditText startDate = (EditText) findViewById(R.id.new_start);
        EditText endDate = (EditText) findViewById(R.id.new_end);

        //transfer the editText units to strings
        String newCompanyName = companyName.getText().toString();
        String newPositionName = positionName.getText().toString();
        String newExpDetails = expDetails.getText().toString();
        String newStartDate = startDate.getText().toString();
        String newEndDate = endDate.getText().toString();

        //transfer start and end date string to Date type
        DateHandler dateHandler = new DateHandler();
        Date[] datePair = dateHandler.stringToDate(newStartDate, newEndDate);

        //save the form date into the return experience object
        if(formExp == null) formExp = new Experience();
        formExp.company = newCompanyName;
        formExp.position = newPositionName;
        formExp.startDate = datePair[0];
        formExp.endDate = datePair[1];
        formExp.detail = newExpDetails;

        //add the new experience into the public experiences list
        //MainActivity.experiences.add(formExp);

        //return to the main page
        Intent intent = new Intent();
        intent.putExtra(KEY_EXP, formExp);
        setResult(Activity.RESULT_OK,intent);

        finish();
    }
}
