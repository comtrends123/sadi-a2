package controller;

import model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserInfoService;

import java.util.List;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    // Create
    @RequestMapping(path = "/userInfoss/create", method = RequestMethod.POST)
    public void saveUserInfoToDatabase(@RequestBody UserInfo userInfo){
        userInfoService.saveUserInfoToDatabase(userInfo);
    }

    // Read
    @RequestMapping(path = "/userInfoss/getAll", method = RequestMethod.GET)
    public List<UserInfo> getAllUserInfoss(){
        return userInfoService.getAllUserInfoss();
    }

    @RequestMapping(path="/userInfoss/readByuid/{uid}", method = RequestMethod.GET)
    public UserInfo getUserInfoFromDatabaseByuid(@PathVariable int uid){
        return userInfoService.getUserInfoFromDatabaseByuid(uid);
    }

    @RequestMapping(path="/userInfoss/readByUsername/{username}", method = RequestMethod.GET)
    public UserInfo getUserInfoFromDatabaseByUsernane(@PathVariable String username){
        return userInfoService.getUserInfoFromDatabaseByUsernane(username);
    }

    @RequestMapping(path="/userInfoss/readByUsername/{name}", method = RequestMethod.GET)
    public List<UserInfo> getUserInfoFromDatabaseByName(@PathVariable String name){
        return userInfoService.getUserInfoFromDatabaseByName(name);
    }

    @RequestMapping(path="/userInfoss/readByRole/{role}", method = RequestMethod.GET)
    public List<UserInfo> getUserInfoFromDatabaseByRole(@PathVariable Boolean role){
        return userInfoService.getUserInfoFromDatabaseByRole(role);
    }

    // Update
    @RequestMapping(path = "/userInfoss/updateUsername", method = RequestMethod.PATCH)
    public void updateUsernameOfUserInfoFromDatabase(@RequestBody UserInfo userInfoWithNewUsername){
        userInfoService.updateUsernameOfUserInfoFromDatabase(userInfoWithNewUsername);
    }

    @RequestMapping(path = "/userInfoss/updatePassword/{oldpassword}", method = RequestMethod.PATCH)
    public void updatePasswordOfUserInfoFromDatabase(@RequestBody UserInfo userInfoWithNewPassword, @PathVariable String oldpassword){
        userInfoService.updatePasswordOfUserInfoFromDatabase(userInfoWithNewPassword, oldpassword);
    }

    @RequestMapping(path = "/userInfoss/updateName", method = RequestMethod.PATCH)
    public void updateNameOfUserInfoFromDatabase(@RequestBody UserInfo userInfoWithNewName){
        userInfoService.updateNameOfUserInfoFromDatabase(userInfoWithNewName);
    }

    @RequestMapping(path = "/userInfoss/updateRole", method = RequestMethod.PATCH)
    public void updateRoleOfUserInfoFromDatabase(@RequestBody UserInfo userInfoWithNewRole){
        userInfoService.updateRoleOfUserInfoFromDatabase(userInfoWithNewRole);
    }

    // Delete
    @RequestMapping(path = "/userInfoss/deleteByuid/{uid}", method =RequestMethod.DELETE)
    public void deleteUserInfoFromDatabase(@PathVariable int uid){
        UserInfo userInfo = userInfoService.getUserInfoFromDatabaseByuid(uid);
        userInfoService.deleteUserInfoFromDatabase(userInfo);
    }
}
