package com.tensquare.user.service;

import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dongcheng
 * create date 2019/3/7
 **/
@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void save(Admin admin){
        adminDao.save(admin);
    }

    public Admin login(Admin admin){
        if (admin != null || "".equals(admin.getPassword())){
            admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));

            Admin loginAdmin = adminDao.findByLoginname(admin.getLoginname());

            if(loginAdmin!=null){
                if (loginAdmin.getPassword().equals(admin.getPassword())){
                    return loginAdmin;
                }
            }
        }
        return null;
    }


}
