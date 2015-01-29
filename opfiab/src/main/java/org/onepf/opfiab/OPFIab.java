/*
 * Copyright 2012-2014 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfiab;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import org.onepf.opfiab.model.Configuration;
import org.onepf.opfiab.model.event.SetupEvent;
import org.onepf.opfutils.Checkable;
import org.onepf.opfutils.OPFChecks;

import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusBuilder;

public final class OPFIab {

    private OPFIab() {
        throw new UnsupportedOperationException();
    }


    static final String FRAGMENT_TAG = "OPFIabFragment";

    private static BaseIabHelper baseIabHelper;

    private static EventBus eventBus;

    private static Context context;

    private static Configuration configuration;

    private static final Checkable CHECK_INIT = new Checkable() {
        @Override
        public boolean check() {
            return baseIabHelper != null;
        }
    };

    @NonNull
    static EventBus getEventBus() {
        OPFChecks.checkThread(true);
        if (eventBus == null) {
            final EventBusBuilder builder = EventBus.builder();
            builder.eventInheritance(true);
            builder.executorService(Executors.newSingleThreadExecutor());
            builder.throwSubscriberException(true);
            eventBus = builder.build();
        }
        return eventBus;
    }

    @NonNull
    static BaseIabHelper getBaseHelper() {
        OPFChecks.checkThread(true);
        OPFChecks.checkInit(CHECK_INIT, true);
        return baseIabHelper;
    }

    @NonNull
    public static Context getContext() {
        OPFChecks.checkThread(true);
        if (context == null) {
            throw new IllegalStateException();
        }
        return context;
    }

    @NonNull
    public static Configuration getConfiguration() {
        OPFChecks.checkThread(true);
        if (configuration == null) {
            throw new IllegalStateException();
        }
        return configuration;
    }

    @NonNull
    public static IabHelper getHelper() {
        return getBaseHelper();
    }

    @NonNull
    public static ManagedIabHelper getManagedHelper() {
        return new ManagedIabHelper(getBaseHelper());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @NonNull
    public static SelfManagedIabHelper getHelper(@NonNull final Activity activity) {
        return new ActivityIabHelper(getManagedHelper(), activity);
    }

    @NonNull
    public static SelfManagedIabHelper getHelper(@NonNull final FragmentActivity fragmentActivity) {
        return new ActivityIabHelper(getManagedHelper(), fragmentActivity);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    public static SelfManagedIabHelper getHelper(@NonNull final android.app.Fragment fragment) {
        return new FragmentIabHelper(getManagedHelper(), fragment);
    }

    @NonNull
    public static SelfManagedIabHelper getHelper(
            @NonNull final android.support.v4.app.Fragment fragment) {
        return new FragmentIabHelper(getManagedHelper(), fragment);
    }

    @Nullable
    public static SetupEvent getSetupEvent() {
        OPFChecks.checkInit(CHECK_INIT, true);
        //TODO blocking call?
        return eventBus.getStickyEvent(SetupEvent.class);
    }

    public static void init(@NonNull final Context context,
                            @NonNull final Configuration configuration) {
        OPFChecks.checkThread(true);
        OPFChecks.checkInit(CHECK_INIT, false);
        OPFIab.context = context.getApplicationContext();
        OPFIab.configuration = configuration;
        baseIabHelper = new BaseIabHelper();
    }

    public static void setup() {
        OPFChecks.checkThread(true);
        OPFChecks.checkInit(CHECK_INIT, true);
        getBaseHelper().setup();
    }
}
