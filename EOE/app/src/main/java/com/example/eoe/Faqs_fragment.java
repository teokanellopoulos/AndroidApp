package com.example.eoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class Faqs_fragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faqs_fragment,container,false);
        ExpandableListView expandableListView;

        //pinakas erwthsewn
        ArrayList<String> listgroup = new ArrayList<>();

        //periexei tis erwthseis kai tis apanthseis
        HashMap<String,ArrayList<String>> listchild = new HashMap<>();
        MainAdapter adapter;

        expandableListView = (ExpandableListView) view.findViewById(R.id.exp_list);

        listgroup.add(getString(R.string.q1));
        ArrayList<String> q1 = new ArrayList<>();
        q1.add(getString(R.string.a1));
        listchild.put(listgroup.get(0),q1);

        listgroup.add(getString(R.string.q2));
        ArrayList<String> q2 = new ArrayList<>();
        q2.add(getString(R.string.a2));
        listchild.put(listgroup.get(1),q2);

        listgroup.add(getString(R.string.q3));
        ArrayList<String> q3 = new ArrayList<>();
        q3.add(getString(R.string.a3));
        listchild.put(listgroup.get(2),q3);

        listgroup.add(getString(R.string.q4));
        ArrayList<String> q4 = new ArrayList<>();
        q4.add(getString(R.string.a4));
        listchild.put(listgroup.get(3),q4);

        listgroup.add(getString(R.string.q5));
        ArrayList<String> q5 = new ArrayList<>();
        q5.add(getString(R.string.a5));
        listchild.put(listgroup.get(4),q5);

        listgroup.add(getString(R.string.q6));
        ArrayList<String> q6 = new ArrayList<>();
        q6.add(getString(R.string.a6));
        listchild.put(listgroup.get(5),q6);

        listgroup.add(getString(R.string.q7));
        ArrayList<String> q7 = new ArrayList<>();
        q7.add(getString(R.string.a7));
        listchild.put(listgroup.get(6),q7);

        adapter = new MainAdapter(listgroup,listchild);
        expandableListView.setAdapter(adapter);

        return view;
    }
}
