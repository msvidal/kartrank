package br.com.amil.kartrank;

public class Program {
	public static void main(String[] args) {

		principal();

	}

	public static void principal() {

		try {
			Processor processor = new Processor();

			processor.processFile();

			processor.processRace();

			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("                            RACE RESULT                                      ");
			System.out.println("-----------------------------------------------------------------------------");

			processor.processResult();

			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("                            FASTEST LAPS                                     ");
			System.out.println("-----------------------------------------------------------------------------");

			processor.processFastestLaps();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
