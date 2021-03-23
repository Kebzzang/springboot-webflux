package com.keb.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;


//구독 정보: 구독자가 누구인지, 어떤 데이터를 구독할지 이 정보를 들고 있어야함
public class MySubscription implements Subscription {
    private Subscriber s;
    private Iterator<Integer> it;

    public MySubscription(Subscriber s, Iterable<Integer> its) {
        this.s = s;
        this.it = its.iterator();
        System.out.println("신문사: 구독 정보 생성 완료 했어 !! ");
    }

    @Override
    public void request(long l) {
        while (l > 0) {
            if (it.hasNext()) {
                s.onNext(it.next());
            } else {
                s.onComplete();
                break;
            }
            l--;
        }
    }

    @Override
    public void cancel() {

    }
}
