package com.codingdojo.dashboard.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.dashboard.models.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

}
