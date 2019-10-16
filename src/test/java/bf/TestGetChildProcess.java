package bf;

import java.util.HashSet;

public class TestGetChildProcess {
	private static HashSet<String> processes = new HashSet<String>();
	
	public static void main(String[] args) {
		String mainPro = "5xxx";
		processes.add(mainPro);
		getChildProcess(mainPro);
		processes.forEach((str) -> System.out.println(str));
	}

	private static void getChildProcess(String mainPro) {
		String res = getRespose(mainPro);

		if (res.equals("")) {
			return;
		}

		String[] processPairs = res.split("\\r\\n");
		String[] childAndMainProcess;
		for (String p : processPairs) {
			childAndMainProcess = p.split(" ");
			if (!childAndMainProcess[0].equals(mainPro) && childAndMainProcess[1].equals(mainPro)) {
				processes.add(childAndMainProcess[0]);
				getChildProcess(childAndMainProcess[0]);
			}
		}
	}

	
	/**
	 * Result:
	 * 5xxx:
	 *     24288:
	 *     		10011,10022
	 *     24289:
	 *     		20011,20022,20033
	 *     24290:
	 * 			30011,30022,30033
	 * */
	private static String getRespose(String mainPro) {
		String result = "";
		
		switch(mainPro) {
		case "5xxx":
			result = "5xxx 28407\r\n" + 
					"24288 5xxx\r\n" + 
					"24289 5xxx\r\n" + 
					"24290 5xxx";
			break;
		case "24288":
			result = "24288 5xxx\r\n" + 
					"10011 24288\r\n" + 
					"10022 24288";
			break;
		case "24289":
			result = "24289 5xxx\r\n" + 
					"20011 24289\r\n" + 
					"20022 24289\r\n" + 
					"20033 24289";
			break;
		case "24290":
			result = "24290 5xxx\r\n" + 
					"30011 24290\r\n" + 
					"30022 24290\r\n" + 
					"30033 24290";
			break;
		default:
			break;
		}
		
		return result;
	}
	
}
