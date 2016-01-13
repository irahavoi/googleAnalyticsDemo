/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
 
package com.example.android.dinnerapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;


public class OrderDinnerActivity extends Activity {
    String selectedDinnerExtrasKey = String.valueOf(R.string.selected_dinner);

    String dinner;
    String dinnerId;

    Button addDinnerToCartBtn;
    Button checkoutDinnerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_dinner);
    }

    protected void onStart() {
        super.onStart();

        // Set the heading
        TextView heading_tv = (TextView) findViewById(R.id.textView_info_heading);
        heading_tv.setText(getResources().getText(R.string.order_online_heading));

        // Set the text
        TextView tv = (TextView) findViewById(R.id.textView_info);

        String dinner = getIntent().getStringExtra(selectedDinnerExtrasKey);
        tv.setText("This is where you will order the selected dinner: \n\n" +
                dinner);

        this.dinner = dinner;
        this.dinnerId = Utility.getDinnerId(dinner);

        addDinnerToCartBtn = (Button) findViewById(R.id.addDinnerToCartButton);
        checkoutDinnerBtn = (Button) findViewById(R.id.checkoutDinnerButton);

        sendViewProductHit(dinner, dinnerId);
    }

    public void sendViewProductHit(String dinner, String dinnerId){
        Product product = new Product()
                .setName("dinner")
                .setPrice(5)
                .setVariant(dinner)
                .setId(dinnerId)
                .setQuantity(1);

        ProductAction action = new ProductAction(ProductAction.ACTION_DETAIL);

        Tracker tracker = ((MyApplication) getApplication()).getTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Shopping steps")
                .setAction("View Order Dinner screen")
                .setLabel(dinner)
                .addProduct(product)
                .setProductAction(action)
                .build());
    }

    public void addDinnerToCart(View view){
        Toast.makeText(this, "Added to the cart", Toast.LENGTH_SHORT).show();
        checkoutDinnerBtn.setVisibility(View.VISIBLE);
        addDinnerToCartBtn.setVisibility(View.INVISIBLE);
        sendProductActionToAnalytics(new ProductAction(ProductAction.ACTION_ADD));

    }

    public void checkoutDinner(View view){
        Toast.makeText(this, "Checked out!", Toast.LENGTH_SHORT).show();
        checkoutDinnerBtn.setVisibility(View.INVISIBLE);
        addDinnerToCartBtn.setVisibility(View.VISIBLE);
        sendProductActionToAnalytics(new ProductAction(ProductAction.ACTION_CHECKOUT));
    }

    private void sendProductActionToAnalytics(ProductAction productAction){
        Product product = new Product()
                .setName("dinner")
                .setPrice(5)
                .setVariant(dinner)
                .setId(dinnerId)
                .setQuantity(1);

        Tracker tracker = ((MyApplication) getApplication()).getTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Shopping steps")
                .setAction("View Order Dinncer screen")
                .setLabel("some product")
                .addProduct(product)
                .setProductAction(productAction)
                .build());
    }

}
