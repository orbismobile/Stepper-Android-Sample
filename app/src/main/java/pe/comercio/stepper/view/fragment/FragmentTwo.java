package pe.comercio.stepper.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.comercio.stepper.R;
import pe.comercio.stepper.util.Utils;

/**
 * Created by Carlos Vargas on 8/10/16.
 *
 */

public class FragmentTwo extends Fragment {

    private TextInputLayout tilAddress;
    private TextInputEditText tieAddress;
    public String address;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tilAddress = (TextInputLayout) view.findViewById(R.id.tilAddress);
        tieAddress = (TextInputEditText) view.findViewById(R.id.tieAddress);

        tieAddress.addTextChangedListener(new CustomTextWatcher(tieAddress));
    }

    public boolean validateFields(){
        return isValid();
    }

    private class CustomTextWatcher implements TextWatcher {

        private final View view;

        private CustomTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            if(view.getId() == R.id.tieAddress){
                isValid();
            }
        }
    }

    private boolean isValid(){

        String text = tieAddress.getText().toString().trim();

        if(text.isEmpty()){
            tilAddress.setErrorEnabled(true);
            tilAddress.setError(getString(R.string.error_address));
            Utils.requestFocus(getActivity(), tieAddress);
            return false;

        }

        if(text.length()<1){
            tilAddress.setError(getString(R.string.error_address));
            Utils.requestFocus(getActivity(), tieAddress);
            return false;
        }else{
            tilAddress.setErrorEnabled(false);
        }
        address = text;

        return  true;
    }

}
