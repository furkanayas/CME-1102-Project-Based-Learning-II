import java.util.Random;

public class Management {
	private Parsing command;
	private Team playerTeam;
	private Team[] aiTeams;
	private double[] TeamScore;
	private double[][] scoreCompare;
	private String[][] prizes;
	private int prizesChess;
	private int prizesRun;
	private int prizesSumo;
	private int prizesPingPong;

	public Team[] getAiTeams() {
		return aiTeams;
	}

	public void setAiTeams(Team[] aiTeams) {
		this.aiTeams = aiTeams;
	}

	private int Week = 1;
	private int countWeek;
	Random rnd = new Random();

	public Management() {
		this.playerTeam = new Team();
		this.aiTeams = new Team[5];
		this.TeamScore = new double[4];
		this.scoreCompare = new double[4][6];
		for (int i = 0; i < scoreCompare.length; i++) {
			for (int j = 0; j < scoreCompare[i].length; j++) {
				scoreCompare[i][j] = 0;
			}
		}
		for (int i = 0; i < TeamScore.length; i++) {
			TeamScore[i] = 0;
		}
		for (int i = 0; i < aiTeams.length; i++) {
			aiTeams[i] = new Team();
		}
		this.prizes = new String[4][6]; 
		for (int i = 0; i < prizes.length; i++) {
			for (int j = 0; j < prizes[i].length; j++) {
				prizes[i][j] = "-";
			}
		}
		this.prizesChess = 200;
		this.prizesRun = 200;
		this.prizesSumo = 250;
		this.prizesPingPong = 250;
		aiGames();
	}

	public Team getPlayerTeam() {
		return playerTeam;
	}

	public void setPlayerTeam(Team playerTeam) {
		this.playerTeam = playerTeam;
	}

	public int weight(String module, int quality) {
		int weight = 0;
		if (module.equals("tr")) {
			weight = 100 + quality * 10;
		} else if (module.equals("hd")) {
			weight = 20 + quality * 1;
		} else if (module.equals("lg")) {
			weight = 80 + quality * 4;
		} else if (module.equals("ar")) {
			weight = 40 + quality * 2;
		}
		return weight;
	}

	public int force(String module, int quality) {
		int force = 0;
		if (module.equals("tr")) {
			force = 100 + quality * 80;
		} else if (module.equals("hd")) {
			force = 0;
		} else if (module.equals("lg")) {
			force = 100 + quality * 80;
		} else if (module.equals("ar")) {
			force = 0;
		}
		return force;
	}

	public int intelligence(String module, int quality) {
		int intelligence = 0;
		if (module.equals("tr")) {
			intelligence = 0;
		} else if (module.equals("hd")) {
			intelligence = 0;
		} else if (module.equals("lg")) {
			intelligence = 100 + quality * 80;
		} else if (module.equals("ar")) {
			intelligence = 0;
		}
		return intelligence;
	}

	public int skill(String module, int quality) {
		int skill = 0;
		if (module.equals("tr")) {
			skill = 0;
		} else if (module.equals("hd")) {
			skill = 0;
		} else if (module.equals("lg")) {
			skill = 0;
		} else if (module.equals("ar")) {
			skill = 100 + quality * 200;
		}
		return skill;
	}

	public int prices(String module, int quality) {
		int prices = 0;
		if (module.equals("tr")) {
			prices = 150 * quality;
		} else if (module.equals("hd")) {
			prices = 100 * quality;
		} else if (module.equals("lg")) {
			prices = 50 * quality;
		} else if (module.equals("ar")) {
			prices = 40 * quality;
		}
		return prices;
	}

