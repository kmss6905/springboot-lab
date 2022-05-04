public class PollingServer {
    public static void main(String[] args) {
        String[] files = {"abc0.txt", "abc1.txt", "abc2.txt"};
        ThreadTest[] tt = new ThreadTest[files.length];

        for (int i = 0; i < files.length; i++) {
            tt[i] = new ThreadTest(files[i]);
            Thread t = new Thread(tt[i]);
            t.start();
        }

        // 메인스레드가 작업 종료 상태를 계속 확인해야 하기 때문에 다른 스레드가 일할 시간이 없다.
        // 성능저하
        for (int i = 0; i < files.length; i++) {
            System.out.println(i + "번째");
            while (true){
                if(tt[i].getResult() != null){
                    System.out.println(tt[i].getResult());
                    break;
                }
            }
        }
    }
}
