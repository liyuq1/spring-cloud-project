package cfca.trustsign.demo.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.EnterpriseTransactorVO;
import cfca.trustsign.common.vo.cs.EnterpriseVO;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.request.tx3.Tx3002ReqVO;
import cfca.trustsign.demo.connector.HttpConnector;
import cfca.trustsign.demo.constant.Request;
import cfca.trustsign.demo.converter.JsonObjectMapper;
import cfca.trustsign.demo.util.SecurityUtil;
import cfca.trustsign.demo.util.TimeUtil;

public class Test3002 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3002ReqVO tx3002ReqVO = new Tx3002ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        EnterpriseVO enterprise = new EnterpriseVO();
        enterprise.setEnterpriseName("千玺文化传媒有限公司");
        enterprise.setIdentTypeCode("8");
        enterprise.setIdentNo("9112383482348238423");
        enterprise.setMobilePhone("15596357026");
        enterprise.setLandlinePhone("69226906");
        enterprise.setAuthenticationMode("公安部数据联网核查");

        EnterpriseTransactorVO enterpriseTransactor = new EnterpriseTransactorVO();
        enterpriseTransactor.setTransactorName("李瑜");
        enterpriseTransactor.setIdentTypeCode("0");
        enterpriseTransactor.setIdentNo("610321199412303116");
        // enterpriseTransactor.setAddress("beijing");

        tx3002ReqVO.setHead(head);
        tx3002ReqVO.setEnterprise(enterprise);
        tx3002ReqVO.setEnterpriseTransactor(enterpriseTransactor);
        // tx3002ReqVO.setSealColor(2);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3002ReqVO);
        System.out.println("req:" + req);

        String txCode = "3002";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
