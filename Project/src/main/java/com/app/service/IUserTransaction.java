package com.app.service;

import com.app.entities.UserTransactionDetails;

public interface IUserTransaction {

	UserTransactionDetails getTransaction(Long id);

}
