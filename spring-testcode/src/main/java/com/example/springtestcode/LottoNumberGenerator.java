package com.example.springtestcode;

import java.util.*;

public class LottoNumberGenerator {
    private static final int LOTTO_TICKET_LIMIT_NUM = 6;
    private LottoNumberCollection collection;

    public LottoNumberGenerator(LottoNumberCollection collection) {
        this.collection = collection;
    }

    public List<Integer> generateTicket(){
        // 1. 빈 Set을 만들고
        Set<Integer> ticket = new HashSet<>();

        // 2. 전체 숫자들을 생성하고
        List<Integer> lottoNumbers = collection.createNumbers();

        // 3. 숫자를 섞는다.
        shuffleNum(lottoNumbers);

        // 4. 6개의 숫자가 될 때까지 add()
        for (int i = 0; ticket.size() < LOTTO_TICKET_LIMIT_NUM; i++) {
            ticket.add(lottoNumbers.get(i));
        }

        return lottoNumbers;
    }

    private void shuffleNum(List<Integer> lottoNumbers){
        Collections.shuffle(lottoNumbers);
    }
}
