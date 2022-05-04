import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadTest implements Runnable{


    private String fileName;
    private String result;

    public ThreadTest(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        FileOutputStream out = null;
        String filePath = "/Users/minshik/Desktop/study/spring-lab/java-nonblocking/src/main/resources/";
        String content = "hello";

        try {
            out = new FileOutputStream(filePath + fileName);
            out.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
                result = fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getResult() {
        return result;
    }
}
