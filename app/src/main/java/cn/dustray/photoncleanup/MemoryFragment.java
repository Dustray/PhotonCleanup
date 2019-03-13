package cn.dustray.photoncleanup;

import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import cn.dustray.memory.AppInfo;
import cn.dustray.memory.AppListAdapter;
import cn.dustray.memory.MemoryEntity;
import cn.dustray.memory.MemoryUtil;
import cn.dustray.utils.xToast;
import cn.dustray.view.xRecycleViewDivider;


public class MemoryFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnChangeAppList;
    private TextView totalMemory, availableMemory, textRemind,memoryPercent;
    private OnFragmentInteractionListener mListener;
    private RecyclerView appListRecycler;
    private AppListAdapter appListAdapter;
    private ProgressBar processBar;

    public MemoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemoryFragment newInstance(String param1, String param2) {
        MemoryFragment fragment = new MemoryFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memory, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        btnChangeAppList = view.findViewById(R.id.btn_change_app_list);
        btnChangeAppList.setOnClickListener(this);
        totalMemory = view.findViewById(R.id.total_memory);
        availableMemory = view.findViewById(R.id.available_memory);
        textRemind = view.findViewById(R.id.text_remind);
        processBar = view.findViewById(R.id.capacity_progressbar);
        memoryPercent=view .findViewById(R.id.memory_percent);
        appListRecycler = view.findViewById(R.id.recycler_app_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //layoutManager.setStackFromEnd(true);
        appListRecycler.setLayoutManager(layoutManager);
        appListRecycler.addItemDecoration(new xRecycleViewDivider(
                getContext(), LinearLayoutManager.VERTICAL, 3, getResources().getColor(R.color.divide_gray_color)));
    }

    private void initData() {
        MemoryEntity entity = new MemoryUtil(getActivity()).getCurrentMeminfo();
        totalMemory.setText(MB2GB(entity.getTotalMemory()) + "GB");
        availableMemory.setText(MB2GB(entity.getAvailableMemory()) + "GB");
        int percent=100-(int)(100*((float)entity.getAvailableMemory()/entity.getTotalMemory()));
      //  processBar.setProgress(percent);
        setAnimation(processBar,processBar.getProgress(), percent);
        memoryPercent.setText(percent+"%");
        textRemind.setText("Android 7.0之后无法再清理内存");
        loadAppList();
    }

    private String MB2GB(int MB) {
        float m = ((float) MB) / 1024;
        return  String.format("%1.2f",m);
    }
    private void setAnimation(final ProgressBar view, final int mProgressBarBegin, final int mProgressBarEnd) {
        ValueAnimator animator = ValueAnimator.ofInt(mProgressBarBegin, mProgressBarEnd).setDuration(500);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setProgress((int) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }
    private void loadAppList() {
        List<AppInfo> list = new MemoryUtil(getActivity()).getInstalledAppList();
        //xToast.toast(getContext(),list.size()+"");
        appListAdapter = new AppListAdapter(getContext(), list);
        appListRecycler.setAdapter(appListAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_app_list:
                new MemoryUtil(getActivity()).clearMemory();
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void onChangeActionBarTitle(String title);
    }
}