	public void shiftingModules() {// test et
		int tempCount = 0;
		if (playerTeam.getModules()[0].equals("-")) {
			for (int i = 0; i < playerTeam.getModules().length; i++) {
				if (playerTeam.getModules()[i].getType().equals("-")) {
					tempCount++;
				} else if (!playerTeam.getModules()[i].getType().equals("-")) {
					break;
				}
			}
			for (int i = 0; i < playerTeam.getModules().length; i++) {
				if (!playerTeam.getModules()[i].getType().equals("-")) {
					playerTeam.getModules()[i + 1 - tempCount].setType(playerTeam.getModules()[i].getType());
					playerTeam.getModules()[i + 1 - tempCount].setQuality(playerTeam.getModules()[i].getQuality());
					playerTeam.getModules()[i + 1 - tempCount]
							.setDurability(playerTeam.getModules()[i].getDurability());
					playerTeam.getModules()[i + 1 - tempCount].setForce(playerTeam.getModules()[i].getForce());
					playerTeam.getModules()[i + 1 - tempCount]
							.setIntelligence(playerTeam.getModules()[i].getIntelligence());
					playerTeam.getModules()[i + 1 - tempCount].setPrice(playerTeam.getModules()[i].getPrice());
					playerTeam.getModules()[i + 1 - tempCount].setSkill(playerTeam.getModules()[i].getSkill());
					playerTeam.getModules()[i + 1 - tempCount].setWeight(playerTeam.getModules()[i].getWeight());
					playerTeam.resetModule(i);
				}
			}

		}
		/*
		 * for (int i = 0; i < playerTeam.getModules().length - 1; i++) { if
		 * (playerTeam.getModules()[i].getType().equals("-")) {
		 * 
		 * playerTeam.getModules()[i].setType(playerTeam.getModules()[i +
		 * 1].getType());
		 * playerTeam.getModules()[i].setQuality(playerTeam.getModules()[i +
		 * 1].getQuality());
		 * playerTeam.getModules()[i].setDurability(playerTeam.getModules()[i +
		 * 1].getDurability());
		 * playerTeam.getModules()[i].setForce(playerTeam.getModules()[i +
		 * 1].getForce());
		 * playerTeam.getModules()[i].setIntelligence(playerTeam.getModules()[i
		 * + 1].getIntelligence());
		 * playerTeam.getModules()[i].setPrice(playerTeam.getModules()[i +
		 * 1].getPrice());
		 * playerTeam.getModules()[i].setSkill(playerTeam.getModules()[i +
		 * 1].getSkill());
		 * playerTeam.getModules()[i].setWeight(playerTeam.getModules()[i +
		 * 1].getWeight()); playerTeam.resetModule(i);
		 * 
		 * } }
		 */
	}

	public void shiftingRobots() {

		for (int i = 0; i < playerTeam.getRobot().length; i++) {
			if (playerTeam.getRobot()[i].getName().equals(" ") && !playerTeam.getRobot()[i + 1].getName().equals(" ")) {
				playerTeam.getRobot()[i] = playerTeam.getRobot()[i + 1];
				playerTeam.getRobot()[i + 1] = null;
			} else {
				System.out.println("Robot can not shifting!");
			}
		}
	}

	public RobotModule alteredModule(String type) {
		RobotModule temp = new RobotModule();
		for (int i = 0; i < playerTeam.getModules().length; i++) {
			if ((playerTeam.getModules()[i].getType() + playerTeam.getModules()[i].getQuality()).equals(type)) {
				temp.setType(playerTeam.getModules()[i].getType());
				temp.setQuality(playerTeam.getModules()[i].getQuality());
				temp.setDurability(playerTeam.getModules()[i].getDurability());
				temp.setForce(playerTeam.getModules()[i].getForce());
				temp.setIntelligence(playerTeam.getModules()[i].getIntelligence());
				temp.setPrice(playerTeam.getModules()[i].getPrice());
				temp.setSkill(playerTeam.getModules()[i].getSkill());
				temp.setWeight(playerTeam.getModules()[i].getWeight());
			}
		}
		return temp;
	}

