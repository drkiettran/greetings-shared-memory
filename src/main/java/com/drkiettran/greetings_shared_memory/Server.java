package com.drkiettran.greetings_shared_memory;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

	public Server(String fileName) throws IOException {
		while (true) {
			SharedMemoryIO smIO = new SharedMemoryIO(fileName + "_in", fileName + "_out");

			String line = smIO.readLine();
			LOGGER.info("Received: {}", line);

			Greetings greetings = new Greetings();
			String returnMsg = greetings.hello(line);
			smIO.writeLine(returnMsg);
			LOGGER.info("Sent: {}", returnMsg);
		}
	}
}
