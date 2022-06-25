package info3.game.entity;

public enum EntityType {
	TILE_TABLE("table", "BasicTable"),

	TILE_CITY("ville", "CityTile"),

	CONE("cone", "TrafficCone"),

	MARKET("marché", "Market"),

	CAR("voiture", "Car"),

	TILE_DELIVERY_CITY("zone de livraison", "CityDeliveryTile"),

	COCKROACH("cafard", "Cockroach"),

	COOK("cuisinier", "KitchenPlayer"),

	TILE_CUT("planche à découp.", "CutTable"),

	TILE_DELIVERY("table de livraison", "DeliveryTable"),

	TILE_FRYER("friteuse", "FryTable"),

	TILE_PAN("plaques de cuiss.", "CookTable"),

	TILE_SAUCE("table à sauce", "SauceTable"),

	TILE_STOCK("garde-manger", "StockTable"),

	TILE_TRASH("poubelle", "TrashTable"),

	TRUCK("camion du joueur", "Truck");

	public final String displayName;
	public final String defaultAutomaton;

	EntityType(String displayName, String defaultAutomaton) {
		this.displayName = displayName;
		this.defaultAutomaton = defaultAutomaton;
	}
}