	public void manage(String readed) {
		this.command = new Parsing(readed);
		if (command.getOperation().equals("by")) {
			if (playerTeam.credits < 0) {
				System.out.println("Not Enough Money!!");
			} else {
				// Add module to teams modules
				if ((playerTeam.getCredits() - prices(command.getStock(), command.getQuality())) < 0) {
					System.out.println("Not Enough Money!!");
				} else {
					playerTeam.setCredits(playerTeam.getCredits() - prices(command.getStock(), command.getQuality()));
					playerTeam.addModule(command.getStock(), command.getQuality(),
							weight(command.getStock(), command.getQuality()),
							force(command.getStock(), command.getQuality()),
							intelligence(command.getStock(), command.getQuality()),
							skill(command.getStock(), command.getQuality()),
							prices(command.getStock(), command.getQuality()));
				}
				// Decreasing Team credit's

				System.out.println("Module bought!");
			}
		} else if (command.getOperation().equals("sl")) {
			if (command.getStock().equals("ro")) {
				for (int i = 0; i < playerTeam.getRobot().length; i++) {
					if (playerTeam.getRobot()[i].getName().equals(command.getStock() + command.getQuality())) {
						int pricesArm = prices(playerTeam.getRobot()[i].getArm().getType(),
								playerTeam.getRobot()[i].getArm().getQuality());
						int priceHead = prices(playerTeam.getRobot()[i].getHead().getType(),
								playerTeam.getRobot()[i].getHead().getQuality());
						int priceLeg = prices(playerTeam.getRobot()[i].getLeg().getType(),
								playerTeam.getRobot()[i].getLeg().getQuality());
						int priceTorso = prices(playerTeam.getRobot()[i].getTorso().getType(),
								playerTeam.getRobot()[i].getTorso().getQuality());
						int durabilityArm = playerTeam.getRobot()[i].getArm().getDurability();
						int durabilityHead = playerTeam.getRobot()[i].getHead().getDurability();
						int durabilityLeg = playerTeam.getRobot()[i].getLeg().getDurability();
						int durabilityTorso = playerTeam.getRobot()[i].getTorso().getDurability();

						int priceOfUsedModule = (5 / 10 * pricesArm * durabilityArm / 100)
								+ (5 / 10 * priceHead * durabilityHead / 100)
								+ (5 / 10 * priceLeg * durabilityLeg / 100)
								+ (5 / 10 * priceTorso * durabilityTorso / 100);
						playerTeam.setCredits(playerTeam.getCredits() + priceOfUsedModule);
						playerTeam.resetRobot(i);
						break;
					}
				}
			} else if (command.getStock().equals("tr") || command.getStock().equals("hd")
					|| command.getStock().equals("ar") || command.getStock().equals("lg")) {
				for (int i = 0; i < playerTeam.getModules().length; i++) {
					String moduleValue = playerTeam.getModules()[i].getType() + playerTeam.getModules()[i].getQuality();
					String commandValue = command.getStock() + command.getQuality();
					if (moduleValue.equals(commandValue)) {
						int priceOfUsedModule = (5 / 10 * playerTeam.getModules()[i].getPrice()
								* playerTeam.getModules()[i].getDurability() / 100);
						playerTeam.setCredits(playerTeam.getCredits() + priceOfUsedModule);
						playerTeam.resetModule(i);
						break;
					}
				}
				System.out.println("Module Sold");

			}

		} else if (command.getOperation().equals("++")) {
			boolean flag1 = false;
			boolean flag2 = false;
			boolean flag3 = false;
			boolean flag4 = false;
			for (int i = 0; i < playerTeam.getModules().length; i++) {
				String tempPlModule = playerTeam.getModules()[i].getType() + playerTeam.getModules()[i].getQuality();
				if (flag1 == true && flag2 == true && flag3 == true && flag4 == true) {
					break;
				}
				if (tempPlModule.equals(command.getModule1())) {
					flag1 = true;
				} else if (tempPlModule.equals(command.getModule2())) {
					flag2 = true;
				} else if (tempPlModule.equals(command.getModule3())) {
					flag3 = true;
				} else if (tempPlModule.equals(command.getModule4())) {
					flag4 = true;
				}
			}
			if (flag1 == true && flag2 == true && flag3 == true && flag4 == true) {
				for (int i = 0; i < playerTeam.getRobot().length; i++) {
					if (playerTeam.getRobot()[i].getName() == "-") {
						playerTeam.addRobot(command.getStock() + command.getQuality(), command.getModule1(),
								command.getModule2(), command.getModule3(), command.getModule4());
						break;

					}
				}
				System.out.println("Robot Created!");

			}

		} else if (command.getOperation().equals("--")) {
			for (int i = 0; i < playerTeam.getRobot().length; i++) {
				if (playerTeam.getRobot()[i].getName().equals(command.getStock() + command.getQuality())) {
					playerTeam.dividedModule(playerTeam.getRobot()[i].arm);
					playerTeam.dividedModule(playerTeam.getRobot()[i].head);
					playerTeam.dividedModule(playerTeam.getRobot()[i].leg);
					playerTeam.dividedModule(playerTeam.getRobot()[i].torso);
					playerTeam.resetRobot(i);
				}
				shiftingModules();

				// shiftingRobots();
			}
			System.out.println("Robot Divided!");

		} else if (command.getOperation().equals("ch")) {
			String tempArm = null;
			String tempTorso = null;
			String tempLeg = null;
			String tempHead = null;
			for (int i = 0; i < playerTeam.getRobot().length; i++) {
				if (playerTeam.getRobot()[i].getName().equals(command.getStock() + command.getQuality())) {
					tempArm = playerTeam.getRobot()[i].getArm().getType();
					tempTorso = playerTeam.getRobot()[i].getTorso().getType();
					tempLeg = playerTeam.getRobot()[i].getLeg().getType();
					tempHead = playerTeam.getRobot()[i].getHead().getType();

					if (tempArm.equals(command.getOperator().substring(0, 2))) {

						playerTeam.dividedModule(playerTeam.getRobot()[i].getArm());
						playerTeam.getRobot()[i].setArm(alteredModule(command.getOperator()));
						System.out.println("Module changed succesfully!");

					} else if (tempTorso.equals(command.getOperator())) {

						playerTeam.dividedModule(playerTeam.getRobot()[i].getTorso());
						playerTeam.getRobot()[i].setTorso(alteredModule(command.getOperator()));
						System.out.println("Module changed succesfully!");
					} else if (tempLeg.equals(command.getOperator())) {

						playerTeam.dividedModule(playerTeam.getRobot()[i].getLeg());
						playerTeam.getRobot()[i].setLeg(alteredModule(command.getOperator()));
						System.out.println("Module changed succesfully!");
					} else if (tempHead.equals(command.getOperator())) {

						playerTeam.dividedModule(playerTeam.getRobot()[i].getHead());
						playerTeam.getRobot()[i].setHead(alteredModule(command.getOperator()));
						System.out.println("Module changed succesfully!");
					} else {
						System.out.println("Error!");
					}
				}

			}

		} else if (command.getOperation().equals("ls")) {
			for (int i = 0; i < playerTeam.getModules().length; i++) {
				System.out.println(playerTeam.getModules()[i].getType());
				System.out.println(" ");
			}
		} else if (command.getOperation().equals("rg")) {
			Robots tempRobot = new Robots();
			for (int i = 0; i < playerTeam.getRobot().length; i++) {
				String tempRName = command.getStock() + command.getQuality();
				if (playerTeam.getRobot()[i].getName().equals(tempRName)) {
					tempRobot.setName(playerTeam.getRobot()[i].getName());
					tempRobot.setArm(playerTeam.getRobot()[i].getArm());
					tempRobot.setHead(playerTeam.getRobot()[i].getHead());
					tempRobot.setLeg(playerTeam.getRobot()[i].getLeg());
					tempRobot.setTorso(playerTeam.getRobot()[i].getTorso());
					break;
				}
			}

			String playerRobot = command.getStock() + command.getQuality();

			for (int i = 0; i < 5; i++) {
				int k = rnd.nextInt(5) + 1;

			}
			scoreCompare[0] = registerScore("playerTeam", convertTeam(playerTeam, playerRobot),
					command.getModule1().substring(0, 1), Integer.parseInt(command.getModule1().substring(1, 2)));
			prize(prizes);
			scoreCompare[1] = registerScore("aiTeams[1]", aiTeams[1].getRobot()[0], "c", 1);
			prize(prizes);
			scoreCompare[2] = registerScore("aiTeams[0]", aiTeams[0].getRobot()[0], "r", 1);
			prize(prizes);
			scoreCompare[3] = registerScore("aiTeams[2]", aiTeams[2].getRobot()[0], "c", 1);
			prize(prizes);

		} else if (command.getOperation().equals("pl")) {

		} else if (command.getOperation().equals("Start!")) {
			System.out.println("Start!");
		} else {
			System.out.println("Wrong entry!");
		}

	}

