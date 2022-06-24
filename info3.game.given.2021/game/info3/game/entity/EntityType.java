package info3.game.entity;

public enum EntityType {
	TILE_TABLE("table", "Table"),

	// TODO
	TILE_CITY("ville", "CityTile"),

	CONE("cone", "TrafficCone"),

	MARKET("marché", "Market"),

	CAR("voiture", "Car"),
	
	TILE_DELIVERY_CITY("zone de livraison", "CityDeliveryTile"),

	COCKROACH("cafard", "Cockroach"),

	COOK("cuisinier", "Cook"),

	TILE_CUT("planche à découp.", "Cut"),

	TILE_DELIVERY_KITCHEN("table de livraison", "Kitchen_Delivery"),

	TILE_FRYER("friteuse", "Fry"),

	TILE_PAN("plaques de cuiss.", "Firecook"),

	TILE_SAUCE("table à sauce", "SauceTable"),

	TILE_STOCK("garde-manger", "StockTable"),

	TILE_TRASH("poubelle", "Trash"),

	TRUCK("camion du joueur", "Truck");

	public final String displayName;
	public final String defaultAutomaton;

	EntityType(String displayName, String defaultAutomaton) {
		this.displayName = displayName;
		this.defaultAutomaton = defaultAutomaton;
	}
}
