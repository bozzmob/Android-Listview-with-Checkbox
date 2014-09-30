package com.list.check.box;

public class Model {

	private String appname;
	private String packagename;
	private boolean selected;

	public Model(String name, String packagename) {
		this.appname = name;
		this.packagename = packagename;
	}
	
	public String getName() {
		return appname;
	}
	
	public String getPackageName() {
		return packagename;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
