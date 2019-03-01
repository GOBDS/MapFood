package mapfood.entities;

public class PropertyByCity {
	private String restaurantId;
	private String restaurant;
	private String adressCity;
	private double longitude;
	private double latitude;
	private String dishdescription;
	
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
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
