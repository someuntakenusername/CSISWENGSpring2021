package esc.baylor.edu.groupProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {
	
	Transaction transaction;
	
	@BeforeEach
	void init() {
		transaction = new Transaction();
	}
}
