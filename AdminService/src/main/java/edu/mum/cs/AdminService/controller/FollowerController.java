package edu.mum.cs.AdminService.controller;

import edu.mum.cs.AdminService.iservice.FollowerService;
import edu.mum.cs.AdminService.model.Follower;
import edu.mum.cs.AdminService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class FollowerController {

    @Autowired
    FollowerService followerService;

    @PostMapping("/follow")
    public Follower follow(Follower follower){
        return followerService.saveFollower(follower);
    }

    @PostMapping("/followedSeller")
    public List<User> followSeller(@RequestBody User user){
        List<Follower> followerList = followerService.sellerFollowers(user);
        return  followerList.stream().map(f-> f.getFollowing()).collect(Collectors.toList());

    }

    @PostMapping("/followingSeller")
    public List<User> followingSeller(@RequestBody User user){
        List<Follower> followerList = followerService.buyerFollowing(user);
        return  followerList.stream().map(f-> f.getFollower()).collect(Collectors.toList());
    }
}
