package com.example.karthikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SqliteActivity extends AppCompatActivity {

    EditText name,mail,mobile,sno;
    ListView listViewRecords;

    public static final String TABLE_NAME="UserTable";
    public static final String COLUMN_SNO="SNo";
    public static final String COLUMN_NAME="Name";
    public static final String COLUMN_MAIL="Mail";
    public static final String COLUMN_MOBILE="Mobile";


    UserHelper userHelper;
    SQLiteDatabase sqLiteDatabase;

    int id;
    String nameStr,mailStr,mobileStr;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        mobile=findViewById(R.id.mobile);
        sno=findViewById(R.id.sno);
        listViewRecords=findViewById(R.id.recordList);

        userHelper=new UserHelper(this);
        sqLiteDatabase=userHelper.getReadableDatabase();

        findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id=Integer.parseInt(sno.getText().toString());
                nameStr=name.getText().toString();
                mobileStr=mobile.getText().toString();
                mailStr=mail.getText().toString();

                long set=userHelper.CreateMethod(sqLiteDatabase,id,nameStr,mailStr,mobileStr);

                Toast.makeText(SqliteActivity.this, ""+set, Toast.LENGTH_SHORT).show();

                if(set==-1)
                {
                    Toast.makeText(SqliteActivity.this, "RECORD ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SqliteActivity.this, "NEW RECORD CREATED", Toast.LENGTH_SHORT).show();
                    readRecordsMethod();

                }



            }
        });
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readRecordsMethod();
            }
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id=Integer.parseInt(sno.getText().toString());
                nameStr=name.getText().toString();
                mobileStr=mobile.getText().toString();
                mailStr=mail.getText().toString();

                long set=userHelper.UpdateMethod(sqLiteDatabase,id,nameStr,mailStr,mobileStr);

                Toast.makeText(SqliteActivity.this, ""+set, Toast.LENGTH_SHORT).show();

                if(set==0)
                {
                    Toast.makeText(SqliteActivity.this, "NO RECORD IS  EXIST", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SqliteActivity.this, " RECORD UPDATE SUCCESS", Toast.LENGTH_SHORT).show();
                    readRecordsMethod();

                }

            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=Integer.parseInt(sno.getText().toString());

                long set=userHelper.DeleteMethod(sqLiteDatabase,id);

                Toast.makeText(SqliteActivity.this, ""+set, Toast.LENGTH_SHORT).show();

                if(set==0)
                {
                    Toast.makeText(SqliteActivity.this, "NO RECORD IS  EXIST", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SqliteActivity.this, " RECORD DELETE SUCCESS", Toast.LENGTH_SHORT).show();
                    readRecordsMethod();

                }
            }
        });
    }

    private void readRecordsMethod() {

        arrayList.clear();

        String[] Columns={"COLUMN_SNO","COLUMN_NAME","COLUMN_MAIL","COLUMN_MOBILE"};

        Cursor cursor=sqLiteDatabase.query("TABLE_NAME",Columns,null,null,null,null,null);

        if(cursor.getCount()>0&&cursor!=null)
        {
            while (cursor.moveToNext())
            {
                String name=cursor.getString(1);
                String mail=cursor.getString(2);
                String mobile=cursor.getString(3);
                arrayList.add(""+name+"\n"+mail+"\n"+mobile);
                arrayAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
                listViewRecords.setAdapter(arrayAdapter);
            }
        }
        else
        {
            Toast.makeText(this, "No Records Found", Toast.LENGTH_SHORT).show();
        }

    }
}