package edu.mum.cs.AdminService.serviceImpl;


import edu.mum.cs.AdminService.iservice.FollowerService;
import edu.mum.cs.AdminService.model.Follower;
import edu.mum.cs.AdminService.model.User;
import edu.mum.cs.AdminService.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class FollowerServiceImpl implements FollowerService {
    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public Follower saveFollower(Follower follower) {
        return followerRepository.save(follower);
    }

    @Override
    public List<Follower> sellerFollowers(User user) {
        return followerRepository.sellerFollowers(user);
    }

    @Override
    public List<Follower> buyerFollowing(User user) {
        return followerRepository.buyerFollowing(user);
    }


}
