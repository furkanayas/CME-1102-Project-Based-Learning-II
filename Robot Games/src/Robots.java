public class Robots {
	String name;
	RobotModule arm;
	RobotModule torso;
	RobotModule leg;
	RobotModule head;

	public Robots(String name, RobotModule arm, RobotModule torso, RobotModule leg, RobotModule head) {
		this.name = name;
		this.arm = arm;
		this.torso = torso;
		this.leg = leg;
		this.head = head;
	}
	public Robots(){
		this.name = "-";
		this.arm = new RobotModule();
		this.torso = new RobotModule();
		this.leg = new RobotModule();
		this.head = new RobotModule();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RobotModule getArm() {
		return arm;
	}

	public void setArm(RobotModule arm) {
		this.arm = arm;
	}

	public RobotModule getTorso() {
		return torso;
	}

	public void setTorso(RobotModule torso) {
		this.torso = torso;
	}

	public RobotModule getLeg() {
		return leg;
	}

	public void setLeg(RobotModule leg) {
		this.leg = leg;
	}

	public RobotModule getHead() {
		return head;
	}

	public void setHead(RobotModule head) {
		this.head = head;
	}

	public String listRobot() {
		String retVal = null;
		if (!name.equals("-")) {
			retVal = "Arm: " + arm.getType() + " " + "Torso: " + " " + torso.getType() + " " + "Leg: " + leg.getType() + " "
					+ "Head: " + head.getType();
		}
		
		return retVal;
	}

}
