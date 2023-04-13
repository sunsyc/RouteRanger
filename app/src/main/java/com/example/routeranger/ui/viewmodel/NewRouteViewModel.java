package com.example.routeranger.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.routeranger.model.AppDatabase;
import com.example.routeranger.model.Dao.RouteDao;
import com.example.routeranger.model.Route;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class NewRouteViewModel extends ViewModel {

    private AppDatabase db;

    private RouteDao routeDao;

    NewRouteViewModel(AppDatabase appDatabase) {
        this.db = appDatabase;
        routeDao = db.routeDao();
    }

    private static final String TAG = "NewRouteViewModel";

    public void saveRoute(String routeName, String startDestination, String endDestination) {
        Route route = new Route();
        route.start = startDestination;
        route.end = endDestination;
        route.userId = db.loggedInUserId;
        if (routeName == null) {
            route.name = "Route " + route.uid;
        } else {
            route.name = routeName;
        }
        ListenableFuture<Long> future = routeDao.insert(route);
        Futures.addCallback(
                future,
                new FutureCallback<Long>() {
                    @Override
                    public void onSuccess(Long result) {
                        Log.i(TAG, "YIPPEE!!!!");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                }, db.dbWriteExecutor
        );
    }

}
