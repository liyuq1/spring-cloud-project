package cfca.trustsign.demo.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.ProxySignVO;
import cfca.trustsign.common.vo.request.tx3.Tx3101ReqVO;
import cfca.trustsign.demo.connector.HttpConnector;
import cfca.trustsign.demo.constant.Request;
import cfca.trustsign.demo.converter.JsonObjectMapper;
import cfca.trustsign.demo.util.SecurityUtil;
import cfca.trustsign.demo.util.TimeUtil;

/**
 * 发送短信
 */
public class Test3101 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3101ReqVO tx3101ReqVO = new Tx3101ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        ProxySignVO proxySignVO = new ProxySignVO();
        proxySignVO.setUserId("B46DAC2F4B5247CFE05312016B0A86BA");
        //todo  授权的项目编号 自定义后面要用 验证短信验证码  和 签合同授权项目编号用同一个
        proxySignVO.setProjectCode("yshdbbw_8a818e8e763d553e01763d553ec50000");
//        proxySignVO.setIsSendVoice(1);
//        proxySignVO.setSmsTemplateId("SMS-1");
//        proxySignVO.setSmsRemark("2333333");
//        proxySignVO.setEmailTemplateId("1");
//        proxySignVO.setEmailRemark("312313");

        tx3101ReqVO.setHead(head);
        tx3101ReqVO.setProxySign(proxySignVO);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3101ReqVO);
        System.out.println("req:" + req);

        String txCode = "3101";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
