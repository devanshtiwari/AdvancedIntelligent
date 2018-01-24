import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by devan on 23-01-2018.
 */
public class DataWriter {
    BufferedWriter writer ;
    DataWriter(String fileName, String header) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(header);
    }
    void append(String data) throws IOException {
        writer.write(data + ",");
    }
    void append(String data, String delim) throws IOException {
        writer.write(data + delim);
    }
    void endOfLine(String data) throws IOException {
        writer.write(data + "\n");
    }
    void close() throws IOException {
        writer.close();
    }
}
