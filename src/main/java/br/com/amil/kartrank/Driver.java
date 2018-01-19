package br.com.amil.kartrank;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Driver {

	private String name;

	private String cod;

	private LocalTime totalRaceTime;

	private int lapCompleted;

	private int position;

	private List<Lap> laps;

	private Lap bestLap;

	public Driver() {
	}

	public Driver(String codDriver, String name) {
		this.cod = codDriver;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public LocalTime getTotalRaceTime() {
		return totalRaceTime;
	}

	public void setTotalRaceTime(LocalTime totalRaceTime) {
		this.totalRaceTime = totalRaceTime;
	}

	public int getLapCompleted() {
		return lapCompleted;
	}

	public void setLapCompleted(int lapCompleted) {
		this.lapCompleted = lapCompleted;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<Lap> getLaps() {
		return laps;
	}

	public void setLaps(List<Lap> laps) {
		this.laps = laps;
	}

	public Lap getBestLap() {
		return bestLap;
	}

	public void setBestLap(Lap bestLap) {
		this.bestLap = bestLap;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Driver driver = (Driver) o;

		return cod.equals(driver.cod);

	}

	@Override
	public int hashCode() {
		return cod.hashCode();
	}

	@Override
	public String toString() {

		return this.getPosition() + " | " + this.getCod() + "-" + this.getName() + " | " + "Laps: "
				+ this.getLapCompleted() + " | " + "Total Time: "
				+ Processor.getFormatDateTimeHour(this.getTotalRaceTime());

	}

	public void process() {
		LocalTime localTime = null;
		int lapCompleted = 0;
		for (Lap lap : this.getLaps()) {
			if (localTime == null) {
				localTime = lap.getTimeLap();
			} else {
				localTime = localTime.plusMinutes(lap.getTimeLap().getMinute())
						.plusSeconds(lap.getTimeLap().getSecond()).plusNanos(lap.getTimeLap().getNano());
			}
			lapCompleted++;
		}
		this.setTotalRaceTime(localTime);
		this.setLapCompleted(lapCompleted);
		this.setBestLap(Collections.min(this.getLaps(), Comparator.comparing(s -> s.getTimeLap())));
	}
}
