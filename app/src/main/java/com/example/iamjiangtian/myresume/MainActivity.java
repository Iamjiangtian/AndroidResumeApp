package com.example.iamjiangtian.myresume;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Normalizer2;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iamjiangtian.myresume.model.BasicInfo;
import com.example.iamjiangtian.myresume.model.Education;
import com.example.iamjiangtian.myresume.model.Experience;
import com.example.iamjiangtian.myresume.model.Project;
import com.example.iamjiangtian.myresume.utils.DateHandler;
import com.example.iamjiangtian.myresume.utils.ModelUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.onClick;
import static android.R.attr.start;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    private BasicInfo basicInfo;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Project> projects;

    private static final int REQ_CODE_EXP = 100;
    private static final int REQ_CODE_EDU = 101;
    private static final int REQ_CODE_PROJECT = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fakeData();
        loadData();
        setupUI();
    }
    public void loadData(){
        Type eduType = new TypeToken<List<Education>>(){}.getType();
        educations = new ArrayList<>();
        educations = ModelUtils.read(this, eduType, "EduData");
        Log.d("ok", "loadData");
       // basicInfo = new BasicInfo();
        experiences = new ArrayList<>();
        projects = new ArrayList<>();

    }
    public void fakeData() {
        Education testEdu = new Education();
        testEdu.school =  "fuck";
        Log.d("0", "0");
        java.text.SimpleDateFormat sdfx = new java.text.SimpleDateFormat("MM/yyyy");
        try {
            testEdu.startDate = sdfx.parse("09/1994");
        } catch (ParseException e) {
            Log.e("TIME", "startTime exception");
        }
        try {
            testEdu.endDate = sdfx.parse("12/2016");
        } catch (ParseException e) {
            Log.e("TIME", "endTime exception");
        }
       // TypeToken type = new TypeToken<List<Education>>(){};
        List<Education> testEduArray = new ArrayList<>();
        testEduArray.add(testEdu);
        ModelUtils.write(this, testEduArray, "EduData");
        Type type = new TypeToken<List<Education>>(){}.getType();
        educations = new ArrayList<>();
        educations = ModelUtils.read(this, type, "EduData");
        Log.d("3", "9");
        //set fake basic information
        basicInfo = new BasicInfo();
        basicInfo.name = "Jiangtian Wang Paul";
        basicInfo.email = "jiw077@ucsd.edu";


        //set fake experience information
        Experience experience1 = new Experience();
        experience1.company = "Banzai Entertainment";
        experience1.detail = "- played in the first month\n- played in the second month\n- " +
                "played in the third month ";
        DateHandler expDate = new DateHandler();
        Date[] expDateRange = expDate.stringToDate("07/2015", "10/2015");
        experience1.startDate = expDateRange[0];
        experience1.endDate = expDateRange[1];


        Experience experience2 = new Experience();
        experience2.company = "CNPC Design Institute";
        experience2.detail = "- slept in the first month\n- slept in the second month";
        expDateRange = expDate.stringToDate("06/2014", "10/2014");
        experience2.startDate = expDateRange[0];
        experience2.endDate = expDateRange[1];

        experiences = new ArrayList<>();
        experiences.add(experience1);
        experiences.add(experience2);
        //set fake project information
        projects = new ArrayList<>();
        Project project = new Project();
        project.projectName = "myResume";
        project.detail = "- Custome resume app on Android platform" +
                "\n- Users can freely edit their personal resume\n" +
                "- Users are able to upload their profile pictures";
        projects.add(project);
        /*Project project2 = new Project();
        project2.projectName = "Balance Ball";
        project2.detail = "- 3D game using javaScript in Unity engine\n- players need to use the " +
                "physical features to solve puzzles";*/
        ;
    }

    public void setupUI() {

        setContentView(R.layout.activity_main);
      //  ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
       // ((TextView) findViewById(R.id.email)).setText(basicInfo.email);

        setupEducation();

        setupExperience();

        setupProject();


        findViewById(R.id.add_edu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationActivity.class);
                startActivityForResult(intent, REQ_CODE_EDU);
            }
        });
        findViewById(R.id.add_exp_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExperienceActivity.class);
                startActivityForResult(intent, REQ_CODE_EXP);
            }
        });
        findViewById(R.id.add_project_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                startActivityForResult(intent, REQ_CODE_PROJECT);
            }
        });


    }

    public void setupEducation() {
        LinearLayout eduLayout = (LinearLayout) findViewById(R.id.education_items); //mainAct edu_items
        eduLayout.removeAllViews();
        for (Education education : educations) {
            View ev = getEduView(education);
            eduLayout.addView(ev);

        }
    }
    public void setupExperience() {
        LinearLayout expLayout = (LinearLayout) findViewById(R.id.experience_items);
        expLayout.removeAllViews();
        for (Experience experience : experiences) {
            View view = getExpView(experience);
            expLayout.addView(view);
        }
    }

    private void setupProject() {
        LinearLayout projectLayout = (LinearLayout) findViewById(R.id.project_items);
        projectLayout.removeAllViews();
        for (Project project : projects) {
            View view = getProjectView(project);
            projectLayout.addView(view);
        }
    }

    private View getEduView(final Education education) {
        View ev = getLayoutInflater().inflate(R.layout.education_items, null); //newForm edu_items
        DateHandler dateHandler = new DateHandler();
        String[] dateRange = dateHandler.dateToString(education.startDate, education.endDate);
        ((TextView) ev.findViewById(R.id.school)).setText(education.school + " " + "(" +
                dateRange[0] + "~" + dateRange[1] + ")");
        ((TextView) ev.findViewById(R.id.courses)).setText(education.courses);

        //edit btn leads to the educationActivity page
        editEduView(ev, education);
        deleteEduView(ev, education);
        return ev;
    }

    private View getExpView(final Experience experience) {
        View view = (View) getLayoutInflater().inflate(R.layout.experience_items, null);
        DateHandler dateHandler = new DateHandler();
        String[] dateRange = dateHandler.dateToString(experience.startDate, experience.endDate);
        ((TextView) view.findViewById(R.id.company)).setText(experience.company + " " + "- " +
                experience.position + " " + "(" + dateRange[0] + "~" + dateRange[1] + ")");
        ((TextView) view.findViewById(R.id.experience_detail)).setText(experience.detail);

        //edit exp
        editExpView(view, experience);
        deleteExpView(view, experience);
        return view;
    }


    private View getProjectView(final Project project){
        View view = (View) getLayoutInflater().inflate(R.layout.project_items, null);
        ((TextView) view.findViewById(R.id.project_name)).setText(project.projectName);
        ((TextView) view.findViewById(R.id.project_detail)).setText(project.detail);

        //edit project
        editProjectView(view, project);
        deleteProjectView(view, project);
        return view;
    }

    private void editEduView(final View view, final Education education){
        view.findViewById(R.id.edit_education_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationActivity.class);
                intent.putExtra(EducationActivity.KEY_EDU, education);
                startActivityForResult(intent, REQ_CODE_EDU);
            }
        });
    }
    private void editExpView(final View view, final Experience experience){
        view.findViewById(R.id.edit_experience_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExperienceActivity.class);
                intent.putExtra(ExperienceActivity.KEY_EXP, experience);
                startActivityForResult(intent, REQ_CODE_EXP);
            }
        });
    }
    private void editProjectView(final View view, final Project project){
        view.findViewById(R.id.edit_project_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                intent.putExtra(ProjectActivity.KEY_PROJECT, project);
                startActivityForResult(intent, REQ_CODE_PROJECT);
            }
        });
    }

    private void deleteExpView(final View view, final Experience experience){
        view.findViewById(R.id.delete_experience_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for(int i = 0; i < experiences.size(); i++){
                    if(TextUtils.equals(experiences.get(i).id, experience.id)){
                        experiences.remove(i);
                        setupExperience();
                    }
                }
            }
        });
    }
    private void deleteProjectView(final View view, final Project project){
        view.findViewById(R.id.delete_project_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for(int i = 0; i < projects.size(); i++){
                    if(TextUtils.equals(projects.get(i).id, project.id)){
                        projects.remove(i);
                        setupProject();
                    }
                }
            }
        });
    }
    private void deleteEduView(final View view, final Education education){
        view.findViewById(R.id.delete_education_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for(int i = 0; i < educations.size(); i++){
                    if(TextUtils.equals(educations.get(i).id, education.id)){
                        educations.remove(i);
                        ModelUtils.write(MainActivity.this, educations, "EduData");
                        setupEducation();
                    }
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQ_CODE_EXP) {
            Experience experience = data.getParcelableExtra(ExperienceActivity.KEY_EXP);
            updateExperience(experience);
            setupExperience();
        } else if (resultCode == Activity.RESULT_OK && requestCode == REQ_CODE_EDU) {
            Education education = data.getParcelableExtra(EducationActivity.KEY_EDU);
            updateEducation(education);
            setupEducation();
        } else if (resultCode == Activity.RESULT_OK && requestCode == REQ_CODE_PROJECT) {
            Project project = data.getParcelableExtra(ProjectActivity.KEY_PROJECT);
            updateProject(project);
            setupProject();
        }
    }

    private void updateProject(Project project){
        for(int i = 0; i < projects.size(); i++){
            if(TextUtils.equals(project.id, projects.get(i).id)){
                projects.set(i, project);
                return;
            }
        }
        projects.add(project);
    }
    private void updateExperience(Experience experience){
        for(int i = 0; i < experiences.size(); i++){
            Log.d("id", experiences.get(i).id);
            if(TextUtils.equals(experience.id, experiences.get(i).id)){
                experiences.set(i,experience);//remove
                //save to sharedPreferences
                return;
            }
        }
        experiences.add(experience);
    }

    private void updateEducation(Education education){
        for(int i = 0; i < educations.size(); i++){
            if(TextUtils.equals(educations.get(i).id, education.id)){
                educations.set(i, education);
                ModelUtils.write(this, educations, "EduData");
                return;
            }
        }
        educations.add(education);
        ModelUtils.write(this, educations, "EduData");
    }
}
