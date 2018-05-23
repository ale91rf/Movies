package com.app.movies.domain.interactor;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

public abstract class BaseInteractor {

    private ThreadExecutor subscriberOn;
    private ThreadExecutor observerOn;

    private Single single;
    private ListCompositeDisposable disposables = new ListCompositeDisposable();


    public BaseInteractor(ThreadExecutor subscriberOn,
                          ThreadExecutor observerOn) {
        this.subscriberOn = subscriberOn;
        this.observerOn = observerOn;
    }

    public <T> void execute(final Single<T> single,
                            final SingleObserver<T> singleObserver) {
        this.single = single;
        getSubscription(single, singleObserver);

    }

    private <T> void getSubscription(final Single<T> single,
                                     final SingleObserver<T> singleObserver) {
        single.subscribeOn(subscriberOn.getScheduler())
                .observeOn(observerOn.getScheduler())
                .doOnSubscribe(this::addSubscription)
                .subscribe(singleObserver);
    }

    private void addSubscription(Disposable disposable) {
        if (disposables.isDisposed()) {
            disposables = new ListCompositeDisposable();
        }
        if (disposable != null) {
            disposables.clear();
            disposables.add(disposable);
        }
    }

}
