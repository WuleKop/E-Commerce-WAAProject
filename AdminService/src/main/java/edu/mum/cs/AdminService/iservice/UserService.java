package edu.mum.cs.AdminService.iservice;

import edu.mum.cs.AdminService.model.User;

import java.util.List;

public interface UserService {
     User findUserByEmail(String email) ;
    User saveUser(User user);

    User findOneAccount(Long id);

    List<User> findAll();

    void delete(User user);

}
