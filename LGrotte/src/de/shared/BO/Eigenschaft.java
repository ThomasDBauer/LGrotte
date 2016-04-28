package de.shared.BO;

public abstract class Eigenschaft extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String erlaeuterung;

	public Eigenschaft(int id){
		this.id = id;
	}
	
	public Eigenschaft(int id, String erl){
		this(id);
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return erlaeuterung;
	}

	public void setName(String name) {
		this.erlaeuterung = name;
	}
	
	
	
}
