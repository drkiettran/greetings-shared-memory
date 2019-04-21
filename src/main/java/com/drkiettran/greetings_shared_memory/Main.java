package com.drkiettran.greetings_shared_memory;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String... args) throws IOException {
		if (args.length < 2) {
			LOGGER.info("**** java edu.harrisburgu.cisc525.app.Main server sm-name | client sm-name <text> ****");
			return;
		}

		LOGGER.info("mode: {}", args[0]);

		if (args[0].equalsIgnoreCase("client")) {
			if (args.length < 3) {
				LOGGER.info("**** java edu.harrisburgu.cisc525.app.Main client sm-name <text> ****");
				return;
			}
			new Client(args[1], args[2]);
		} else if (args[0].equalsIgnoreCase("server")) {
			if (args.length < 2) {
				LOGGER.info("**** java edu.harrisburgu.cisc525.app.Main server port ****");
				return;
			}
			new Server(args[1]);
		} else {
			LOGGER.info(">>> java edu.harrisburgu.cisc525.app.Main server sm-name | client sm-name <text> <<<");
		}
	}
}