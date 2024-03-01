package models;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Individual.class, name = "Individual"),
    @JsonSubTypes.Type(value = Corporation.class, name = "Corporation")
})
public abstract class Subscriber {
	private String name;
	private String address;
	
	
	public Subscriber() {
	}

	public Subscriber(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	abstract String getBillingInformation();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Subscriber [name=" + name + ", address=" + address + "]";
	}

	
	
	
	
}
