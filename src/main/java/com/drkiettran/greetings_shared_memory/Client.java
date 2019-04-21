package com.drkiettran.greetings_shared_memory;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

	public Client(String fileName, String greetings) throws IOException {
		SharedMemoryIO smIO = new SharedMemoryIO(fileName + "_out", fileName + "_in");

		LOGGER.info("Sent: {}", greetings);

		smIO.writeLine(greetings);

		LOGGER.info("Received: {}", smIO.readLine());
	}
}