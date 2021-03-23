package com.keb.reactivetest;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

public class MyPub implements Publisher<Integer> {
    Iterable<Integer> its = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Override
    public void subscribe(Subscriber<? super Integer> s) {
        System.out.println("예비구독자: 신문사야 나 너희 신문 볼게!!");
        System.out.println("신문사: 알겠어 ====== 구독정보 만들어서 돌려줄테니 기다리렴");
        MySubscription subscription = new MySubscription(s, its); //구독자 정보랑 구독지 정보 넘기기

        System.out.println("신문사: 구독 정보 생성 완료 했어 잘 받아!! ");

        s.onSubscribe(subscription);

    }
}
