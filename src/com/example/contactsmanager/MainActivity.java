package com.example.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();
        if (intent != null) {
          String phone = intent.getStringExtra("PHONE_NUMBER_KEY");
          if (phone != null) {
        	EditText editphone = (EditText)findViewById(R.id.telefon);	
            editphone.setText(phone);
          } else {
            Toast.makeText(this, getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
          }
        } 
        
        Button addButton = (Button)findViewById(R.id.buttonAdditional);
        addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LinearLayout l2 = (LinearLayout)findViewById(R.id.layout2);
				l2.setVisibility( ~ l2.getVisibility()); 
				
			}
		});
        
        Button saveButton = (Button)findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
					  
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				EditText name = (EditText)findViewById(R.id.nume);
				if (name.getText().toString() != null) {
					intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
				}
				EditText phone = (EditText)findViewById(R.id.telefon);
				if (phone != null) {
					intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
				}
				EditText job = (EditText)findViewById(R.id.pozitia);
				if (job.getText().toString() != null) {
					intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, job.getText().toString());
				}
				EditText companie = (EditText)findViewById(R.id.compania );
				
				if (companie.getText().toString()  != null) {
					intent.putExtra(ContactsContract.Intents.Insert.COMPANY, companie.getText().toString());
				}

				/*ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
        
				intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);*/
				startActivity(intent);
    	
			}
		});
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
