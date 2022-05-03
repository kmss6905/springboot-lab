import java.util.Arrays;

public class MainServer {
    public static void main(String[] args) {
        String[] files = {"file1.txt", "file2.txt", "file3.txt"};
        ThreadTest[] tt = new ThreadTest[files.length];

        for (int i = 0; i < files.length; i++) {
            tt[i] = new ThreadTest(files[i]);
        }

        Arrays.stream(files).forEach(
                file -> {
                    ThreadTest tt = new ThreadTest(file);
                    Thread t = new Thread(tt);
                    t.start();
                    System.out.println(tt.getResult());
                }
        );
    }
}
