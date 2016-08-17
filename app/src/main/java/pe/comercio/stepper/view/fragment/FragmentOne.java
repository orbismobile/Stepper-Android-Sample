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
import pe.comercio.stepper.util.Constant;
import pe.comercio.stepper.util.Utils;

/**
 * Created by Carlos Vargas on 8/10/16.
 */

public class FragmentOne extends Fragment {

    private TextInputLayout tilName, tilLastName;
    private TextInputEditText tieName, tieLastName;
    public String name, lastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tilName = (TextInputLayout) view.findViewById(R.id.tilName);
        tilLastName = (TextInputLayout) view.findViewById(R.id.tilLastName);
        tieName = (TextInputEditText) view.findViewById(R.id.tieName);
        tieLastName = (TextInputEditText) view.findViewById(R.id.tieLastName);

        tieName.addTextChangedListener(new CustomTextWatcher(tieName));
        tieLastName.addTextChangedListener(new CustomTextWatcher(tieLastName));
    }

    public boolean validateFields(){
        return isValid(tieName, tilName, Constant.VAR_ONE) &&
                isValid(tieLastName, tilLastName, Constant.VAR_TWO);
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
            switch (view.getId()) {
                case R.id.tieName:
                    isValid(tieName, tilName, Constant.VAR_ONE);
                    break;
                case R.id.tieLastName:
                    isValid(tieLastName, tilLastName, Constant.VAR_TWO);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isValid(TextInputEditText tieText, TextInputLayout tilText, int var){

        String text = tieText.getText().toString().trim();

        if(text.isEmpty()){
            tilText.setErrorEnabled(true);

            tilText.setError(getString(R.string.error_name));

            if(var == Constant.VAR_TWO){
                tilText.setError(getString(R.string.error_lastname));
            }

            Utils.requestFocus(getActivity(), tieText);
            return false;

        }

        if(text.length()<1){
            tilText.setError(getString(R.string.error_name));

            if(var == Constant.VAR_TWO){
                tilText.setError(getString(R.string.error_lastname));
            }
            Utils.requestFocus(getActivity(), tieText);
            return false;
        }else{
            tilText.setErrorEnabled(false);
        }

        if(var == Constant.VAR_ONE){
            name = text;
        }else if(var == Constant.VAR_TWO){
            lastName = text;
        }

        return  true;

    }

}
