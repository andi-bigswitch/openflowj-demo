package org.openflow.proto.type;

public class MacAddressMask {

    public MacAddressMask(String addr, String mask) {
    }

    public static MacAddressMask of(String addr, String mask) {
        return new MacAddressMask(addr, mask);
    }

}
