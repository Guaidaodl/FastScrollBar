### About the project

I try to write a fast scroll bar. You can use it in your app easily.

### How to use

1. Import FastScrollBar.java in your project.
2. Add it to your layout, for example
    ```xml
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:fast="http://schemas.android.com/io.github.guaidaodl.fastscrollbar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity$PlaceholderFragment">
    
        <ListView
            android:id="@android:id/list"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
        <io.github.guaidaodl.fastscrollbar.FastScrollBar
            android:id="@+id/fastScrollBar"
            fast:textSize="12sp"
            fast:textColor="#B4B4B4"
            android:layout_width="24dp"
            android:layout_height="match_parent"
            android:layout_gravity="right"/>
    </FrameLayout>  
    ```
3. Let your adpter class implement the FastScrollBar.FastScrollBarAdapter interface.
4. using bindview method to bind your listview.
    ``` java
    View root = inflater.inflate(R.layout.fragment_main, container, false);
    ListView listView = (ListView) root.findViewById(android.R.id.list);
    
    FastScrollBar fastScrollBar =(FastScrollBar) root.findViewById(R.id.fastScrollBar);
    fastScrollBar.bindView(listView);
    ```
5. nothing need more
