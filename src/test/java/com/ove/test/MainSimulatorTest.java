package com.ove.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ove.simulator.InputCommands;
import com.ove.simulator.InputManager;
import com.ove.simulator.MainSimulator;

public class MainSimulatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testValidateQueueSize_ForNullValueOfInputs() {
		InputManager manager = new InputManager(null);
		MainSimulator simulator = new MainSimulator();
		
		
		simulator.validateQueueSize(manager);
		
	}
	
	@Test
	public void testValidateQueueSize_ForValueOfInputs() throws IllegalAccessException {
		MainSimulator sim = new MainSimulator();
		List<String> contents = sim.readFile(new File("sampledata.txt"));
        List<InputCommands> commandList = sim.commandMapper(contents);
		InputManager manager = new InputManager(commandList);
		manager.populateList();
		
		
		
		boolean result = sim.validateQueueSize(manager);
		
		assertTrue(result);
		
	}

	@Test
	public void testCommandMapper_ForEmptyInputs() throws IllegalAccessException {
		MainSimulator sim = new MainSimulator();
		List<String> contents = new ArrayList<String>();
		List<InputCommands> commandList = sim.commandMapper(contents);
		
		assertEquals(0, commandList.size());
	}
	
	@Test(expected = IllegalAccessException.class)
	public void testCommandMapper_ForWrongInputs() throws IllegalAccessException {
		MainSimulator sim = new MainSimulator();
		List<String> contents = new ArrayList<String>();
		contents.add("PLACE 1,1");
		contents.add("BLOCK 1,1");
		contents.add("RAGE 1,1");
		contents.add("PLACE 1,1");
		sim.commandMapper(contents);
		
		
	}
	
	@Test
	public void testCommandMapper_ForCorrectInputs() throws IllegalAccessException {
		MainSimulator sim = new MainSimulator();
		List<String> contents = new ArrayList<String>();
		contents.add("PLACE 1,1");
		contents.add("BLOCK 1,1");
		contents.add("BLOCK 2,1");
		contents.add("PLACE 2,2");
		
		assertEquals(4, sim.commandMapper(contents).size());
		
		
	}
}
