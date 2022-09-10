package com.lucascram.tilegraphicsgame.resource;



public abstract class AbstractResource {

	private String		location;
	private boolean		loaded;
	private String		name;
	
	public AbstractResource(String path, String name) {
		if(path != null) {
			location = path;
		} else {
			location = null;
		}
		loaded = false;
		this.name = name;
	}	
	
	/*
	 * Accessors and Mutators
	 */
	
	public String getPath() {
		if(location != null) {
			return location.toString();
		} else {
			return null;
		}
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	public void setLoaded(boolean b) {
		loaded = b;
	}
	
	public String getName() {
		return name;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(name == null) {
			result = prime;
		} else {
			result = prime * result + name.hashCode();
		}
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		AbstractResource other = (AbstractResource) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
}
