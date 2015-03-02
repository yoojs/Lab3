package com.example.james.myruns;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.app.DialogFragment;


public class Profile extends ActionBarActivity {
    public static final String PREFS_NAME = "com.example.james.myruns";
    EditText name, email, phone, classes, major;
    RadioGroup radioGenders;
    int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        name = (EditText) findViewById(R.id.editName);
        email = (EditText) findViewById(R.id.editEmail);
        phone = (EditText) findViewById(R.id.editPhone);
        classes = (EditText) findViewById(R.id.editClass);
        major = (EditText) findViewById(R.id.editMajor);
        RadioGroup radioGenders = (RadioGroup) findViewById(R.id.radioGender);

        radioGenders.check(settings.getInt("gender", -1));

        name.setText(settings.getString("name",""));
        email.setText(settings.getString("major",""));
        phone.setText(settings.getString("phone",""));
        classes.setText(settings.getString("class",""));
        major.setText(settings.getString("major",""));

    }

    public void savePreferences(View view) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        name = (EditText) findViewById(R.id.editName);
        email = (EditText) findViewById(R.id.editEmail);
        phone = (EditText) findViewById(R.id.editPhone);
        classes = (EditText) findViewById(R.id.editClass);
        major = (EditText) findViewById(R.id.editMajor);
        radioGenders = (RadioGroup) findViewById(R.id.radioGender);

        int selectedId = radioGenders.getCheckedRadioButtonId();
        gender = radioGenders.indexOfChild(findViewById(selectedId));



        String savedName = name.getText().toString();
        String savedEmail= email.getText().toString();
        String savedPhone = phone.getText().toString();
        String savedClass = classes.getText().toString();
        String savedMajor = major.getText().toString();

        editor.putInt("gender", gender);
        editor.putString("name", savedName);
        editor.putString("email", savedEmail);
        editor.putString("phone", savedPhone);
        editor.putString("class", savedClass);
        editor.putString("major", savedMajor);

        editor.commit();

    }
    public void cancelActivity(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayDialog(View m) {
           int id = Integer.parseInt(m.getTag().toString());
        DialogFragment fragment = MyRunsDialogFragment.newInstance(id); fragment.show(getFragmentManager(),
                getString(R.string.dialog_fragment_tag_photo_picker)); }

}
