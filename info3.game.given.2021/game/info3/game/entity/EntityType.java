package info3.game.entity;

public enum EntityType {
	TILE_TABLE("table", "Table"),

	// TODO
	TILE_CITY("ville", "TrafficCone"),

	COCKROACH("cafard", "Cockroach"),

	COOK("cuisinier", "Cook"),

	TILE_CUT("planche à découp.", "Planche"),

	TILE_DELIVERY("table de livraison", "Livraison"),

	TILE_FRYER("friteuse", "Friteuse"),

	TILE_PAN("plaques de cuiss.", "Feu_cuisson"),

	TILE_SAUCE("table à sauce", "Table_a_sauce"),

	TILE_STOCK("garde-manger", "Garde_manger"),

	TILE_TRASH("poubelle", "Poubelle");

	public final String displayName;
	public final String defaultAutomaton;

	EntityType(String displayName, String defaultAutomaton) {
		this.displayName = displayName;
		this.defaultAutomaton = defaultAutomaton;
	}
}
