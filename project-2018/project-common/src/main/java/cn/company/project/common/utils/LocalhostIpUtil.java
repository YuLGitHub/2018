package cn.company.project.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author yule
 * @date 2018/08/22 19:00
 * @since JDK1.8
 */
public class LocalhostIpUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取服务器地址
     *
     * @return Ip地址
     */
    public String getServerIp() {
        String ip = "";
        // 获取操作系统类型
        String sysType = System.getProperties().getProperty("os.name");
        /*
        如果是Windows系统，获取本地IP地址
         */
        if (sysType.toLowerCase().startsWith("win")) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                logger.error("getServerIp fail,e:{}",e);
            }
        } else if (sysType.toLowerCase().startsWith("eth")){
            // 兼容Linux
            ip = getIpByEthName("eth0");
        } else if (sysType.toLowerCase().startsWith("en")) {
            // 兼容Mac电脑
            ip = getIpByEthName("en0");
        }
        return ip;
    }

    /**
     * 根据网络接口获取IP地址
     * @param ethName 网络接口名，Linux下是eth0
     * @return
     */
    private String getIpByEthName(String ethName) {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (ethName.equals(netInterface.getName())) {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (!ObjectUtils.isEmpty(ip)) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            logger.error("getIpByEthNum fail,e:{}",e);
        }
        return null;
    }
}
