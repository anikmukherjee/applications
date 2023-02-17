package com.points.vo;

public class PointsVO {
    private long customerId;
	private long currentMonthPoints;
    private long lastMonthPoints;
    private long lastToLastMonthPoints;
    private long totalPoints;

    public long getCustomerId() {
        return customerId;
    }

    public long getCurrentMonthPoints() {
		return currentMonthPoints;
	}

	public void setCurrentMonthPoints(long currentMonthPoints) {
		this.currentMonthPoints = currentMonthPoints;
	}

	public long getLastMonthPoints() {
		return lastMonthPoints;
	}

	public void setLastMonthPoints(long lastMonthPoints) {
		this.lastMonthPoints = lastMonthPoints;
	}

	public long getLastToLastMonthPoints() {
		return lastToLastMonthPoints;
	}

	public void setLastToLastMonthPoints(long lastToLastMonthPoints) {
		this.lastToLastMonthPoints = lastToLastMonthPoints;
	}

	public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(long totalRewards) {
        this.totalPoints = totalRewards;
    }
}
