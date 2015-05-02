package com.progbook.persistence.model;

public enum VoteType{
    UP(1),DOWN(-1),NIL(0);
    private final int value;

    private VoteType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public VoteType toVoteType(int i){
        if(i == 1) return VoteType.UP;
        if(i == -1) return VoteType.DOWN;
        return VoteType.NIL;
    }
}
