import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by devan on 23-01-2018.
 */
public class DataWriter {
    BufferedWriter writer ;
    DataWriter(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("Iteration,Qc, Qd, Qc, Qd\n");
    }
    void append(String data) throws IOException {
        writer.write(data + ",");
    }
    void endOfLine(String data) throws IOException {
        writer.write(data + "\n");
    }
    void close() throws IOException {
        writer.close();
    }
}
