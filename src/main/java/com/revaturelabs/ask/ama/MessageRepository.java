package com.revaturelabs.ask.ama;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Bianca/Updates to follow.
 *
 */

@Repository
public interface MessageRepository extends JpaRepository<MessageTable, Integer> {
	
	public MessageTable save (MessageTable messageTable);
}



	
