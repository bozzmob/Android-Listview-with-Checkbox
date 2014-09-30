package com.list.check.box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener{
   
	ListView listView;
	ArrayAdapter<Model> adapter;
	List<Model> list = new ArrayList<Model>();
	List<Model> something = new ArrayList<Model>();
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		listView = (ListView) findViewById(R.id.my_list);
		adapter = new MyAdapter(this, getPackages());
		//adapter = new MyAdapter(this,getModel());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		TextView label = (TextView) v.getTag(R.id.label);
		CheckBox checkbox = (CheckBox) v.getTag(R.id.check);
		Toast.makeText(v.getContext(), label.getText().toString()+" "+isCheckedOrNot(checkbox), Toast.LENGTH_LONG).show();
	}
	
	private String isCheckedOrNot(CheckBox checkbox) {
		if(checkbox.isChecked())
			return "is checked";
		else
			return "is not checked";
	}

	private List<Model> getModel() {
		/*list.add(new Model("Linux"));
		list.add(new Model("Windows7"));
		list.add(new Model("Suse"));
		list.add(new Model("Eclipse"));
		list.add(new Model("Ubuntu"));
		list.add(new Model("Solaris"));
		list.add(new Model("Android"));
		list.add(new Model("iPhone"));
		list.add(new Model("Java"));
		list.add(new Model(".Net"));
		list.add(new Model("PHP"));*/
		something = getPackages();
		
		return something;
	}

	public List<Model> getPackages() {
    ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
    //List<Model> applist = new ArrayList<Model>();
    final int max = apps.size();
    for (int i=0; i<max; i++) {
        apps.get(i).prettyPrint();
        list.add(new Model(apps.get(i).appname,apps.get(i).pname));
    }
    //Collections.sort(list);
    return list;
}

public ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
    ArrayList<PInfo> res = new ArrayList<PInfo>();        
    List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
    for(int i=0;i<packs.size();i++) {
        PackageInfo p = packs.get(i);
        if ((!getSysPackages) && (p.versionName == null)) {
            continue ;
        }
        PInfo newInfo = new PInfo();
        newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
        newInfo.pname = p.packageName;
        newInfo.versionName = p.versionName;
        newInfo.versionCode = p.versionCode;
        newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
        System.out.println(newInfo.appname);
        System.out.println(newInfo.pname);
        res.add(newInfo);
    }
    return res; 
}


	//public ArrayList<PInfo> getPackages() {
    //ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
    /*final int max = apps.size();
    for (int i=0; i<max; i++) {
        apps.get(i).prettyPrint();
    }
    return apps;
}

public ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
    ArrayList<PInfo> res = new ArrayList<PInfo>();        
    List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
    for(int i=0;i<packs.size();i++) {
        PackageInfo p = packs.get(i);
        if ((!getSysPackages) && (p.versionName == null)) {
            continue ;
        }
        PInfo newInfo = new PInfo();
        newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
        newInfo.pname = p.packageName;
        newInfo.versionName = p.versionName;
        newInfo.versionCode = p.versionCode;
        newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
        System.out.println(newInfo.appname);
        System.out.println(newInfo.pname);
        Toast toast = Toast.makeText(getApplicationContext(), newInfo.appname, 2);
        toast.show();
        res.add(newInfo);
    }
    return res; 
}
*/
}