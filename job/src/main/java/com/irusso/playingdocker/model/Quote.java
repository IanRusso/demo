package com.irusso.playingdocker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Quote {

  @JsonProperty("c")
  private double current;
  @JsonProperty("h")
  private double high;
  @JsonProperty("l")
  private double low;
  @JsonProperty("o")
  private double open;
  @JsonProperty("pc")
  private double previousClose;
  @JsonProperty("t")
  private long timestamp;

  public double getCurrent() {
    return current;
  }

  public void setCurrent(double current) {
    this.current = current;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getPreviousClose() {
    return previousClose;
  }

  public void setPreviousClose(double previousClose) {
    this.previousClose = previousClose;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Quote quote = (Quote) o;
    return Double.compare(quote.current, current) == 0 &&
        Double.compare(quote.high, high) == 0 &&
        Double.compare(quote.low, low) == 0 &&
        Double.compare(quote.open, open) == 0 &&
        Double.compare(quote.previousClose, previousClose) == 0 &&
        timestamp == quote.timestamp;
  }

  @Override
  public int hashCode() {
    return Objects.hash(current, high, low, open, previousClose, timestamp);
  }

  @Override
  public String toString() {
    return "Quote{" +
        "current=" + current +
        ", high=" + high +
        ", low=" + low +
        ", open=" + open +
        ", previousClose=" + previousClose +
        ", timestamp=" + timestamp +
        '}';
  }
}
