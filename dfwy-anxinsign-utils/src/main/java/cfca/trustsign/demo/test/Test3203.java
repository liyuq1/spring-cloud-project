package cfca.trustsign.demo.test;

import java.io.File;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.SignLocationVO;
import cfca.trustsign.common.vo.cs.UploadContractVO;
import cfca.trustsign.common.vo.cs.UploadSignInfoVO;
import cfca.trustsign.common.vo.request.tx3.Tx3203ReqVO;
import cfca.trustsign.demo.connector.HttpConnector;
import cfca.trustsign.demo.constant.Request;
import cfca.trustsign.demo.converter.JsonObjectMapper;
import cfca.trustsign.demo.util.SecurityUtil;
import cfca.trustsign.demo.util.TimeUtil;

public class Test3203 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3203ReqVO tx3203ReqVO = new Tx3203ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        UploadContractVO uploadContract = new UploadContractVO();
        uploadContract.setContractTypeCode("JK");
        uploadContract.setContractName("测试合同");
        uploadContract.setIsSign(1);  // 0：抄送；1：签署；2：暂不签署；默认为0

        UploadSignInfoVO[] signInfos = new UploadSignInfoVO[2];
        UploadSignInfoVO signInfoVO0 = new UploadSignInfoVO();
        signInfoVO0.setUserId("B46DAC2F4B5247CFE05312016B0A86BA"); //todo 注册用户时返回的 userId
        signInfoVO0.setIsProxySign(0);
        UploadSignInfoVO signInfoVO1 = new UploadSignInfoVO();
        signInfoVO1.setUserId("B5E0E33DA0CC05EAE05312016B0A66F0"); //todo 注册用户时返回的 userId
        signInfoVO1.setIsProxySign(0);
        //signInfoVO0.setLocation("陕西省西安市"); //todo  授权地址
        //个人签章
/*        SignLocationVO[] signLocations0 = new SignLocationVO[1];
        SignLocationVO signLocation0 = new SignLocationVO();
        signLocation0.setSignOnPage("1"); //签名域所在的合同页数
        signLocation0.setSignLocationLBX("85");  //签名域的左下角X轴坐标值
        signLocation0.setSignLocationLBY("550"); //签名域的左下角Y轴坐标值
        signLocation0.setSignLocationRUX("140"); //签名域的右上角X轴坐标值
        signLocation0.setSignLocationRUY("575"); //签名域的右上角Y轴坐标值
        signLocations0[0] = signLocation0;
        signInfoVO0.setSignLocations(signLocations0);*/
/*        signInfoVO0.setProjectCode("001"); //todo 和授权过的项目编号一致
        signInfoVO0.setIsCheckProjectCode(1);*/
        //signInfoVO0.setAuthorizationTime("20201119150300");
        //signInfoVO0.setCertType(1);
        signInfos[0] = signInfoVO0;
        signInfos[1] =signInfoVO1;


        //个人签章
        SignLocationVO[] signLocationsPlat = new SignLocationVO[1];
        SignLocationVO signLocationPlat = new SignLocationVO();
        signLocationPlat.setSignOnPage("2");//签名域所在的合同页数
        signLocationPlat.setSignLocationLBX("310"); //签名域的左下角X轴坐标值
        signLocationPlat.setSignLocationLBY("10"); //签名域的左下角Y轴坐标值
        signLocationPlat.setSignLocationRUX("440"); //签名域的右上角X轴坐标值
        signLocationPlat.setSignLocationRUY("130"); // 签名域的右上角Y轴坐标值
        signLocationsPlat[0] = signLocationPlat;
        uploadContract.setSignLocations(signLocationsPlat);
        uploadContract.setSignInfos(signInfos);
        tx3203ReqVO.setHead(head);
        tx3203ReqVO.setUploadContract(uploadContract);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3203ReqVO);
        System.out.println("req:" + req);

        File file = new File("E:\\学习工程\\spring-cloud-project\\dfwy-anxinsign-utils\\src\\main\\resources\\file\\3.pdf");

        String txCode = "3203";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature, file);
        System.out.println("res:" + res);
 /*       {
            "head": {
            "txTime": "20201124180539",
                    "retCode": "60000000",
                    "retMessage": "OK"
        },
            "contract": {
            "contractNo": "MM20201124000000888",
                    "contractTypeCode": "MM",
                    "contractName": "测试合同",
                    "contractState": 1,
                    "createTime": "20201124180540",
                    "expiredDate": "20201224",
                    "fileId": "2258399c-d655-4019-a52e-107a612c9b98",
                    "signatories": [{
                "userId": "71A35B81C9CF55E8E05312016B0AB80A",
                        "userName": "广西北部湾银行股份有限公司",
                        "identTypeCode": "8",
                        "identNo": "914500001983761846",
                        "signmentState": 1,
                        "signTime": 20201124180540
            }, {
                "userId": "B46DAC2F4B5247CFE05312016B0A86BA",
                        "userName": "李瑜",
                        "identTypeCode": "0",
                        "identNo": "610321199412303116",
                        "signmentState": 1,
                        "signTime": 20201124180540
            }]
        }
        }*/
    }
}
