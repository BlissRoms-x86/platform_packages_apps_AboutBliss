package bliss.about.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;

import bliss.about.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_home, container, false);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
        }

        ObservableScrollView content = (ObservableScrollView) root.findViewById(R.id.HomeContent);

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.apply_btn);
        fab.setColorNormal(getResources().getColor(R.color.fab));
        fab.setColorPressed(getResources().getColor(R.color.fab_pressed));
        fab.setColorRipple(getResources().getColor(R.color.semitransparent_white));
        fab.show(true);
        fab.attachToScrollView(content);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUpdate = getActivity().getPackageManager().getLaunchIntentForPackage("com.chummy.blissroms.updates");
                if (intentUpdate == null) {
                    Toast.makeText(getActivity(), ("BlissOTA not found. Please reflash your ROM."), Toast.LENGTH_SHORT).show();
                } else startActivity(intentUpdate);
            }
        });

        return root;
    }
}