	public double[] registerScore(String teamName, Robots playerRobot, String games, int order) {
		double retVal = 0;
		double[] score = new double[4];
		if (games.equals("c")) {
			if (order == 1) {
				retVal = playerRobot.getHead().getIntelligence() * (playerRobot.getHead().getDurability()) / 100;
			} else if (order > 1) {
				retVal = (playerRobot.getHead().getIntelligence() * (playerRobot.getHead().getDurability()) / 100)
						/ (4 * (order - 1));
			}
			TeamScore[0] += retVal;

			prizes[0][0] = "c";
			for (int i = 0; i < prizes[0].length; i++) {
				if (!prizes[0][i].equals(teamName)) {
					prizes[0][i] = teamName;
					break;
				}
			}
		} else if (games.equals("r")) {
			int totalWeight = (playerRobot.getArm().getWeight() + playerRobot.getHead().getWeight()
					+ playerRobot.getTorso().getWeight() + playerRobot.getLeg().getWeight());
			if (order == 1) {
				retVal = ((250 * playerRobot.getLeg().getForce()) / totalWeight * playerRobot.getLeg().getDurability())
						/ 100;
			} else if (order > 1) {
				retVal = ((250 * playerRobot.getLeg().getForce()) / totalWeight * playerRobot.getLeg().getDurability()
						/ 100) / (4 * (order - 1));
			}
			TeamScore[1] += retVal;
			prizes[1][0] = "r";
			for (int i = 0; i < prizes[1].length; i++) {
				if (!prizes[1][i].equals(teamName)) {
					prizes[1][i] = teamName;
					break;
				}
			}
		} else if (games.equals("s")) {
			if (order == 1) {
				retVal = (playerRobot.getTorso().getForce() * playerRobot.getTorso().getDurability() / 100 * 0.7)
						+ (playerRobot.getLeg().getForce() * playerRobot.getLeg().getDurability() / 100 * 0.3);
			} else if (order > 1) {
				retVal = (playerRobot.getTorso().getForce() * playerRobot.getTorso().getDurability() / 100 * 0.7)
						+ (playerRobot.getLeg().getForce() * playerRobot.getLeg().getDurability() / 100 * 0.3)
								/ (4 * (order - 1));
			}
			TeamScore[2] += retVal;
			prizes[2][0] = "s";
			for (int i = 0; i < prizes[2].length; i++) {
				if (!prizes[2][i].equals(teamName)) {
					prizes[2][i] = teamName;
					break;
				}
			}
		} else if (games.equals("p")) {
			int totalWeight = (playerRobot.getArm().getWeight() + playerRobot.getHead().getWeight()
					+ playerRobot.getTorso().getWeight() + playerRobot.getLeg().getWeight());
			if (order == 1) {

				retVal = (playerRobot.getArm().getSkill() * playerRobot.getArm().getDurability() / 100 * 0.6)
						+ (playerRobot.getHead().getIntelligence() * (playerRobot.getHead().getDurability()) / 100
								* 0.2)
						+ (((250 * playerRobot.getLeg().getForce()) / totalWeight
								* playerRobot.getLeg().getDurability()) / 100 * 0.2);

			} else if (order > 1) {
				retVal = ((playerRobot.getArm().getSkill() * playerRobot.getArm().getDurability() / 100 * 0.6)
						+ (playerRobot.getHead().getIntelligence() * (playerRobot.getHead().getDurability()) / 100
								* 0.2)
						+ (((250 * playerRobot.getLeg().getForce()) / totalWeight
								* playerRobot.getLeg().getDurability()) / 100 * 0.2))
						/ (4 * (order - 1));
			}
			TeamScore[3] += retVal;

			prizes[3][0] = "p";
			for (int i = 0; i < prizes[3].length; i++) {
				if (!prizes[3][i].equals(teamName)) {
					prizes[3][i] = teamName;
					break;
				}
			}
		}
		score[0] = TeamScore[0];
		score[1] = TeamScore[1];
		score[2] = TeamScore[0];
		score[3] = TeamScore[0];
		return score;
	}

