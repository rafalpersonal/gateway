package com.hongxu.ripple.gateway.federation.subledger.pnp;

public enum Order {
ASC, DESC;

public String toString() {
return name().toLowerCase();
}
}