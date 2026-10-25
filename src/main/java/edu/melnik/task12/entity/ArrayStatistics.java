package edu.melnik.task12.entity;

public class ArrayStatistics {
    private final int min;
    private final int max;
    private final int sum;
    private final double average;
    private final int arraySize;

    public ArrayStatistics(int min, int max, int sum, double average, int arraySize) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.average = average;
        this.arraySize = arraySize;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public int getArraySize() {
        return arraySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayStatistics that = (ArrayStatistics) o;
        return min == that.min && max == that.max && sum == that.sum && Double.compare(average, that.average) == 0 && arraySize == that.arraySize;
    }

    @Override
    public int hashCode() {
        int result = min;
        result = 31 * result + max;
        result = 31 * result + sum;
        result = 31 * result + (int) average;
        result = 31 * result + arraySize;

        // return Objects.hash(min, max, sum, average, arraySize);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayStatistics{");
        sb.append("min=");
        sb.append(min);
        sb.append(", max=");
        sb.append(max);
        sb.append(", sum=");
        sb.append(sum);
        sb.append(", average=");
        sb.append(average);
        sb.append(", ArraySize=");
        sb.append(arraySize);
        sb.append('}');

        return sb.toString();
    }
}
