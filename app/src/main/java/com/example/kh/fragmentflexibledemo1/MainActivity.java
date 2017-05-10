package com.example.kh.fragmentflexibledemo1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kh.fragmentflexibledemo1.Module.Fragment_A;
import com.example.kh.fragmentflexibledemo1.Module.Fragment_B;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{
    private android.app.FragmentManager manager  =getFragmentManager();
    private FragmentTransaction transaction;
    private   Fragment_A fragment_a;
    private   Fragment_B fragment_b;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager.addOnBackStackChangedListener(this);
        init();
    }

    private void init() {
        txt  = (TextView) findViewById(R.id.txt);
    }

    public void process(View view){
        switch(view.getId()){
            case R.id.btnAddA:

                transaction  = manager.beginTransaction();
                transaction.add(R.id.group, new Fragment_A(), "A");
                transaction.addToBackStack("AddA");
                transaction.commit();
                break;
            case R.id.btnAddB:
                transaction = manager.beginTransaction();
                transaction.add(R.id.group, new Fragment_B(), "B");
                transaction.addToBackStack("AddB");
                transaction.commit();
                break;
            case R.id.btnRemoveA:
               fragment_a= (Fragment_A) manager.findFragmentByTag("A");
                transaction  = manager.beginTransaction();
                if(fragment_a!=null) {
                    transaction.remove(fragment_a);
                    transaction.addToBackStack("RemoveA");
                    transaction.commit();
                }else{
                    Toast.makeText(this, "Fragment A is not Found", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnRemoveB:
                fragment_b  = (Fragment_B) manager.findFragmentByTag("B");
                transaction = manager.beginTransaction();
                if(fragment_b!=null) {
                    transaction.remove(fragment_b);
                    transaction.addToBackStack("RemoveB");
                    transaction.commit();
                }else Toast.makeText(this, "Fragment B is not found", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnReplaceA:
                transaction  = manager.beginTransaction();
                transaction.replace(R.id.group, new Fragment_A());
                transaction.addToBackStack("ReplaceA");
                transaction.commit();
                break;
            case R.id.btnReplaceB:
                transaction = manager.beginTransaction();
                transaction.replace(R.id.group, new Fragment_B());
                transaction.addToBackStack("ReplaceB");
                transaction.commit();
                break;
            case R.id.btnAttachA:
                fragment_a  = (Fragment_A) manager.findFragmentByTag("A");
                transaction = manager.beginTransaction();
                if(fragment_a!=null) {
                    transaction.attach(fragment_a);
                    transaction.addToBackStack("AttachA");
                    transaction.commit();
                }else{
                    Toast.makeText(this, "Fragment A is not existed", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnDetachA:
                fragment_a  = (Fragment_A) manager.findFragmentByTag("A");
                transaction = manager.beginTransaction();
                if(fragment_a!=null){
                    transaction.detach(fragment_a);
                     transaction.addToBackStack("DetachA");
                    transaction.commit();
                }else{
                    Toast.makeText(this, "Fragment A is not existed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnAttachB:
                fragment_b  = (Fragment_B) manager.findFragmentByTag("B");
                transaction = manager.beginTransaction();
                if(fragment_b!=null){
                    transaction.attach(fragment_b);
                    transaction.addToBackStack("AttachB");
                    transaction.commit();
                }else{
                    Toast.makeText(this, "Fragment B is not existed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDetachB:
                fragment_b = (Fragment_B) manager.findFragmentByTag("B");
                transaction= manager.beginTransaction();
                if(fragment_b!=null){
                    transaction.detach(fragment_b);
                    transaction.addToBackStack("DetachB");
                    transaction.commit();
                }else{
                    Toast.makeText(this, "Fragment B is not existed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBack:
                manager.popBackStack();;
                break;
            case R.id.btnPopBackStack:
                manager.popBackStack("ReplaceA",0);
                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        txt.setText("The activity of Fragment: ");
        int count =  manager.getBackStackEntryCount();
        for(int i = count-1; i>=0; i--) {
            FragmentManager.BackStackEntry entry  = manager.getBackStackEntryAt(i);
            txt.setText(txt.getText()+" "+entry.getName());
        }
    }
}
