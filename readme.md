# greetings-shared-memory project

This project is a spin off my-app application. The code is splitted into
a server and a client pieces that communicates over shared-memory using nio.

## Run

To run tests:

```bash

mvn clean test
mvn clean package
jar -t target/greetings-shared-memory-jar-with-dependencies.jar

java -cp target/greetings-shared-memory-jar-with-dependencies.jar com.drkiettran.greetings_shared_memory.Main server sm

java -cp target/greetings-shared-memory-jar-with-dependencies.jar com.drkiettran.greetings_shared_memory.Main client sm "John Doe"

```

The output should be, ` Received Hello, John Doe from SaintGoretti/49692@SaintGoretti from host-name/xxxxx@SaintGoretti`