	public void prize(String[][] prizes) {
		int countChess = 0;
		int countRun = 0;
		int countSumo = 0;
		int countPingPong = 0;
		for (int i = 0; i < prizes[0].length; i++) {
			if (!prizes[0][i].equals("-")) {
				countChess++;
				break;
			}
		}
		for (int i = 0; i < prizes[1].length; i++) {
			if (!prizes[1][i].equals("-")) {
				countRun++;
				break;
			}
		}
		for (int i = 0; i < prizes[2].length; i++) {
			if (!prizes[2][i].equals("-")) {
				countSumo++;
				break;
			}
		}
		for (int i = 0; i < prizes[3].length; i++) {
			if (!prizes[3][i].equals("-")) {
				countPingPong++;
				break;
			}
		}
		prizesChess += countChess * 25;
		prizesRun += countRun * 30;
		prizesSumo += countSumo * 35;
		prizesPingPong += countPingPong * 40;
	}

	public int getPrizesChess() {
		return prizesChess;
	}

	public void setPrizesChess(int prizesChess) {
		this.prizesChess = prizesChess;
	}

	public int getPrizesRun() {
		return prizesRun;
	}

	public void setPrizesRun(int prizesRun) {
		this.prizesRun = prizesRun;
	}

	public int getPrizesSumo() {
		return prizesSumo;
	}

	public void setPrizesSumo(int prizesSumo) {
		this.prizesSumo = prizesSumo;
	}

	public int getPrizesPingPong() {
		return prizesPingPong;
	}

	public void setPrizesPingPong(int prizesPingPong) {
		this.prizesPingPong = prizesPingPong;
	}

