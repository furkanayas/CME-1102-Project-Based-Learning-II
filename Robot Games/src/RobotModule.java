public class RobotModule {
	private String type;
	private int quality;
	private int weight;
	private int force;
	private int intelligence;
	private int skill;
	private int price;
	private int durability;
	
	public RobotModule(String type, int quality, int weight, int force, int intelligence, int skill, int price) {
		this.type = type;
		this.quality = quality;
		this.weight = weight;
		this.force = force;
		this.intelligence = intelligence;
		this.skill = skill;
		this.price = price;
		this.durability = 100;
	}

	
	public RobotModule() {
		this.type = "-";
		this.quality = 0;
		this.weight = 0;
		this.force = 0;
		this.intelligence = 0;
		this.skill = 0;
		this.durability = 100;
	}
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}
	
	
	
}
