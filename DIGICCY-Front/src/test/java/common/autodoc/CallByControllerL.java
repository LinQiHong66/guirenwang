package common.autodoc;

import com.inesv.digiccy.util.UserCardIdUtil;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class CallByControllerL {

    public static void main(String[] args) {
        getAddressController();
        //getFicWithdrawController();
    }

    public static void getAddressController() {
        HttpRequest httpRequest = HttpRequest.post("http://localhost:8081/address/getUserAddress.do");
        httpRequest.form("userNo", 1);
        HttpResponse httpResponse = httpRequest.send();
        System.out.println(httpResponse.bodyText());
    }

    public static void getFicWithdrawController() {
        HttpRequest httpRequest = HttpRequest.post("http://localhost:8081/virtualWith/getWithdrawinfo.do");
        httpRequest.form("userNo", 1);
        httpRequest.form("coinNo", 1);
        HttpResponse httpResponse = httpRequest.send();
        System.out.println(httpResponse.bodyText());
    }
}
