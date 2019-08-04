package com.codingdojo.dashboard.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.dashboard.models.Subscription;
import com.codingdojo.dashboard.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	private SubscriptionRepository sR;
	
	public SubscriptionService(SubscriptionRepository sR) {
		this.sR = sR;
	}
	
	
	
	public Subscription create(Subscription subscription) {
		return sR.save(subscription);
	}
	
	public ArrayList<Subscription> findAll(){
		return (ArrayList<Subscription>) sR.findAll();
	}
	
	
	public Subscription findById(Long id) {
		return sR.findById(id).get();
	}
	
	public Subscription update(Subscription subscription) {
		return sR.save(subscription);
	}
	
	public void destroy(Long id) {
		sR.deleteById(id);
	}
}
