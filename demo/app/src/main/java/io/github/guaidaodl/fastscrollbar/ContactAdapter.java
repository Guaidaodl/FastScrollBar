package io.github.guaidaodl.fastscrollbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactAdapter extends BaseAdapter implements FastScrollBar.FastScrollBarAdapter {
    private String []contactArray;
    private List<String> keywords;
    private Map<String, Integer> indexMap;
    private Context context;

    public ContactAdapter(Context context, String[] contactArray) {
        this.context = context;
        this.contactArray = contactArray;
        Arrays.sort(this.contactArray);
        generateIndexMap();
    }

    private void generateIndexMap() {
        indexMap = new HashMap<String, Integer>();
        keywords = new ArrayList<String>();
        for (int index = 0; index < contactArray.length; index++) {
            if (!indexMap.containsKey(contactArray[index].substring(0,1))) {
                String first = contactArray[index].substring(0, 1);
                indexMap.put(first, index);
                keywords.add(first);
            }
        }
    }
    @Override
    public int getCount() {
        return contactArray.length;
    }

    @Override
    public Object getItem(int position) {
        return contactArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(contactArray[position]);

        return convertView;
    }

    @Override
    public int getPosition(String keyword) {
        if (indexMap.containsKey(keyword)) {
            return indexMap.get(keyword);
        }

        return -1;
    }

    @Override
    public String getKeyword(int index) {
        return keywords.get(index);
    }

    @Override
    public int getKeywordLength() {
        return keywords.size();
    }
}
