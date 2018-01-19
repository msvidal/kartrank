package br.com.amil.kartrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Processor {

	private static List<Lap> lapList;

	public static List<Lap> getLapList() {
		return lapList == null ? lapList = new ArrayList<Lap>() : lapList;
	}

	private static List<Driver> driverList;

	public static List<Driver> getDriverList() {
		return driverList == null ? driverList = new ArrayList<Driver>() : driverList;
	}

	public void processFile() {

		BufferedReader bufferedReader = null;
		InputStream in = Program.class.getResourceAsStream("Input");
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				processLine(line);
			}
			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void processLine(String line) {
		String arr[] = line.split("[\\s]");
		String arr2[] = new String[7];
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].equalsIgnoreCase("")) {
				arr2[j++] = arr[i];
			}
		}

		LocalTime hourStartLap = parseLocalTimeHour(arr2[0]);
		LocalTime timeLap = parseLocalTimeMinute(arr2[5]);
		Integer numberLap = Integer.parseInt(arr2[4]);

		getLapList().add(new Lap(hourStartLap, timeLap, numberLap, new Driver(arr2[1], arr2[3])));

	}

	public void processRace() {
		Map<Driver, List<Lap>> mapDriverListMap = getLapList().stream()
				.collect(Collectors.groupingBy(Lap::getDriverLap));

		for (Map.Entry<Driver, List<Lap>> entry : mapDriverListMap.entrySet()) {
			Driver driver = entry.getKey();
			driver.setLaps(entry.getValue());
			driver.process();
			getDriverList().add(driver);
		}
	}

	public void processResult() {
		getDriverList().sort((p1, p2) -> {
			if (p1.getLapCompleted() > p2.getLapCompleted()) {
				return -1;
			} else if (p1.getLapCompleted() < p2.getLapCompleted()) {
				return 1;
			}
			return p1.getTotalRaceTime().compareTo(p2.getTotalRaceTime());
		});

		int position = 1;
		for (Driver driver : getDriverList()) {
			driver.setPosition(position);
			position++;
		}

		for (Driver driver : getDriverList()) {
			System.out.println(driver);
		}

	}

	public void processFastestLaps() {

		getDriverList().sort((p1, p2) -> {
			return p1.getBestLap().getTimeLap().compareTo(p2.getBestLap().getTimeLap());
		});

		for (Driver driver : getDriverList()) {
			System.out.println(driver.getCod() + "-" + driver.getName() + " | " + "Best Lap: "
					+ driver.getBestLap().getNumberLap() + "-->" + driver.getBestLap().getTimeLap());
		}
	}

	public static Float formatNumberFloat(String number) {
		float floatValue = -1;

		try {
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			Number numberFormated = format.parse(number);
			floatValue = numberFormated.floatValue();

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return floatValue;
	}

	public static LocalTime parseLocalTimeHour(String time) {
		return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
	}

	public static LocalTime parseLocalTimeMinute(String time) {
		time = String.format("%9s", time).replace(" ", "00:0");
		return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
	}

	public static String getFormatDateTimeHour(LocalTime localTime) {
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS");
		return localTime.format(dtFormatter);
	}

}
