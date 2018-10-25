package cn.com.study.boot.customer.controller;

import cn.com.study.boot.customer.jpa.po.CustomerPO;
import cn.com.study.boot.customer.service.CustomerService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Resource
    private CustomerService customerService;


    @RequestMapping("/queryAllUser")
    public List<CustomerPO> queryAllAvailableCustomer(@RequestParam(required = false)
                                                              Map<String, Object> requestParam) {
        CustomerPO customerPO = JSONObject.parseObject(JSON.toJSONString(requestParam), CustomerPO.class);
        return customerService.queryAllCustomer(customerPO);
    }

}