	public void aiGames() {
		aiTeams[0].addModule("ar", 1, weight("ar", 1), force("ar", 1), intelligence("ar", 1), skill("ar", 1),
				prices("ar", 1));
		aiTeams[0].addModule("tr", 1, weight("tr", 1), force("tr", 1), intelligence("tr", 1), skill("tr", 1),
				prices("tr", 1));
		aiTeams[0].addModule("hd", 1, weight("hd", 1), force("hd", 1), intelligence("hd", 1), skill("hd", 1),
				prices("hd", 1));
		/* 3 */aiTeams[0].addModule("lg", 1, weight("lg", 1), force("lg", 1), intelligence("lg", 1), skill("lg", 1),
				prices("lg", 1));

		aiTeams[0].addModule("ar", 5, weight("ar", 5), force("ar", 5), intelligence("ar", 5), skill("ar", 5),
				prices("ar", 5));
		/* 5 */aiTeams[0].addModule("tr", 5, weight("tr", 5), force("tr", 5), intelligence("tr", 5), skill("tr", 5),
				prices("tr", 5));
		/* 6 */aiTeams[0].addModule("hd", 5, weight("hd", 5), force("hd", 5), intelligence("hd", 5), skill("hd", 5),
				prices("hd", 5));
		aiTeams[0].addModule("lg", 5, weight("lg", 5), force("lg", 5), intelligence("lg", 5), skill("lg", 5),
				prices("lg", 5));

		/* 8 */aiTeams[0].addModule("ar", 3, weight("ar", 3), force("ar", 3), intelligence("ar", 3), skill("ar", 3),
				prices("ar", 3));
		aiTeams[0].addModule("tr", 3, weight("tr", 3), force("tr", 3), intelligence("tr", 3), skill("tr", 3),
				prices("tr", 3));
		aiTeams[0].addModule("hd", 3, weight("hd", 3), force("hd", 3), intelligence("hd", 3), skill("hd", 3),
				prices("hd", 3));
		aiTeams[0].addModule("lg", 3, weight("lg", 3), force("lg", 3), intelligence("lg", 3), skill("lg", 3),
				prices("lg", 3));

		aiTeams[0].addModule("ar", 6, weight("ar", 6), force("ar", 6), intelligence("ar", 6), skill("ar", 6),
				prices("ar", 6));
		aiTeams[0].addModule("tr", 6, weight("tr", 6), force("tr", 6), intelligence("tr", 6), skill("tr", 6),
				prices("tr", 6));
		aiTeams[0].addModule("hd", 6, weight("hd", 6), force("hd", 6), intelligence("hd", 6), skill("hd", 6),
				prices("hd", 6));
		aiTeams[0].addModule("lg", 6, weight("lg", 6), force("lg", 6), intelligence("lg", 6), skill("lg", 6),
				prices("lg", 6));

		aiTeams[0].getRobot()[0].setName("ro1");
		aiTeams[0].getRobot()[0].setArm(aiTeams[0].getModules()[12]);
		aiTeams[0].getRobot()[0].setHead(aiTeams[0].getModules()[10]);
		aiTeams[0].getRobot()[0].setLeg(aiTeams[0].getModules()[15]);
		aiTeams[0].getRobot()[0].setTorso(aiTeams[0].getModules()[9]);

		aiTeams[0].getRobot()[1].setName("ro2");
		aiTeams[0].getRobot()[1].setArm(aiTeams[0].getModules()[0]);
		aiTeams[0].getRobot()[1].setHead(aiTeams[0].getModules()[14]);
		aiTeams[0].getRobot()[1].setLeg(aiTeams[0].getModules()[7]);
		aiTeams[0].getRobot()[1].setTorso(aiTeams[0].getModules()[1]);

		aiTeams[0].getRobot()[2].setName("ro3");
		aiTeams[0].getRobot()[2].setArm(aiTeams[0].getModules()[4]);
		aiTeams[0].getRobot()[2].setHead(aiTeams[0].getModules()[2]);
		aiTeams[0].getRobot()[2].setLeg(aiTeams[0].getModules()[11]);
		aiTeams[0].getRobot()[2].setTorso(aiTeams[0].getModules()[13]);

		//////////////////////////////////// team 1\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		aiTeams[1].addModule("ar", 4, weight("ar", 4), force("ar", 4), intelligence("ar", 4), skill("ar", 4),
				prices("ar", 4));
		aiTeams[1].addModule("tr", 4, weight("tr", 4), force("tr", 4), intelligence("tr", 4), skill("tr", 4),
				prices("tr", 4));
		aiTeams[1].addModule("hd", 4, weight("hd", 4), force("hd", 4), intelligence("hd", 4), skill("hd", 4),
				prices("hd", 4));
		aiTeams[1].addModule("lg", 4, weight("lg", 4), force("lg", 4), intelligence("lg", 4), skill("lg", 4),
				prices("lg", 4));

		aiTeams[1].addModule("ar", 5, weight("ar", 3), force("ar", 3), intelligence("ar", 3), skill("ar", 3),
				prices("ar", 3));
		aiTeams[1].addModule("tr", 5, weight("tr", 3), force("tr", 3), intelligence("tr", 3), skill("tr", 3),
				prices("tr", 3));
		aiTeams[1].addModule("hd", 5, weight("hd", 3), force("hd", 3), intelligence("hd", 3), skill("hd", 3),
				prices("hd", 3));
		aiTeams[1].addModule("lg", 5, weight("lg", 3), force("lg", 3), intelligence("lg", 3), skill("lg", 3),
				prices("lg", 3));

		aiTeams[1].getRobot()[0].setName("ro1");
		aiTeams[1].getRobot()[0].setArm(aiTeams[1].getModules()[4]);
		aiTeams[1].getRobot()[0].setHead(aiTeams[1].getModules()[6]);
		aiTeams[1].getRobot()[0].setLeg(aiTeams[1].getModules()[7]);
		aiTeams[1].getRobot()[0].setTorso(aiTeams[1].getModules()[5]);

		/////////////////////////////// team 2\\\\\\\\\\\\\\\\\\\
		aiTeams[2].addModule("ar", 4, weight("ar", 4), force("ar", 4), intelligence("ar", 4), skill("ar", 4),
				prices("ar", 4));
		aiTeams[2].addModule("tr", 4, weight("tr", 4), force("tr", 4), intelligence("tr", 4), skill("tr", 4),
				prices("tr", 4));
		aiTeams[2].addModule("hd", 4, weight("hd", 4), force("hd", 4), intelligence("hd", 4), skill("hd", 4),
				prices("hd", 4));
		aiTeams[2].addModule("lg", 4, weight("lg", 4), force("lg", 4), intelligence("lg", 4), skill("lg", 4),
				prices("lg", 4));

