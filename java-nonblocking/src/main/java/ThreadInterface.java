public class ThreadInterface {
    public static void getResult(String name){
        System.out.println(name);
    }

    public static void main(String[] args) {
        String[] files = {"abc0.txt", "abc1.txt", "abc2.txt"};
        ThreadCallBackTest[] tt = new ThreadCallBackTest[files.length];

        for (int i = 0; i < files.length; i++) {
            tt[i] = new ThreadCallBackTest(files[i]);
            Thread t = new Thread(tt[i]);
            t.start();
        }
    }
}
