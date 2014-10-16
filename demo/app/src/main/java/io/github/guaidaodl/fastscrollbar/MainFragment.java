package io.github.guaidaodl.fastscrollbar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by linyb on 2014/10/16.
 */
public class MainFragment extends Fragment
        implements ListView.OnItemClickListener {
    private MainFragmentListener listener;
    private ListAdapter listAdapter;
    public interface MainFragmentListener {
        public void onItemClick(View view, int position, long id);
    }
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    //for system
    public MainFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) root.findViewById(android.R.id.list);

        String []ss = getResources().getStringArray(R.array.contact_names);
        listAdapter = new ContactAdapter(getActivity(), ss);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);

        FastScrollBar fastScrollBar =(FastScrollBar) root.findViewById(R.id.fastScrollBar);
        fastScrollBar.bindView(listView);

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof MainFragmentListener)) {
            throw new ClassCastException("The Activity " + activity.getClass()
                    + "should implement the MainFragmentListener interface");
        }
        listener = (MainFragmentListener) activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listener.onItemClick(view, position, id);
    }
}
