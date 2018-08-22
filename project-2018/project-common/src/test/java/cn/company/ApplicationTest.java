package cn.company;

import cn.company.project.common.utils.LocalhostIpUtil;
import cn.company.project.common.utils.RegIPUtil;
import junit.framework.TestCase;

/**
 * 项目测试类
 */
public class ApplicationTest extends TestCase {

    /**
     * 测试匹配IP代码
     */
    public void testReg() {
        String ipv4 = "rstp://192.168.40.228:554/live";
        String ip = RegIPUtil.regIP(ipv4);
        System.out.println(ip);
    }

    /**
     * 测试获取本地IP地址
     */
    public void testGetLocalIP() {
        System.out.println(new LocalhostIpUtil().getServerIp());
    }
}
