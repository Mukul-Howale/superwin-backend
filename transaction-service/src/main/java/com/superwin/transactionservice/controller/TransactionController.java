package com.superwin.transactionservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransactionController {


    /**
     *  Flow for transaction
     *
     *  Deposit:
     *      1. Select transaction method
     *      2. Enter amount
     *      3. Initiate transaction
     *      check authenticity and availability for transaction method
     *      Also Check the amount is within the limit of transaction
     *      and not exceed the limit for a particular transaction method
     *      4. Once the transaction is successful, update the amount
     *      5.
     */
}
