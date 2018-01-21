package com.silence.rxjavademo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Silence-Dell
 *
 * @time 2018/1/20 0:36
 * @des ${TODO}
 */

public class HelloWorld {



    public static void main(String[] args) {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext : " + s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError : "  + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete : " );
            }
        };

        Observer<Integer> observerInteger = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                System.out.println("onNext : " + s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError : "  + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete : " );
            }
        };
//        onCreate(observer);
//        onJust(observer);
        onFrom(observerInteger);
//        Flowable.just("Hello World!!").subscribe(System.out::println);

    }

    private static void onFrom(Observer<Integer> observerInteger) {
        Observable<Integer> observable = Observable.fromArray(new Integer[]{1,2,3,4,5});
        observable.subscribe(observerInteger);
    }

    private static void onJust(Observer<String> observer) {
        Observable<String> observable = Observable.just("Hello World!!!, Just");
        observable.subscribe(observer);
    }

    private static void onCreate(Observer<String> observer) {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello World!!!");
                emitter.onComplete();
            }
        });

        observable.subscribe(observer);
    }

}
