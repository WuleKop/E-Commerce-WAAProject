package edu.mum.cs.AdminService.controller;

import edu.mum.cs.AdminService.iservice.FollowerService;
import edu.mum.cs.AdminService.iservice.UserService;
import edu.mum.cs.AdminService.model.Follower;
import edu.mum.cs.AdminService.model.User;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class FollowerController {

    @Autowired
    FollowerService followerService;

    @Autowired
    private UserService userService;

    @PostMapping("/follow")
    public @ResponseBody  Follower follow(@RequestBody Follower follower){
        return followerService.saveFollower(follower);
    }

    @GetMapping("/followedSeller/{sellerId}")
    public @ResponseBody List<User> followSeller(@PathVariable("sellerId") Long id){
        User user = userService.findOneAccount(id);
        List<Follower> followerList = followerService.sellerFollowers(user);
        return  followerList.stream().map(f-> f.getFollowing()).collect(Collectors.toList());

    }

    @GetMapping("/followingSeller/{followeId}")
    public @ResponseBody List<User> followingSeller(@PathVariable("followerId") Long id){
        User user = userService.findOneAccount(id);
        List<Follower> followerList = followerService.buyerFollowing(user);
        return  followerList.stream().map(f-> f.getFollower()).collect(Collectors.toList());
    }
}
