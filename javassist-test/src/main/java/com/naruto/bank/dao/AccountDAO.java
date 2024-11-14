package com.naruto.bank.dao;


import com.naruto.bank.model.Account;

/**
 * @Author: naruto
 * @CreateTime: 2024-11-14-20:15
 */
public interface AccountDAO {

    /**
     * 根据账号查询账户信息
     *
     * @param act
     * @return
     */
    Account selectByActno(String act);

    /**
     * 更新账户信息
     * @param act 被更新的账户对象
     * @return
     */
    int updateByAccount(Account act);
}
