import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

//生成一千万条数据
public class GenerateManyRows {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("/tmp/manyRows.txt");
        BufferedWriter bufWriter = new BufferedWriter(writer);
        for (int i = 1; i <= 10000000; i++) {
            String str = i +
                    "," +
                    "value" +
                    "," +
                    Instant.now().toEpochMilli() +
                    "\n";
            bufWriter.write(str);
        }
        bufWriter.close();
        writer.close();
        System.out.println("done");
    }
}
