package com.example.iamjiangtian.myresume;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.iamjiangtian.myresume.model.Project;

/**
 * Created by iamjiangtian on 12/9/16.
 */

public class ProjectActivity extends AppCompatActivity{
    public static final String KEY_PROJECT = "KEY_PROJECT";
    public Project oldProject;
    public Project newProject;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_project);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        oldProject = getIntent().getParcelableExtra(KEY_PROJECT);
        if(oldProject != null) retrieveOldProject(oldProject);
    }

    private void retrieveOldProject(Project oldProject){
        newProject = oldProject;
        ((EditText)findViewById(R.id.project_edit_name)).setText(oldProject.projectName);
        ((EditText)findViewById(R.id.project_edit_detail)).setText(oldProject.detail);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit_save:
                saveAndExit();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void saveAndExit(){
        String newProjectName = ((EditText)findViewById(R.id.project_edit_name)).getText().toString();
        String newProjectDetail = ((EditText)findViewById(R.id.project_edit_detail)).getText().toString();

        if(oldProject == null) newProject = new Project();
        newProject.projectName = newProjectName;
        newProject.detail = newProjectDetail;

        Intent intent = new Intent(ProjectActivity.this, MainActivity.class);
        intent.putExtra(KEY_PROJECT, newProject);
        setResult(RESULT_OK, intent);
        finish();
    }
}

