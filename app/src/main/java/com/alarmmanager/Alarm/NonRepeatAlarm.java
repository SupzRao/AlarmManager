package com.alarmmanager.Alarm;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alarmmanager.BasePackage.BaseFragment;
import com.alarmmanager.R;
import com.alarmmanager.model.entity.Alarm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NonRepeatAlarm.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NonRepeatAlarm#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresPresenter(NonRepeatAlarmListPresenter.class)
public class NonRepeatAlarm extends BaseFragment<NonRepeatAlarmListPresenter> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.recycler_view_non_repeat)
    public RecyclerView recycler_view_non_repeat;
    private OnFragmentInteractionListener mListener;
    NonRepeatAdapter nonRepeatAdapter;
    @BindView(R.id.no_data_linear_layout)
    LinearLayout no_data_linear_layout;
    @BindView(R.id.fab_add)
    FloatingActionButton fab_add;
    private List<Alarm> list_alarm;
    Reciever reciever;

    public NonRepeatAlarm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NonRepeatAlarm.
     */
    // TODO: Rename and change types and number of parameters
    public static NonRepeatAlarm newInstance(String param1, String param2) {
        NonRepeatAlarm fragment = new NonRepeatAlarm();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_repeat_alarm, container, false);
        ButterKnife.bind(this, view);
        list_alarm = new ArrayList<Alarm>();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view_non_repeat.setLayoutManager(llm);
        list_alarm = getPresenter().getAlarmDataFromDb();
        setDataToAdapter();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addfuelstation = new Intent(getContext(), AddAlarmActivity.class);
                startActivity(addfuelstation);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (reciever != null)
            getActivity().unregisterReceiver(reciever);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void setDataToAdapter() {
        list_alarm = getPresenter().getAlarmDataFromDb();
        nonRepeatAdapter = new NonRepeatAdapter(list_alarm);
        if (nonRepeatAdapter.getItemCount() > 0) {
            recycler_view_non_repeat.setVisibility(View.VISIBLE);
            recycler_view_non_repeat.setAdapter(nonRepeatAdapter);
            no_data_linear_layout.setVisibility(View.GONE);
        } else {
            recycler_view_non_repeat.setVisibility(View.GONE);
            no_data_linear_layout.setVisibility(View.VISIBLE);
        }
        nonRepeatAdapter.notifyDataSetChanged();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        reciever = new Reciever();
        getActivity().registerReceiver(reciever, new IntentFilter(Intent.ACTION_TIME_TICK));

        try {
            setDataToAdapter();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
