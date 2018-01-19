package br.com.amil.kartrank;

import java.time.LocalTime;

public class Lap {

	private LocalTime hourStartLap;

	private LocalTime timeLap;

	private Integer numberLap;

	private Driver driverLap;

	public Lap() {
	}

	public Lap(LocalTime hourStartLap, LocalTime timeLap, Integer numberLap, Driver driverLap) {
		this.hourStartLap = hourStartLap;
		this.timeLap = timeLap;
		this.numberLap = numberLap;
		this.driverLap = driverLap;
	}

	public LocalTime getHourStartLap() {
		return hourStartLap;
	}

	public void setHourStartLap(LocalTime hourStartLap) {
		this.hourStartLap = hourStartLap;
	}

	public Integer getNumberLap() {
		return numberLap;
	}

	public void setNumberLap(Integer numberLap) {
		this.numberLap = numberLap;
	}

	public Driver getDriverLap() {
		return driverLap;
	}

	public void setDriverLap(Driver driverLap) {
		this.driverLap = driverLap;
	}

	public LocalTime getTimeLap() {
		return timeLap;
	}

	public void setTimeLap(LocalTime timeLap) {
		this.timeLap = timeLap;
	}

	public Integer getTimeLapMilisecond() {
		return this.getTimeLap().getNano();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Lap lap = (Lap) o;

		if (!numberLap.equals(lap.numberLap))
			return false;
		return driverLap.equals(lap.driverLap);

	}

	@Override
	public int hashCode() {
		int result = numberLap.hashCode();
		result = 31 * result + driverLap.hashCode();
		return result;
	}

}
