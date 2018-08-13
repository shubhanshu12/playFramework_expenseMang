package dto;

public class ClientDTO {
	
	private Long Id;
	private String name;
	private String city;
	
	public ClientDTO() {
	
	}
	
	public ClientDTO(Long Id, String name, String city) {
		super();
		this.Id = Id;
		this.name = name;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "ClientDTO [name=" + name + ", city=" + city + "]";
	}
}
