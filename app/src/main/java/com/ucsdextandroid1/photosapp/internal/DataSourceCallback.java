package com.ucsdextandroid1.photosapp.internal;

/**
 * Created by rjaylward on 4/13/19
 */
public interface DataSourceCallback<T> {
    void onDataFetched(T data);
}
