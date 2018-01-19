package br.com.amil.kartrank.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.amil.kartrank.Processor;

public class ProcessorTest {

	@Test
	public void processFile() {

		new Processor().processFile();

		assertTrue(Processor.getLapList().size() > 0);

	}

	@Test
	public void processRace() {

		new Processor().processRace();

		assertTrue(Processor.getDriverList().size() > 0);

	}
}
