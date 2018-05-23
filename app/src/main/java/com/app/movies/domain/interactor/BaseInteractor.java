package com.app.movies.domain.interactor;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

public abstract class BaseInteractor {

    private ThreadExecutor subscriberOn;
    private ThreadExecutor observerOn;


    public BaseInteractor(ThreadExecutor subscriberOn,
                          ThreadExecutor observerOn) {
        this.subscriberOn = subscriberOn;
        this.observerOn = observerOn;
    }

    public void execute(final Single single,
                        final SingleObserver singleObserver) {
        single.subscribeOn(subscriberOn.getScheduler())
                .observeOn(observerOn.getScheduler())
                .subscribe(singleObserver);
    }

}
