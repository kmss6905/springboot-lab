import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadCallBackTest implements Runnable{

    private String fileName;
    private String result;

    public ThreadCallBackTest(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        String filePath = "/Users/minshik/Desktop/study/spring-lab/java-nonblocking/src/main/resources/";
        String content = "hello";

        try(FileOutputStream out = new FileOutputStream(filePath + fileName)){
            out.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 단일 스레드의 작업이 끝났을 때 자신을 생성한 클래스를 호출하여 메인 스레드에게 결과를 알려준다.
        ThreadInterface.getResult(fileName);
    }

    public String getResult() {
        return result;
    }
}
