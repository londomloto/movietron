package com.supernova.jetpack.movietron.util;

import java.util.concurrent.Executor;

public class InstantAppExecutors extends AppExecutors {
    private final static Executor instant = Runnable::run;

    public InstantAppExecutors() {
        super(instant, instant, instant);
    }
}
