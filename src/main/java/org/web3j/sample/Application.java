/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.web3j.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.math.BigDecimal;

/*

https://medium.com/coinmonks/ethereum-blockchain-hello-world-smart-contract-with-java-9b6ae2961ad1

*/


public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        // We start by creating a new web3j instance to connect to remote nodes on the network.
        Web3j web3j = Web3j.build(new HttpService());
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "supersafepassword",
                        "chaindata\\keystore\\UTC--2018--<rest of the file name>");
        log.info("Credentials loaded");
        log.info("Sending Ether ..");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0xf900D5fEEf974F058543E47B27C801D396684266",  // you can put any address here
                BigDecimal.valueOf(100), Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
                .sendAsync().get();
        log.info("Transaction complete : "
                + transferReceipt.getTransactionHash());
    }
}