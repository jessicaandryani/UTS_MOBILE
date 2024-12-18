// GoogleApiResponse.java
package com.jess.utsmobile;

import java.util.List;

public class GoogleApiResponse {
    public List<DataItem> data;

    public static class DataItem {
        public String category;
        public int value;
    }
}