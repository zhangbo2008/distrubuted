package com.hc.hcbasic.designMode.creationalpattern.builder;

/**
 * 人的建造者
 * 实际除此之外还有另外一种建造者模式，但与set方法类似且不常用，所有就不管了
 *
 * @author HC
 * @create 2019-05-01 1:05
 */
public class Computer {

    public Computer(int mCpuCore, int mRamSize, String os) {
        this.mCpuCore = mCpuCore;
        this.mRamSize = mRamSize;
        this.os = os;
    }

    private int mCpuCore;

    private int mRamSize;

    private String os;

    public void setmCpuCore(int mCpuCore) {
        this.mCpuCore = mCpuCore;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setmRamSize(int mRamSize) {
        this.mRamSize = mRamSize;
    }

    public int getmCpuCore() {
        return mCpuCore;
    }

    public int getmRamSize() {
        return mRamSize;
    }

    public String getOs() {
        return os;
    }

    public static class ComputerBuilder {
        private int mCpuCore;

        private int mRamSize;

        private String os;

        public ComputerBuilder mCpuCore(int core) {
            this.mCpuCore = core;
            return this;
        }

        public ComputerBuilder mRamSize(int mRamSize) {
            this.mRamSize = mRamSize;
            return this;
        }

        public ComputerBuilder os(String os) {
            this.os = os;
            return this;
        }

        public Computer build() {
            return new Computer(this.mCpuCore, this.mRamSize, this.os);
        }

    }

    @Override
    public String toString() {
        return this.mRamSize + ":" + this.mCpuCore + ":" + this.os;
    }

    public static void main(String[] args) {
        System.out.println(new ComputerBuilder().mCpuCore(1).mRamSize(2).os("ali").build());
    }
}
