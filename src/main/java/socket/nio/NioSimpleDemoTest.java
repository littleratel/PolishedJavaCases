package socket.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioSimpleDemoTest {

	public static void main(String[] args) throws IOException {
		// 默认为项目工作路径 即项目的根目录
		String filePath = "src/main/resources/DesignPatternFile/decoratordemo.txt";
		RandomAccessFile file = new RandomAccessFile(filePath, "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(128);

		int bytesRead = channel.read(buffer);
		while (bytesRead != -1) {
			// call flip(), turn the model of write to read bytesRead
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
			}
			buffer.clear();
			bytesRead = channel.read(buffer);
		}
		file.close();
	}

}
