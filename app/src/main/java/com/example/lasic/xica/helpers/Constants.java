package com.example.lasic.xica.helpers;

/**
 * Created by lasic on 10.10.2017..
 */

public final class Constants {

    public static final String BASE_URL = "http://filestest.dbtouch.com/scst/menu/api/?place=";

    public static final class Endpoint {
        public static final String FESB = "fesb_vrh";
        public static final String KAMPUS = "kampus";
        public static final String EFST = "efst";
        public static final String FGAG = "fgag";
        public static final String MEDICINA = "medicina";
        public static final String INDEKS = "indeks";
        public static final String HOSTEL = "hostel";

        public static final String [] ENDPOINT_ARRAY = {
                FESB,
                KAMPUS,
                EFST,
                FGAG,
                MEDICINA,
                INDEKS,
                HOSTEL
        };
    }

    public static final class CanteenName {
        public static final String FESB = "FESB";
        public static final String KAMPUS = "KAMPUS";
        public static final String EFST = "EKONOMIJA";
        public static final String FGAG = "GRAĐEVINA";
        public static final String MEDICINA = "MEDICINA";
        public static final String INDEKS = "INDEKS";
        public static final String HOSTEL = "HOSTEL";

        public static final String [] NAME_ARRAY = {
                FESB,
                KAMPUS,
                EFST,
                FGAG,
                MEDICINA,
                INDEKS,
                HOSTEL
        };
    }
}
