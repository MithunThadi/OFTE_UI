package com.ofte.file.services;

import java.io.IOException;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.apache.kafka.clients.consumer.internals.NoAvailableBrokersException;

import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.ofte.cassandra.services.CassandraInteracter;

import kafka.common.KafkaException;
import kafka.common.KafkaStorageException;
import kafka.utils.ZkUtils;

public class WorkerThread implements Runnable {

	public Map<String, String> metaDataMap;
	public Map<String, String> transferMetaData;
	public ZkClient zkClient;
	public ZkUtils zkUtils;
	public String sourceFileName;

	public WorkerThread(String sourceFileName, ZkClient zkClient,
			ZkUtils zkUtils, Map<String, String> metaDataMap,
			Map<String, String> transferMetaData) {

		this.sourceFileName = sourceFileName;
		this.zkClient = zkClient;
		this.zkUtils = zkUtils;
		this.metaDataMap = metaDataMap;
		this.transferMetaData = transferMetaData;
	}

	public void run() {
		// String sPath = transferMetaData.get("sourceFileName").toString();
		// System.out.println("Entered into thread " + sPath);
		FilesProcessorService filesProcessorService = new FilesProcessorService();
		CassandraInteracter cassandraInteracter = new CassandraInteracter();

		try {
			filesProcessorService.getMessages(sourceFileName, zkClient, zkUtils,
					metaDataMap, transferMetaData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (KafkaException e) {
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (KafkaStorageException e) {
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (ZkException e) {
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (NoHostAvailableException e) {
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		} catch (NoAvailableBrokersException e) {
			cassandraInteracter.updatingException(
					cassandraInteracter.connectCassandra(), transferMetaData,
					e);
			e.printStackTrace();
		}

		// Lock lock = new ReentrantLock();
		// FilesProcessor fileProcessor = new FilesProcessor();
		// lock.lock();
		// fileProcessor.getMessages(sPath, metaDataMap, transferMetaData);
		System.out.println("fileProcessor releasing");
		// lock.unlock();
	}

}
