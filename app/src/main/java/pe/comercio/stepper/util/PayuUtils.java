package pe.comercio.stepper.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Carlos Vargas on 8/16/16.
 */
public class PayuUtils {

    public static Set<String> SBI_MAES_BIN = new HashSet();

    public PayuUtils() {
    }

    public String getIssuer(String mCardNumber) {
        return mCardNumber.startsWith("4")?"VISA":(mCardNumber.matches("^508[5-9][0-9][0-9]|60698[5-9]|60699[0-9]|607[0-8][0-9][0-9]|6079[0-7][0-9]|60798[0-4]|(?!608000)608[0-4][0-9][0-9]|608500|6521[5-9][0-9]|652[2-9][0-9][0-9]|6530[0-9][0-9]|6531[0-4][0-9]")?"RUPAY":(mCardNumber.matches("^((6304)|(6706)|(6771)|(6709))[\\d]+")?"LASER":(mCardNumber.matches("6(?:011|5[0-9]{2})[0-9]{12}[\\d]+")?"LASER":(!mCardNumber.matches("(5[06-8]|6\\d)\\d{14}(\\d{2,3})?[\\d]+") && !mCardNumber.matches("(5[06-8]|6\\d)[\\d]+") && !mCardNumber.matches("((504([435|645|774|775|809|993]))|(60([0206]|[3845]))|(622[018])\\d)[\\d]+")?(mCardNumber.matches("^5[1-5][\\d]+")?"MAST":(mCardNumber.matches("^3[47][\\d]+")?"AMEX":(!mCardNumber.startsWith("36") && !mCardNumber.matches("^30[0-5][\\d]+") && !mCardNumber.matches("2(014|149)[\\d]+")?(mCardNumber.matches("^35(2[89]|[3-8][0-9])[\\d]+")?"JCB":""):"DINR"))):(mCardNumber.length() >= 6 && SBI_MAES_BIN.contains(mCardNumber.substring(0, 6))?"SMAE":"MAES")))));
    }

    static {
        SBI_MAES_BIN.add("504435");
        SBI_MAES_BIN.add("504645");
        SBI_MAES_BIN.add("504775");
        SBI_MAES_BIN.add("504809");
        SBI_MAES_BIN.add("504993");
        SBI_MAES_BIN.add("600206");
        SBI_MAES_BIN.add("603845");
        SBI_MAES_BIN.add("622018");
        SBI_MAES_BIN.add("504774");
    }
}
