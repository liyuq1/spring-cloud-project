package cfca.trustsign.demo.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.PersonVO;
import cfca.trustsign.common.vo.request.tx3.Tx3001ReqVO;
import cfca.trustsign.demo.connector.HttpConnector;
import cfca.trustsign.demo.constant.Request;
import cfca.trustsign.demo.converter.JsonObjectMapper;
import cfca.trustsign.demo.util.SecurityUtil;
import cfca.trustsign.demo.util.TimeUtil;

/**
 * 注册用户
 */
public class Test3001 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3001ReqVO tx3001ReqVO = new Tx3001ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        PersonVO person = new PersonVO();
        person.setPersonName("李瑜");
        person.setIdentTypeCode("0");
        person.setIdentNo("610321199412303116");
        person.setMobilePhone("15596357026");
        person.setAuthenticationMode("公安部数据联网核查");

        tx3001ReqVO.setHead(head);
        tx3001ReqVO.setPerson(person);
        // 身份认证时需要
        tx3001ReqVO.setIsVerifyBankCard(0);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3001ReqVO);
        System.out.println("req:" + req);

        String txCode = "3001";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
/*        {
            "head": {
            "txTime": "20201124175201",
                    "retCode": "60000000",
                    "retMessage": "OK"
        },
            "person": {
            "userId": "B46DAC2F4B5247CFE05312016B0A86BA",
                    "personName": "李瑜",
                    "identTypeCode": "0",
                    "identNo": "610321199412303116",
                    "mobilePhone": "15596357026",
                    "authenticationMode": "公安部",
                    "anXinSignMobilePhone": "155****7026",
                    "usedEmailLogin": 1,
                    "usedMobileLogin": 1,
                    "isOpenSM2": 0
        },
            "notSendPwd": 0
        }*/
    }
}
