package pe.comercio.stepper.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pe.comercio.stepper.R;

/**
 * Created by Carlos Vargas on 8/10/16.
 *
 */
public class FragmentFour extends Fragment {

    public AppCompatTextView lblMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lblMessage = (AppCompatTextView) view.findViewById(R.id.lblMessage);

    }

}
