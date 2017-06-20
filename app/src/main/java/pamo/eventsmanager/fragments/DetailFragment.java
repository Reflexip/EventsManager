package pamo.eventsmanager.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pamo.eventsmanager.R;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    public void setEventTitle(String txt) {
        TextView view = (TextView) getView().findViewById(R.id.eventTitle);
        view.setText(txt);
    }

    public void setEventDate(String txt) {
        TextView view = (TextView) getView().findViewById(R.id.eventDate);
        view.setText(txt);
    }

    public void setEventTime(String txt) {
        TextView view = (TextView) getView().findViewById(R.id.eventTime);
        view.setText(txt);
    }
}