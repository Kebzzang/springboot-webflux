package com.keb.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer> {
    private Subscription s;
    private int buffersize = 3;

    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("구독자 : 구독 정보 잘받았어!!");
        this.s = s;
        System.out.println("구독자 : 나 이제 신문 하나씩 갖다 줘!!");
        s.request(buffersize); //신문을 하나씩 매일매일 줘~~~(백프레셔) 소비자가 한번에 처리할 수 있는 개수를 요청
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("onNext(): " + integer);

        buffersize--;
        if (buffersize == 0) {
            System.out.println("다음날");
            buffersize = 3;
            s.request(buffersize);
        }


    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("구독 중 에러 발생");
    }

    @Override
    public void onComplete() {
        System.out.println("구독 완료!!");
    }
}