		aiTeams[2].addModule("ar", 2, weight("ar", 2), force("ar", 2), intelligence("ar", 2), skill("ar", 2),
				prices("ar", 2));
		aiTeams[2].addModule("tr", 2, weight("tr", 2), force("tr", 2), intelligence("tr", 2), skill("tr", 2),
				prices("tr", 2));
		aiTeams[2].addModule("hd", 2, weight("hd", 2), force("hd", 2), intelligence("hd", 2), skill("hd", 2),
				prices("hd", 2));
		aiTeams[2].addModule("lg", 2, weight("lg", 2), force("lg", 2), intelligence("lg", 2), skill("lg", 2),
				prices("lg", 2));

		aiTeams[2].addModule("ar", 5, weight("ar", 5), force("ar", 5), intelligence("ar", 5), skill("ar", 5),
				prices("ar", 5));
		aiTeams[2].addModule("tr", 5, weight("tr", 5), force("tr", 5), intelligence("tr", 5), skill("tr", 5),
				prices("tr", 5));
		aiTeams[2].addModule("hd", 5, weight("hd", 5), force("hd", 5), intelligence("hd", 5), skill("hd", 5),
				prices("hd", 5));
		aiTeams[2].addModule("lg", 5, weight("lg", 5), force("lg", 5), intelligence("lg", 5), skill("lg", 5),
				prices("lg", 5));

		aiTeams[2].getRobot()[0].setName("ro1");
		aiTeams[2].getRobot()[0].setArm(aiTeams[2].getModules()[0]);
		aiTeams[2].getRobot()[0].setHead(aiTeams[2].getModules()[2]);
		aiTeams[2].getRobot()[0].setLeg(aiTeams[2].getModules()[3]);
		aiTeams[2].getRobot()[0].setTorso(aiTeams[2].getModules()[1]);

		aiTeams[2].getRobot()[0].setName("ro2");
		aiTeams[2].getRobot()[0].setArm(aiTeams[2].getModules()[8]);
		aiTeams[2].getRobot()[0].setHead(aiTeams[2].getModules()[10]);
		aiTeams[2].getRobot()[0].setLeg(aiTeams[2].getModules()[11]);
		aiTeams[2].getRobot()[0].setTorso(aiTeams[2].getModules()[9]);

