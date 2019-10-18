import enigma.core.Enigma;


public class test {

	public static void main(String[] args) {
		enigma.console.Console cn = Enigma.getConsole("Robot Games");
		Management m = new Management();
		int tempx = 0;
		int tempy = 23;
		while (true) {
			if(m.getPlayerTeam().getCredits()>=10000)
			{
				int count=0;
				for (int i = 0; i < m.getPlayerTeam().getRobot().length; i++) {
				if(!m.getPlayerTeam().getRobot()[i].getArm().equals("-"))
				{
					count++;;
				}
				
			}
				if(count==9){
					System.out.println("YOU WIN!!!!!!");
					break;}
				
			}
			else{
			
				
				cn.getTextWindow().setCursorPosition(0, 1);
			
			cn.getTextWindow().output("Week: "+m.getWeek()+ " Robot/Credit: T1:" + m.getPlayerTeam().credits + " T2:"
							+ m.getAiTeams()[0].credits + " T3:" + m.getAiTeams()[1].credits + " T4:"
							+ m.getAiTeams()[2].credits + " T5:" + m.getAiTeams()[3].credits + " T6:"
							+ m.getAiTeams()[4].credits);
			System.out.println();
			System.out.println("--- Teams1: Modules---");
			m.getPlayerTeam().listModule();
			System.out.println();
			System.out.println("--- Teams1: Robots---");
			m.getPlayerTeam().displayRobots();			
			System.out.println("--- Games(Registering)---");
			System.out.println("Chess: "+m.getPrizesChess()+"    Run: "+m.getPrizesRun()+"      Sumo: "+m.getPrizesSumo()+"      PingPong: "+m.getPrizesPingPong()   );
			System.out.println();
			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}
			cn.getTextWindow().setCursorPosition(tempx, tempy);
			System.out.println();
			System.out.print("Command--> ");
			String readed = cn.readLine();
			m.manage(readed);
			tempx = cn.getTextWindow().getCursorX();
			tempy = cn.getTextWindow().getCursorY();

		}
			}

	}

	

}