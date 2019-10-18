
public class Team {
	int credits;
	private Robots[] robot;
	private RobotModule[] modules;
	private RobotModule[] tempModules;
	private int countRobot;
	private int countModule;

	public Team(int credits, Robots[] robot, RobotModule[] modules) {
		this.credits = credits;
		this.robot = robot;
		this.modules = modules;
	}

	public Team() {
		this.credits = 1500;
		this.robot = new Robots[9];
		this.modules = new RobotModule[20];
		this.tempModules = new RobotModule[4];
		this.countRobot = 0;
		this.countModule = 0;
		for (int i = 0; i < modules.length; i++) {
			modules[i] = new RobotModule();
		}
		for (int i = 0; i < robot.length; i++) {
			robot[i] = new Robots();
		}

	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Robots[] getRobot() {
		return robot;
	}

	public void setRobot(Robots[] robot) {
		this.robot = robot;
	}

	public RobotModule[] getModules() {
		return modules;
	}

	public void setModules(RobotModule[] modules) {
		this.modules = modules;
	}

	public void addModule(String type, int quality, int weight, int force, int intelligence, int skill, int price) {
		for (int i = 0; i < modules.length; i++) {
			if (modules[i].getType().equals("-")) {
				modules[i] = new RobotModule(type, quality, weight, force, intelligence, skill, price);
				break;
			}
		}		
		
		countModule++;

	}

	public void dividedModule(RobotModule dividedModule) {
		String type = dividedModule.getType();
		int quality = dividedModule.getQuality();
		int weight = dividedModule.getWeight();
		int force = dividedModule.getForce();
		int intelligence = dividedModule.getIntelligence();
		int skill = dividedModule.getSkill();
		int price = dividedModule.getPrice();
		countRobot--;
		addModule(type, quality, weight, force, intelligence, skill, price);
	}

	public void addRobot(String name, String arm, String torso, String leg, String head) {
		if (arm != torso || arm != leg || arm != head || torso != leg || torso != head || leg != head) {
			for (int i = 0; i < modules.length; i++) {

				if (modules[i].getType() != "-") {
					if ((modules[i].getType() + modules[i].getQuality()).equals(arm)) {
						tempModules[0] = new RobotModule(modules[i].getType(), modules[i].getQuality(),
								modules[i].getWeight(), modules[i].getForce(), modules[i].getIntelligence(),
								modules[i].getSkill(), modules[i].getPrice());
						resetModule(i);
					} else if ((modules[i].getType() + modules[i].getQuality()).equals(torso)) {
						tempModules[1] = new RobotModule(modules[i].getType(), modules[i].getQuality(),
								modules[i].getWeight(), modules[i].getForce(), modules[i].getIntelligence(),
								modules[i].getSkill(), modules[i].getPrice());
						resetModule(i);
					} else if ((modules[i].getType() + modules[i].getQuality()).equals(leg)) {
						tempModules[2] = new RobotModule(modules[i].getType(), modules[i].getQuality(),
								modules[i].getWeight(), modules[i].getForce(), modules[i].getIntelligence(),
								modules[i].getSkill(), modules[i].getPrice());
						resetModule(i);
					} else if ((modules[i].getType() + modules[i].getQuality()).equals(head)) {
						tempModules[3] = new RobotModule(modules[i].getType(), modules[i].getQuality(),
								modules[i].getWeight(), modules[i].getForce(), modules[i].getIntelligence(),
								modules[i].getSkill(), modules[i].getPrice());
						resetModule(i);
					}
				}
			}
			robot[countRobot].setName(name);
			robot[countRobot].setArm(tempModules[0]);
			// countModule--;
			robot[countRobot].setTorso(tempModules[1]);
			// countModule--;
			robot[countRobot].setLeg(tempModules[2]);
			// countModule--;
			robot[countRobot].setHead(tempModules[3]);
			// countModule--;
			countRobot++;

		} else {
			System.out.println("Error! This robot can't created.");
		}
	}

	public void displayRobots() {
		for (int i = 0; i < robot.length; i++) {
			if (!robot[i].getName().equals("-")) {
				System.out.println(robot[i].listRobot());
			}
		}
	}

	public void listModule() {
		for (int i = 0; i < modules.length; i++) {
			if (modules[i] != null) {
				if(i%5==0){
					System.out.println();
					System.out.print("m"+(i+1)+" "+modules[i].getType() + modules[i].getQuality()+" ");
					}
				else{
				System.out.print("m"+(i+1)+" "+modules[i].getType() + modules[i].getQuality()+" ");
				}			
			}	
		}
	}

	public void resetModule(int i) {
		modules[i].setType("-");
		modules[i].setQuality(0);
		modules[i].setDurability(100);
		modules[i].setForce(0);
		modules[i].setIntelligence(0);
		modules[i].setPrice(0);
		modules[i].setSkill(0);
		modules[i].setWeight(0);
		countModule--;
	}

	public void resetRobot(int i) {
		robot[i].setName("-");
		robot[i].getArm().setType("-");
		robot[i].getArm().setQuality(0);
		robot[i].getArm().setDurability(100);
		robot[i].getArm().setForce(0);
		robot[i].getArm().setIntelligence(0);
		robot[i].getArm().setPrice(0);
		robot[i].getArm().setSkill(0);
		robot[i].getArm().setWeight(0);

		robot[i].setName("-");
		robot[i].getTorso().setType("-");
		robot[i].getTorso().setQuality(0);
		robot[i].getTorso().setDurability(100);
		robot[i].getTorso().setForce(0);
		robot[i].getTorso().setIntelligence(0);
		robot[i].getTorso().setPrice(0);
		robot[i].getTorso().setSkill(0);
		robot[i].getTorso().setWeight(0);

		robot[i].setName("-");
		robot[i].getLeg().setType("-");
		robot[i].getLeg().setQuality(0);
		robot[i].getLeg().setDurability(100);
		robot[i].getLeg().setForce(0);
		robot[i].getLeg().setIntelligence(0);
		robot[i].getLeg().setPrice(0);
		robot[i].getLeg().setSkill(0);
		robot[i].getLeg().setWeight(0);

		robot[i].setName("-");
		robot[i].getHead().setType("-");
		robot[i].getHead().setQuality(0);
		robot[i].getHead().setDurability(100);
		robot[i].getHead().setForce(0);
		robot[i].getHead().setIntelligence(0);
		robot[i].getHead().setPrice(0);
		robot[i].getHead().setSkill(0);
		robot[i].getHead().setWeight(0);

		countRobot--;
	}

}
