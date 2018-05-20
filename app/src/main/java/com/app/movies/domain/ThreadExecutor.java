package com.app.movies.domain;

import io.reactivex.Scheduler;

public class ThreadExecutor {

    private Scheduler scheduler;

    public ThreadExecutor(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
