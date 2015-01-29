/*
 * Copyright 2012-2015 One Platform Foundation
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

package org.onepf.sample.trivialdrive;

import android.support.annotation.NonNull;

import org.onepf.opfiab.listener.BillingListener;
import org.onepf.opfiab.model.event.SetupEvent;
import org.onepf.opfiab.model.event.request.Request;
import org.onepf.opfiab.model.event.response.ConsumeResponse;
import org.onepf.opfiab.model.event.response.InventoryResponse;
import org.onepf.opfiab.model.event.response.PurchaseResponse;
import org.onepf.opfiab.model.event.response.Response;
import org.onepf.opfiab.model.event.response.SkuDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TrivialBillingListener implements BillingListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrivialBillingListener.class);

    @Override
    public void onRequest(@NonNull final Request request) {
        LOGGER.debug("", request);
    }

    @Override
    public void onResponse(@NonNull final Response response) {
        LOGGER.debug("", response);
    }

    @Override
    public void onConsume(@NonNull final ConsumeResponse consumeResponse) {
        LOGGER.debug("", consumeResponse);
    }

    @Override
    public void onInventory(@NonNull final InventoryResponse inventoryResponse) {
        LOGGER.debug("", inventoryResponse);
    }

    @Override
    public void onPurchase(@NonNull final PurchaseResponse purchaseResponse) {
        LOGGER.debug("", purchaseResponse);
    }

    @Override
    public void onSetup(@NonNull final SetupEvent setupEvent) {
        LOGGER.debug("", setupEvent);
    }

    @Override
    public void onSkuDetails(@NonNull final SkuDetailsResponse skuDetailsResponse) {
        LOGGER.debug("", skuDetailsResponse);
    }
}
