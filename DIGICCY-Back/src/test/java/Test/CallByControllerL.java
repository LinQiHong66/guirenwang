package Test;

import javax.servlet.http.HttpServletResponse;

import com.inesv.digiccy.util.sinapay.SinaPayConstants;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class CallByControllerL {

    public static void main(String[] args) {
        //getCrowdFundingDetailController();
        //getFicController();
        getCoinController();
    }

    public static void getCrowdFundingDetailController() {
        HttpRequest httpRequest = HttpRequest.get("http://localhost:8080/crowFundingDetail/getAllCrowdFundingDetail.do");
        HttpResponse httpResponse = httpRequest.send();
        System.out.println(httpResponse.bodyText());
    }

    public static void getFicController() {
        HttpRequest httpRequest = HttpRequest.get("http://localhost:8080/fic/getWithdraw.do");
        httpRequest.query("userName", "123");
        httpRequest.query("coinTypeSearch", "");
        httpRequest.query("startData", "");
        httpRequest.query("endData", "");
        HttpResponse httpResponse = httpRequest.send();
        System.out.println(httpResponse.bodyText());
    }

    public static void getCoinController() {
        HttpRequest httpRequest = HttpRequest.get("http://localhost:8080/coin/getAllCrowdCoin.do");
        HttpResponse httpResponse = httpRequest.send();
        System.out.println(httpResponse.bodyText());
    }
}