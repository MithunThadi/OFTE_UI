package com.ofte.delete.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ofte.cassandra.services.CassandraInteracter;

public class DeleteScheduler {
	// public static void main(String[] args) {
	// String schedulerName = args[0];
	// CassandraInteracter cassandraInteracter = new CassandraInteracter();
	// if (schedulerName != null) {
	// cassandraInteracter.deletingSchedulerThread(
	// cassandraInteracter.connectCassandra(), schedulerName);
	// System.out.println(schedulerName + " is successfully deleted");
	// } else {
	// try {
	// throw new Exception("scheduler name should not be empty");
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	public void delete(String taskName) {

		List<String> commands = new ArrayList<String>();
		commands.add("schtasks.exe");
		commands.add("/delete");
		commands.add("/tn");
		commands.add(taskName);
		commands.add("/f");

		ProcessBuilder builder = new ProcessBuilder(commands);
		Process processTask = null;
		try {
			processTask = builder.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			processTask.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(processTask.exitValue());
		System.out.println("done");
		CassandraInteracter cassandraInteracter = new CassandraInteracter();
		cassandraInteracter.deleteScheduler(
				cassandraInteracter.connectCassandra(), taskName);

	}
}