		///////////////////////////////// team3\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		aiTeams[3].addModule("ar", 3, weight("ar", 3), force("ar", 3), intelligence("ar", 3), skill("ar", 3),
				prices("ar", 3));
		aiTeams[3].addModule("tr", 3, weight("tr", 3), force("tr", 3), intelligence("tr", 3), skill("tr", 3),
				prices("tr", 3));
		aiTeams[3].addModule("hd", 3, weight("hd", 3), force("hd", 3), intelligence("hd", 3), skill("hd", 3),
				prices("hd", 3));
		aiTeams[3].addModule("lg", 3, weight("lg", 3), force("lg", 3), intelligence("lg", 3), skill("lg", 3),
				prices("lg", 3));

		aiTeams[3].getRobot()[0].setName("ro1");
		aiTeams[3].getRobot()[0].setArm(aiTeams[3].getModules()[0]);
		aiTeams[3].getRobot()[0].setHead(aiTeams[3].getModules()[2]);
		aiTeams[3].getRobot()[0].setLeg(aiTeams[3].getModules()[3]);
		aiTeams[3].getRobot()[0].setTorso(aiTeams[3].getModules()[1]);

		////////////////////////////////////// team4\\\\\\\\\\\\\\\\\\\\\\
		aiTeams[4].addModule("ar", 4, weight("ar", 4), force("ar", 4), intelligence("ar", 4), skill("ar", 4),
				prices("ar", 4));
		aiTeams[4].addModule("tr", 4, weight("tr", 4), force("tr", 4), intelligence("tr", 4), skill("tr", 4),
				prices("tr", 4));
		aiTeams[4].addModule("hd", 4, weight("hd", 4), force("hd", 4), intelligence("hd", 4), skill("hd", 4),
				prices("hd", 4));
		aiTeams[4].addModule("lg", 4, weight("lg", 4), force("lg", 4), intelligence("lg", 4), skill("lg", 4),
				prices("lg", 4));

		aiTeams[4].addModule("ar", 3, weight("ar", 3), force("ar", 3), intelligence("ar", 3), skill("ar", 3),
				prices("ar", 3));
		aiTeams[4].addModule("tr", 3, weight("tr", 3), force("tr", 3), intelligence("tr", 3), skill("tr", 3),
				prices("tr", 3));
		aiTeams[4].addModule("hd", 3, weight("hd", 3), force("hd", 3), intelligence("hd", 3), skill("hd", 3),
				prices("hd", 3));
		aiTeams[4].addModule("lg", 3, weight("lg", 3), force("lg", 3), intelligence("lg", 3), skill("lg", 3),
				prices("lg", 3));

		aiTeams[4].addModule("ar", 6, weight("ar", 6), force("ar", 6), intelligence("ar", 6), skill("ar", 6),
				prices("ar", 6));
		aiTeams[4].addModule("tr", 6, weight("tr", 6), force("tr", 6), intelligence("tr", 6), skill("tr", 6),
				prices("tr", 6));
		aiTeams[4].addModule("hd", 6, weight("hd", 6), force("hd", 6), intelligence("hd", 6), skill("hd", 6),
				prices("hd", 6));
		aiTeams[4].addModule("lg", 6, weight("lg", 6), force("lg", 6), intelligence("lg", 6), skill("lg", 6),
				prices("lg", 6));

		aiTeams[4].getRobot()[0].setName("ro1");
		aiTeams[4].getRobot()[0].setArm(aiTeams[4].getModules()[8]);
		aiTeams[4].getRobot()[0].setHead(aiTeams[4].getModules()[10]);
		aiTeams[4].getRobot()[0].setLeg(aiTeams[4].getModules()[11]);
		aiTeams[4].getRobot()[0].setTorso(aiTeams[4].getModules()[9]);

		aiTeams[4].getRobot()[0].setName("ro1");
		aiTeams[4].getRobot()[0].setArm(aiTeams[4].getModules()[4]);
		aiTeams[4].getRobot()[0].setHead(aiTeams[4].getModules()[6]);
		aiTeams[4].getRobot()[0].setLeg(aiTeams[4].getModules()[7]);
		aiTeams[4].getRobot()[0].setTorso(aiTeams[4].getModules()[5]);
	}

	public void play() {
		Week++;
		// team skorları 0.95 ile 1,05 arası random ile çarpılacak
		for (int i = 0; i < 4; i++) {
			double rand = 0.95;
			double rando = 1.05;
			double k = rand + (rando - rand) * rnd.nextDouble();
			TeamScore[i] = TeamScore[i] * k;
		}
		// Team skorları karşılaştırlıp ödül dağıtılacak
		/*double tempScore = 0;
		String [][] teamName = new String[4][2];
		int game=0;
		for (int i = 0; i < scoreCompare.length; i++) {
			for (int j = 0; j < scoreCompare[i].length; j++) {				
				if (tempScore < scoreCompare[i][j]) {
					tempScore = scoreCompare[i][j];
					if (j == 0) {
						teamName[0][j] = Integer.toString(i);
						teamName[i][0] = "Player Team"; ben uyumamda 8 geç gibi sen bana kodu at şimdi bakarım bolmuyor
					}
					else{
						teamName[1][i] = Integer.toString(i);
						teamName[1][i] = "Player Team";
					}
				}
			}
			if(teamName.equals("Player Team")){
				if (game == 0) {
					playerTeam.credits+=prizesChess;
				}
				else if (game == 1) {
					playerTeam.credits+=prizesRun;
				}
				else if (game == 2) {
					playerTeam.credits+=prizesSumo;
				}
				else if (game == 3) {
					playerTeam.credits+=prizesPingPong;
				}
			}
			else if (teamName.equals("Computer Team-1")) {
				
			}
		}*/
		
		
		if (Week % 3 == 0 && countWeek >= 2)// her 3 haftada 2 yarışa giren 150
											// kredi alıyor
		{
			playerTeam.credits += 150;
			countWeek = 0;
		}

		for (int i = 0; i < playerTeam.getRobot().length; i++) {
			playerTeam.getRobot()[i].getArm().setDurability(playerTeam.getRobot()[i].getArm().getDurability() - 2);
			playerTeam.getRobot()[i].getHead().setDurability(playerTeam.getRobot()[i].getHead().getDurability() - 2);
			playerTeam.getRobot()[i].getTorso().setDurability(playerTeam.getRobot()[i].getTorso().getDurability() - 2);
			playerTeam.getRobot()[i].getLeg().setDurability(playerTeam.getRobot()[i].getLeg().getDurability() - 2);
		}
		for (int i = 0; i < playerTeam.getModules().length; i++) {
			if (playerTeam.getModules()[i].getQuality() != 0) {
				playerTeam.getModules()[i].setDurability(playerTeam.getModules()[i].getDurability() - 2);
			}
		}
		for (int i = 0; i < 4; i++) {
			TeamScore[i] = 0;

		}
	}

	public int getWeek() {
		return Week;
	}

	public void setWeek(int week) {
		Week = week;
	}

	public Robots convertTeam(Team team, String playerRobot) {
		Robots tempRobot2 = new Robots();
		for (int j = 0; j < playerTeam.getRobot().length; j++) {
			if (team.getRobot()[j].getName().equals(playerRobot)) {
				tempRobot2.setName(team.getRobot()[j].getName());
				tempRobot2.setArm(team.getRobot()[j].getArm());
				tempRobot2.setHead(team.getRobot()[j].getHead());
				tempRobot2.setTorso(team.getRobot()[j].getTorso());
			}
		}
		return tempRobot2;
	}
}
