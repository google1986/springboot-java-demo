package com.htzw.transaction.controller;

import com.htzw.transaction.service.PlatformTransactionManagerService;
import com.htzw.transaction.service.TransactionTemplateService;
import com.htzw.transaction.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务 Controller
 *
 */
@RestController
public class UserController {

    @Autowired
    private PlatformTransactionManagerService platformTransactionManagerService;
    @Autowired
    private TransactionTemplateService transactionTemplateService;
    @Autowired
    private TransactionalService transactionalService;

    /**
     * 测试 platformTransactionManager
     */
    @GetMapping("/test1")
    public void addUser1(){
        platformTransactionManagerService.addUser();
    }

    /**
     * 测试 transactionTemplate
     */
    @GetMapping("/test2")
    public void addUser2(){
        transactionTemplateService.addUser();
    }

    /**
     * 测试 @Transactional
     */
    @GetMapping("/test3")
    public void addUser3(){
        transactionalService.addUser();
    }

}
