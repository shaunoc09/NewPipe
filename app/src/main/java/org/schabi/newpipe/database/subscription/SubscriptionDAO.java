package org.schabi.newpipe.database.subscription;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import org.schabi.newpipe.database.BasicDAO;

import java.util.List;

import io.reactivex.Flowable;

import static org.schabi.newpipe.database.subscription.SubscriptionEntity.SUBSCRIPTION_SERVICE_ID;
import static org.schabi.newpipe.database.subscription.SubscriptionEntity.SUBSCRIPTION_TABLE;
import static org.schabi.newpipe.database.subscription.SubscriptionEntity.SUBSCRIPTION_URL;

@Dao
public interface SubscriptionDAO extends BasicDAO<SubscriptionEntity> {
    @Override
    @Query("SELECT * FROM " + SUBSCRIPTION_TABLE)
    Flowable<List<SubscriptionEntity>> findAll();

    @Override
    @Query("SELECT * FROM " + SUBSCRIPTION_TABLE + " WHERE " + SUBSCRIPTION_SERVICE_ID + " = :serviceId")
    Flowable<List<SubscriptionEntity>> listByService(int serviceId);

    @Query("SELECT * FROM " + SUBSCRIPTION_TABLE + " WHERE " +
            SUBSCRIPTION_URL + " LIKE :url AND " +
            SUBSCRIPTION_SERVICE_ID + " = :serviceId")
    Flowable<List<SubscriptionEntity>> findAll(int serviceId, String url);
}
