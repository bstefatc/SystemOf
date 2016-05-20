package com.egnaro.shsm.entity;

import java.io.Serializable;

public class MonitoredNode implements Serializable {
	private String id;
	private String ipAddress;
	private String name;
	private String description;
	private String status;
	private int periodicity;
	private int heartBeatGracePeriod;
	private MonitoredService monitoredServices;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}

	public int getHeartBeatGracePeriod() {
		return heartBeatGracePeriod;
	}

	public void setHeartBeatGracePeriod(int heartBeatGracePeriod) {
		this.heartBeatGracePeriod = heartBeatGracePeriod;
	}

	public MonitoredService getMonitoredServices() {
		return monitoredServices;
	}

	public void setMonitoredServices(MonitoredService monitoredServices) {
		this.monitoredServices = monitoredServices;
	}

	public static class MonitoredService implements Serializable {
		private boolean mongo;
		private boolean redis;
		private boolean docker;
		private boolean marathon;
		private boolean mesos;
		private boolean morphia;
		private boolean nginx;

		public boolean isMongo() {
			return mongo;
		}

		public void setMongo(boolean mongo) {
			this.mongo = mongo;
		}

		public boolean isRedis() {
			return redis;
		}

		public void setRedis(boolean redis) {
			this.redis = redis;
		}

		public boolean isDocker() {
			return docker;
		}

		public void setDocker(boolean docker) {
			this.docker = docker;
		}

		public boolean isMarathon() {
			return marathon;
		}

		public void setMarathon(boolean marathon) {
			this.marathon = marathon;
		}

		public boolean isMesos() {
			return mesos;
		}

		public void setMesos(boolean mesos) {
			this.mesos = mesos;
		}

		public boolean isMorphia() {
			return morphia;
		}

		public void setMorphia(boolean morphia) {
			this.morphia = morphia;
		}

		public boolean isNginx() {
			return nginx;
		}

		public void setNginx(boolean nginx) {
			this.nginx = nginx;
		}

	}
}
