package com.example.lasic.xica.helpers;

/**
 * Created by lasic on 10.10.2017..
 */

public final class Constants {

    public static final String BASE_URL = "http://filestest.dbtouch.com/scst/menu/api/?place=";

    public static final class Endpoint {
        public static final String FESB = "fesb_vrh";
        public static final String KAMPUS = "kampus";
        public static final String EKONOMIJA = "efst";

        public static final String [] ENDPOINT_ARRAY = {
                FESB,
                KAMPUS,
                EKONOMIJA
        };
    }

    public static final class CanteenName {
        public static final String FESB = "FESB";
        public static final String KAMPUS = "KAMPUS";
        public static final String EKONOMIJA = "EFST";

        public static final String [] NAME_ARRAY = {
                FESB,
                KAMPUS,
                EKONOMIJA
        };
    }
}
