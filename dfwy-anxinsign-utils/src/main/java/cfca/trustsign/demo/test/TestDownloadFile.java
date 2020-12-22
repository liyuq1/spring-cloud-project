package cfca.trustsign.demo.test;

import java.io.File;
import java.io.FileOutputStream;

import cfca.trustsign.demo.connector.HttpConnector;
import cfca.trustsign.demo.constant.Request;

public class TestDownloadFile {
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        String contractNo = "JK20201208000000978"; //todo 调完3203 根据返回的contractNo 下载合同
        byte[] fileBtye = httpConnector.getFile("platId/" + Request.PLAT_ID + "/contractNo/" + contractNo + "/downloading");
        if (fileBtye == null || fileBtye.length == 0) {
            return;
        }

        try {
            String filePath = "E:\\学习工程\\spring-cloud-project\\dfwy-anxinsign-utils\\src\\main\\resources\\file";
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(filePath + File.separator + contractNo + ".pdf");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(fileBtye);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
