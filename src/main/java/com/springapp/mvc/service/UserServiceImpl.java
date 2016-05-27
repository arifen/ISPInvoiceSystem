package com.springapp.mvc.service;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by arifen on 5/26/16.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveUser(User user) {
        if(user != null){
            logger.debug("User "+user);
            user.setPassword(passwordEncoder.encodePassword(user.getPassword(),null));
            userDao.addUser(user);
            logger.debug("uer has saved");
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        logger.debug("loginId "+loginId);
        if(userDao == null){
            logger.debug("userdao is null");
        }

        User user = userDao.findUserByLoginId(loginId);
        System.out.println("UserRole : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role: user.getRole()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }
}
