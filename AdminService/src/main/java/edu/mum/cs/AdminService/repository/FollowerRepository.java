package edu.mum.cs.AdminService.repository;

import edu.mum.cs.AdminService.model.Follower;
import edu.mum.cs.AdminService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower,Long> {

    @Query("select f from Follower as f where f.follower=?1")
     public List<Follower> sellerFollowers(User user);

    @Query("select f from Follower  as f where f.following=?1")
    public List<Follower> buyerFollowing(User user);
}
