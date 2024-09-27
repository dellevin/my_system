package io.dellevin.controller;

import com.alibaba.druid.util.StringUtils;
import io.dellevin.common.utils.IpUtils;
import io.dellevin.common.utils.Result;
import io.dellevin.entity.MarkVisiterEntity;
import io.dellevin.service.ApiMarkVisiterIPService;
import io.dellevin.utils.GetIpAddr;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@Api(tags="记录访问者ip")
public class ApiMarkVisiterIPControler {
    @Autowired
    ApiMarkVisiterIPService apiMarkVisiterIPService;

    @CrossOrigin(origins = "*")
    @GetMapping("markIP")
    @ApiOperation("记录ip")
    public Result<String> markIP(@RequestParam(value = "url", required = false) String url,
                                 HttpServletRequest request) {
        try {
            String urlChild = url;

            String referer = request.getHeader("Referer"); // 获取请求来源
            String sourceIp = IpUtils.getIpAddr(request);
            String ipAddr = GetIpAddr.getIpAddr(sourceIp);

            MarkVisiterEntity markVisiter = new MarkVisiterEntity();
            markVisiter.setVisitUrl(referer);
            markVisiter.setVisitUrlChild(urlChild);
            markVisiter.setIp(sourceIp);
            markVisiter.setIpAddr(ipAddr);

            // 调用DAO方法插入记录
            int result = apiMarkVisiterIPService.insertUrlVisitIP(markVisiter);
            if (result > 0) {
                System.out.printf("{网址: %s,详细网址: %s ,访问ip: %s, 访问地址: %s }\n", referer, urlChild,sourceIp, ipAddr);
                return new Result<String>().ok("ip:" + sourceIp + " -ip地址  " + ipAddr + " -网址:" + referer+ " -详细网址:" + urlChild);
            } else {
                return new Result<String>().error("记录插入失败");
            }
        } catch (Exception e) {
            return new Result<String>().error("记录插入失败");
        }
    }

//    @CrossOrigin(origins = "*")
//    @GetMapping("markIP")
//    @ApiOperation("记录ip")
//    public Result<String> markIP(HttpServletRequest request){
//        try {
//            // 获取完整的请求 URL
//            String referer = request.getHeader("Referer"); // 获取请求来源
//            String sourceIp  = IpUtils.getIpAddr(request);
//            String ipAddr = GetIpAddr.getIpAddr(sourceIp);
//
//
//            MarkVisiterEntity markVisiter =new MarkVisiterEntity();
//            markVisiter.setVisitUrl(referer);
//            markVisiter.setIp(sourceIp);
//            markVisiter.setIpAddr(ipAddr);
//
//            // 调用DAO方法插入记录
//            int result = apiMarkVisiterIPService.insertUrlVisitIP(markVisiter);
//            if (result > 0) {
//                System.out.printf("{网址: %s, 访问ip: %s, 访问地址: %s }\n", referer,sourceIp, ipAddr);
//                return new Result<String>().ok(""+sourceIp+"  -  "+ipAddr +"  -  "+referer);
//            } else {
//                return new Result<String>().error("记录插入失败");
//            }
//        } catch (Exception e) {
//            return new Result<String>().error("记录插入失败");
//        }
//    }
}
