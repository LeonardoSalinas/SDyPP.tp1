package tp1.pto3;

import java.io.Serializable;

public class Mensaje implements Serializable{
	private String from;
	private String to;
	private String body;
	
	public Mensaje(String from, String to, String body) {
		this.from = from;
		this.to = to;
		this.body = body;
	}
	
	public String getTo(){
		return this.to;
	}
	
	public String getFrom(){
		return this.from;
	}
	
	public String toString(){
		return "From: "+this.from+"\nTo: "+this.to+"\nMessage: "+this.body;
	}
}
