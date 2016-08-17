package pe.comercio.stepper.view.fragment;

import android.graphics.drawable.Drawable;
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
import android.widget.EditText;

import pe.comercio.stepper.R;
import pe.comercio.stepper.listener.PayuConstants;
import pe.comercio.stepper.util.PayuUtils;
import pe.comercio.stepper.util.Utils;

/**
 * Created by Carlos Vargas on 8/10/16.
 *
 */
public class FragmentThree extends Fragment {

    private TextInputLayout tilCardNumber;
    private TextInputEditText tieCardNumber;
    private EditText txtCardExpiryMonth, txtCardExpiryYear, txtCardCVV;

    public String card;
    private PayuUtils payuUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tilCardNumber = (TextInputLayout) view.findViewById(R.id.tilCardNumber);
        tieCardNumber = (TextInputEditText) view.findViewById(R.id.tieCardNumber);

        txtCardExpiryMonth = (EditText) view.findViewById(R.id.txtCardExpiryMonth);
        txtCardExpiryYear = (EditText) view.findViewById(R.id.txtCardExpiryYear);
        txtCardCVV = (EditText) view.findViewById(R.id.txtCardCVV);

        payuUtils = new PayuUtils();
        tieCardNumber.addTextChangedListener(new CustomTextWatcher(tieCardNumber));
    }

    public boolean validateFields(){
        return isValid();
    }

    private class CustomTextWatcher implements TextWatcher {

        String issuer;
        Drawable issuerDrawable;

        private final View view;

        private CustomTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (charSequence.length() > 6){ // to confirm rupay card we need min 6 digit.
                if(null == issuer) issuer = payuUtils.getIssuer(charSequence.toString().replace(" ",""));
                if (issuer != null && issuer.length() > 1 && issuerDrawable == null){
                    issuerDrawable = getIssuerDrawable(issuer);
                    if(issuer.contentEquals(PayuConstants.SMAE)){ // hide cvv and expiry
                        txtCardExpiryMonth.setVisibility(View.GONE);
                        txtCardExpiryYear.setVisibility(View.GONE);
                        txtCardCVV.setVisibility(View.GONE);
                    }else{ //show cvv and expiry
                        txtCardExpiryMonth.setVisibility(View.VISIBLE);
                        txtCardExpiryYear.setVisibility(View.VISIBLE);
                        txtCardCVV.setVisibility(View.VISIBLE);
                    }
                }
            }else{
                issuer = null;
                issuerDrawable = null;
            }
            tieCardNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, issuerDrawable, null);
        }

        public void afterTextChanged(Editable s) {
            if(view.getId() == R.id.tieCardNumber){

                // Remove all spacing char
                int pos = 0;
                while (true) {
                    if (pos >= s.length()) break;
                    if (' ' == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
                        s.delete(pos, pos + 1);
                    } else {
                        pos++;
                    }
                }

                // Insert char where needed.
                pos = 4;
                while (true) {
                    if (pos >= s.length()) break;
                    final char c = s.charAt(pos);
                    // Only if its a digit where there should be a space we insert a space
                    if ("0123456789".indexOf(c) >= 0) {
                        s.insert(pos, "" + ' ');
                    }
                    pos += 5;
                }


                isValid();
            }
        }
    }

    private boolean isValid(){

        String text = tieCardNumber.getText().toString().trim();

        if(text.isEmpty()){
            tilCardNumber.setErrorEnabled(true);
            tilCardNumber.setError(getString(R.string.error_card));
            Utils.requestFocus(getActivity(), tieCardNumber);
            return false;

        }

        if(text.length()<1){
            tilCardNumber.setError(getString(R.string.error_address));
            Utils.requestFocus(getActivity(), tieCardNumber);
            return false;
        }else{
            tilCardNumber.setErrorEnabled(false);
        }

        card = text;

        return  true;
    }

    private Drawable getIssuerDrawable(String issuer){

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            switch (issuer) {
                case PayuConstants.VISA:
                    return getResources().getDrawable(R.drawable.visa);
                case PayuConstants.LASER:
                    return getResources().getDrawable(R.drawable.laser);
                case PayuConstants.DISCOVER:
                    return getResources().getDrawable(R.drawable.discover);
                case PayuConstants.MAES:
                    return getResources().getDrawable(R.drawable.maestro);
                case PayuConstants.MAST:
                    return getResources().getDrawable(R.drawable.master);
                case PayuConstants.AMEX:
                    return getResources().getDrawable(R.drawable.amex);
                case PayuConstants.DINR:
                    return getResources().getDrawable(R.drawable.diner);
                case PayuConstants.JCB:
                    return getResources().getDrawable(R.drawable.jcb);
                case PayuConstants.SMAE:
                    return getResources().getDrawable(R.drawable.maestro);
                case PayuConstants.RUPAY:
                    return getResources().getDrawable(R.drawable.rupay);
            }
            return null;
        }else {

            switch (issuer) {
                case PayuConstants.VISA:
                    return getResources().getDrawable(R.drawable.visa, null);
                case PayuConstants.LASER:
                    return getResources().getDrawable(R.drawable.laser, null);
                case PayuConstants.DISCOVER:
                    return getResources().getDrawable(R.drawable.discover, null);
                case PayuConstants.MAES:
                    return getResources().getDrawable(R.drawable.maestro, null);
                case PayuConstants.MAST:
                    return getResources().getDrawable(R.drawable.master, null);
                case PayuConstants.AMEX:
                    return getResources().getDrawable(R.drawable.amex, null);
                case PayuConstants.DINR:
                    return getResources().getDrawable(R.drawable.diner, null);
                case PayuConstants.JCB:
                    return getResources().getDrawable(R.drawable.jcb, null);
                case PayuConstants.SMAE:
                    return getResources().getDrawable(R.drawable.maestro, null);
                case PayuConstants.RUPAY:
                    return getResources().getDrawable(R.drawable.rupay, null);
            }
            return null;
        }
    }

}
