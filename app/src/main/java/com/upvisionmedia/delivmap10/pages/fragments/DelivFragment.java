package com.upvisionmedia.delivmap10.pages.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.upvisionmedia.delivmap10.R;

import java.io.InputStream;
import java.io.InputStreamReader;

public class DelivFragment extends Fragment {

    private TextView deliverNumber;
    private TextView sender;
    private TextView recipient;
    private TextView recipientAddress;
    private TextView recipientPhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deliv, container, false);

        // Text view fields

        deliverNumber = view.findViewById(R.id.deliveryNumberTextView);
        TextView createdAt = view.findViewById(R.id.createdAtTextView);
        sender = view.findViewById(R.id.senderTextView);
        recipient = view.findViewById(R.id.recipientTextView);
        recipientAddress = view.findViewById(R.id.recipientAddressTextView);
        TextView boxNumber = view.findViewById(R.id.boxNumberTextView);
        recipientPhoneNumber = view.findViewById(R.id.recipientPhoneNumberTextView);

        loadDataFromJson();

        return view;
    }

    private void loadDataFromJson() {

        Gson gson = new Gson();
        InputStream inputStream = getResources().openRawResource(R.raw.jsonfakedatadeliveries);
        InputStreamReader reader = new InputStreamReader(inputStream);
        try {

            JsonParser jsonParser = new JsonParser();
            JsonElement rootElement = jsonParser.parse(reader);

            if (rootElement.isJsonObject()) {
                JsonObject jsonObject = rootElement.getAsJsonObject();


                String deliveryNumber = jsonObject.get("delivery_number").getAsString();
                String createdAt = jsonObject.get("create_at").getAsString();
                String senderFirstName = jsonObject.get("sender_firstname").getAsString();
                String senderLastName = jsonObject.get("sender_lastname").getAsString();
                String recipientFirstName = jsonObject.get("recipient_firstname").getAsString();
                String recipientLastName = jsonObject.get("recipient_lastname").getAsString();
                String recipientCity = jsonObject.get("_recipient_city").getAsString();
                String recipientStreet = jsonObject.get("recipient_street").getAsString();
                String recipientStreetNumber = jsonObject.get("recipient_street_number").getAsString();
                String recipientZipcode = jsonObject.get("recipient_zipcode").getAsString();
                String boxNumber = jsonObject.get("box_number").getAsString();
                String senderPhoneNumber = jsonObject.get("sender_phonenumber").getAsString();

                String formattedCreatedAt = getString(R.string.created_at_format, createdAt);
                createdAt.setText(formattedCreatedAt);


                deliverNumber.setText(getString(R.string.delivery_number_format, deliveryNumber));
                sender.setText(getString(R.string.sender_format, senderFirstName, senderLastName));
                recipient.setText(getString(R.string.recipient_format, recipientFirstName, recipientLastName));
                recipientAddress.setText(getString(R.string.recipient_address_format, recipientStreet, recipientStreetNumber, recipientCity, recipientZipcode));
                boxNumber.setText(getString(R.string.box_number_format, boxNumber));
                recipientPhoneNumber.setText(getString(R.string.recipient_phone_format, senderPhoneNumber));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

