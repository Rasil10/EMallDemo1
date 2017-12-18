package helpinghand.emalldemo1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    ViewPager featuredShopViewPager,completeMakeoverViewPager;
    private static int currentPage = 0;

    LinearLayout skirtLinearLayout,shoesLinearLayout,suitLinearLayout,heelsLinearLayout,glassesLinearLayout,pursesLinearLayout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View rootView= inflater.inflate(R.layout.fragment_main, container, false);

        skirtLinearLayout=(LinearLayout)rootView.findViewById(R.id.skirtLinearLayout);
        shoesLinearLayout=(LinearLayout)rootView.findViewById(R.id.shoesLinearLayout);
        suitLinearLayout=(LinearLayout)rootView.findViewById(R.id.suitLinearLayout);
        heelsLinearLayout=(LinearLayout)rootView.findViewById(R.id.heelsLinearLayout);
        glassesLinearLayout=(LinearLayout)rootView.findViewById(R.id.glassesLinearLayout);
        pursesLinearLayout=(LinearLayout)rootView.findViewById(R.id.pursesLinearLayout);

        skirtLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "SKIRT", Toast.LENGTH_SHORT).show();
            }
        });
        shoesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "SHOES", Toast.LENGTH_SHORT).show();
            }
        });
        suitLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "SUIT", Toast.LENGTH_SHORT).show();
            }
        });
        heelsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "HEELS", Toast.LENGTH_SHORT).show();
            }
        });
        glassesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "GLASSES", Toast.LENGTH_SHORT).show();
            }
        });
        pursesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "PURSES", Toast.LENGTH_SHORT).show();
            }
        });

        initFeaturedShopView(rootView);
        initCompleteMakeoverView(rootView);

        return rootView;
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

    private void initFeaturedShopView(View rootView) {

        final Integer[] featuredShopImage={R.drawable.featured_shop_image_one,R.drawable.featured_shop_image_two,
                R.drawable.featured_shop_image_three,R.drawable.featured_shop_image_four,
                R.drawable.featured_shop_image_five};
        ArrayList<Integer> featuredShopImageArray=new ArrayList<>();
        for(int i=0;i<featuredShopImage.length;i++)
            featuredShopImageArray.add(featuredShopImage[i]);


        featuredShopViewPager = (ViewPager)rootView. findViewById(R.id.featuredShopViewpager);
        featuredShopViewPager.setAdapter(new ViewPagerAdapter(getActivity(),featuredShopImageArray));
        CircleIndicator indicator = (CircleIndicator)rootView. findViewById(R.id.featuredShopindicator);
        indicator.setViewPager(featuredShopViewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == featuredShopImage.length) {
                    currentPage = 0;
                }
                featuredShopViewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }

        }, 2500, 2500);
    }
    private void initCompleteMakeoverView(View rootView) {

        final Integer[] completeMakeOverImage={R.drawable.makeup_banner_one,R.drawable.makeup_banner_two,
                R.drawable.makeup_banner_three,R.drawable.makeup_banner_four,
                R.drawable.makeup_banner_five};
        ArrayList<Integer> completeMakeOverImageArray=new ArrayList<>();
        for(int i=0;i<completeMakeOverImage.length;i++)
            completeMakeOverImageArray.add(completeMakeOverImage[i]);


        completeMakeoverViewPager = (ViewPager) rootView.findViewById(R.id.completeMakeOverViewpager);
        completeMakeoverViewPager.setAdapter(new ViewPagerAdapter(getActivity(),completeMakeOverImageArray));
        CircleIndicator indicator = (CircleIndicator) rootView.findViewById(R.id.completeMakeOverindicator);
        indicator.setViewPager(completeMakeoverViewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == completeMakeOverImage.length) {
                    currentPage = 0;
                }
                featuredShopViewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }

        }, 2500, 2500);
    }
}
