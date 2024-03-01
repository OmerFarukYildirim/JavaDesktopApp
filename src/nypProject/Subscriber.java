package nypProject;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Individual.class, name = "Individual"),
    @JsonSubTypes.Type(value = Corporation.class, name = "Corporation")
})

/*use = JsonTypeInfo.Id.NAME: Tip bilgilerini isimle belirtir. Bu durumda, tip adları kullanılacaktır.
include = JsonTypeInfo.As.PROPERTY: Tip bilgisini JSON nesnesine bir özellik olarak eklemeyi belirtir. Bu özellik, "_class" adında bir özellik olacaktır.
property = "_class": Tip bilgisini içeren özelliğin adını belirtir. Bu durumda, "_class" adında bir özellik kullanılacaktır.

@JsonSubTypes.Type(value = Individual.class, name = "Individual"): "Individual" adında bir alt tip belirtir. Bu durumda, Individual sınıfı, "_class" özelliği 
"Individual" olan bir JSON nesnesiyle ilişkilendirilecektir.
@JsonSubTypes.Type(value = Corporation.class, name = "Corporation"): "Corporation" adında bir alt tip belirtir. Bu durumda, Corporation sınıfı, "_class" özelliği 
"Corporation" olan bir JSON nesnesiyle ilişkilendirilecektir.*/
public abstract class Subscriber {
	private String name;
	private String address;
	
	public Subscriber() { // parametre almayan constructor		
	}
	public Subscriber(String name, String address) {// isim ve adres degiskenlerini alan constructor
		this.name = name;
		this.address = address;
	}
	
	abstract String getBillingInformation();// alt dallarda yazılacak method

	public String getName() {// isimi getirir
		return name;
	}

	public void setName(String name) {// isime atama yapar
		this.name = name;
	}

	public String getAddress() {// adresi getirir
		return address;
	}

	public void setAddress(String address) {// adrese atama yapar
		this.address = address;
	}

	@Override
	public String toString() {// isim ve adresi yazdırır
		return "Subscriber [name=" + name + ", address=" + address + "]";
	}
}
