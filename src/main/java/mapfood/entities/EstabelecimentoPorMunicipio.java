package mapfood.entities;

public class EstabelecimentoPorMunicipio {
	private String restauranteId;
	private String restaurante;
	private String adressCity;
	private double longitude;
	private double latitude;
	private String dishdescription;
	public String getRestauranteId() {
		return restauranteId;
	}
	public void setRestauranteId(String restauranteId) {
		this.restauranteId = restauranteId;
	}
	public String getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}
	public String getAdressCity() {
		return adressCity;
	}
	public void setAdressCity(String adressCity) {
		this.adressCity = adressCity;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public String getDishdescription() {
		return dishdescription;
	}
	public void setDishdescription(String dishdescription) {
		this.dishdescription = dishdescription;
	}
}
