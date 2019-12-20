package com.weisen.www.code.yjf.merchant.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_DishesbuyDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_OrderDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishesorder}.
 */
@RestController
@RequestMapping("/api/public")
@Api(tags = "000-菜品订单操作")
public class Rewrite_DishesorderResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private Rewrite_DishesorderService dishesorderService;

    public Rewrite_DishesorderResource(Rewrite_DishesorderService dishesorderService) {
        this.dishesorderService = dishesorderService;
    }

    @PostMapping("/createMenu")
    @ApiOperation("创建菜单订单")
    public ResponseEntity<Result> createMenu (@RequestBody Rewrite_OrderDTO rewrite_OrderDTO) {
        Result result = dishesorderService.createMenu(rewrite_OrderDTO);
        return ResponseEntity.ok(result);
    }
    
	//测试接口(增删改查)
	@PostMapping("/test")
    public String createTest(@RequestBody String name) {
        return "调用了MERCHANT的增加接口";
    }
    @DeleteMapping("/test/{name}")
    public String deleteTest(@PathVariable String name) {
        return "调用了MERCHANT的删除接口";
    }
    @PutMapping("/test")
    public String updateTest(@RequestBody String name) {
        return "调用了MERCHANT的修改接口";
    }
    @GetMapping("/test/{name}")
    public String getTest(@PathVariable String name) {
        return "调用了MERCHANT的查询接口";
    }
    
    @PostMapping("/getMenu")
    @ApiOperation("根据当前用户当前商店查询历史点单")
    public ResponseEntity<Result> getMenu (@RequestParam Long userid,@RequestParam Long merchantid) {
        Result result = dishesorderService.getMenu(userid, merchantid);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/updateMenu")
    @ApiOperation("根据当前用户当前商店查询历史点单")
    public ResponseEntity<Result> updateMenu (@RequestBody Rewrite_DishesbuyDTO dishesbuyDTO) {
        Result result = dishesorderService.updateMenu(dishesbuyDTO);
        return ResponseEntity.ok(result);
    }
    
}
