package com.drkiettran.greetings_shared_memory;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedMemoryIO {
	private static final long MAX_BUF_SIZE = 4096;

	CharBuffer inBuf = null, outBuf = null;
	private FileChannel inChannel;
	private FileChannel outChannel;
	private MappedByteBuffer outMap;
	private MappedByteBuffer inMap;

	public SharedMemoryIO(String inFileName, String outFileName) throws IOException {
		File inFile = new File(inFileName);
		File outFile = new File(outFileName);

		if (inFile.exists()) {
			inFile.delete();
		}
		if (outFile.exists()) {
			outFile.delete();
		}

		inChannel = FileChannel.open(inFile.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);
		outChannel = FileChannel.open(outFile.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);

		inMap = inChannel.map(MapMode.READ_WRITE, 0, MAX_BUF_SIZE);
		outMap = outChannel.map(MapMode.READ_WRITE, 0, MAX_BUF_SIZE);

		inBuf = inMap.asCharBuffer();
		outBuf = outMap.asCharBuffer();
	}

	public String readLine() {
		StringBuilder sb = new StringBuilder();
		while (inBuf.get(0) == '\0') {
			sleep(1);
		}

		int index = 0;

		while (inBuf.get(index) != '\0' && index < MAX_BUF_SIZE) {
			sb.append(inBuf.get(index++));
		}

		inBuf.put('\0');
		return sb.toString();
	}

	public void writeLine(String line) {
		outBuf.put(line + "\0");
	}

	private void sleep(long count) {
		try {
			Thread.sleep(count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
