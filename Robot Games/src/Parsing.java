
public class Parsing {
	private String command;
	private String tempCommand[];
	private String operation; // like by,sl,++...
	private String stock;//hd,tr...
	private int quality;//1,2
	private String operator;// m02 ya da =,>
	private String module1;
	private String module2;
	private String module3;
	private String module4;

	
	public Parsing(String command) {
		this.tempCommand = command.split(" ");

		if (tempCommand.length == 1) {
			operation = tempCommand[0];
			stock = null;
			quality = 0;
			operator = null;
			module1 = null;
			module2 = null;
			module3 = null;
			module4 = null;
			for (int i = 0; i < tempCommand.length; i++) {
				tempCommand[i] = null;
			}
		} else if (tempCommand.length == 2) {
			operation = tempCommand[0];
			stock = tempCommand[1].substring(0, 2);
			quality = Integer.parseInt(tempCommand[1].substring(2, 3));
			operator = null;
			module1 = null;
			module2 = null;
			module3 = null;
			module4 = null;
			for (int i = 0; i < tempCommand.length; i++) {
				tempCommand[i] = null;
			}
		} else if (tempCommand.length == 3) {
			operation = tempCommand[0];
			stock = tempCommand[1].substring(0, 2);
			quality = Integer.parseInt(tempCommand[1].substring(2, 3));
			operator = tempCommand[2];
			module1 = null;
			module2 = null;
			module3 = null;
			module4 = null;
			for (int i = 0; i < tempCommand.length; i++) {
				tempCommand[i] = null;
			}
		} else if (tempCommand.length == 4) {
			operation = tempCommand[0];
			stock = tempCommand[1].substring(0, 2);
			quality = Integer.parseInt(tempCommand[1].substring(2, 3));
			operator = tempCommand[2];
			module1 = tempCommand[3];
			module2 = null;
			module3 = null;
			module4 = null;
			for (int i = 0; i < tempCommand.length; i++) {
				tempCommand[i] = null;
			}
		} else if (tempCommand.length == 7) {
			operation = tempCommand[0];
			stock = tempCommand[1].substring(0, 2);
			quality = Integer.parseInt(tempCommand[1].substring(2, 3));
			operator = tempCommand[2];
			module1 = tempCommand[3];
			module2 = tempCommand[4];
			module3 = tempCommand[5];
			module4 = tempCommand[6];
			for (int i = 0; i < tempCommand.length; i++) {
				tempCommand[i] = null;
			}
		} else {
			System.out.println("Commands can't responed!");
		}
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getModule1() {
		return module1;
	}

	public void setModule1(String module1) {
		this.module1 = module1;
	}

	public String getModule2() {
		return module2;
	}

	public void setModule2(String module2) {
		this.module2 = module2;
	}

	public String getModule3() {
		return module3;
	}

	public void setModule3(String module3) {
		this.module3 = module3;
	}

	public String getModule4() {
		return module4;
	}

	public void setModule4(String module4) {
		this.module4 = module4;
	}

	
}
