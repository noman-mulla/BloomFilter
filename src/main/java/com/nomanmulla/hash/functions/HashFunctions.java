package com.nomanmulla.hash.functions;

public enum HashFunctions {

    MURMUR3("MURMUR3"),
    CITYHASH("CITYHASH"),
    FARMHASH("FARMHASH"),
    XXXHASH("XXXHASH"),
    ADLERHASH("ADLERHASH"),
    CRCHASH("CRCHASH"),
    SIPHASH("SIPHASH"),
    JENKINSHASH("JENKINSHASH");

    private String hash;

    public String getHash(){
        return this.hash;
    }

    HashFunctions(String hash){
        this.hash = hash;
    }

}
