package edu.mum.cs.AdminService.iservice;

import edu.mum.cs.AdminService.model.Follower;
import edu.mum.cs.AdminService.model.User;

import java.util.List;

public interface FollowerService {

    Follower saveFollower(Follower follower);

     List<Follower> sellerFollowers(User user);

     List<Follower> buyerFollowing(User user);
}
