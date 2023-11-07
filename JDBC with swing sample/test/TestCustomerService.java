import dvdrental.services.CustomerCrudService;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TestCustomerService {
        public static void main(String[] args) {
                try {
                        CachedRowSet crs = CustomerCrudService.searchCustomer(new HashMap<>());
                        System.out.println("CRS size:  " + crs.size());
                        while (crs.next()) {
                                System.out.println(crs.getInt(1));
                        }
                        while (crs.nextPage()) {
                                System.out.println("Starting next page");
                                System.out.println(crs.isBeforeFirst());
                                crs.beforeFirst();
                                crs.absolute(1);
                                System.out.println(crs.getInt(1));
                        }
                        System.out.println("CRS size:  " + crs.size());

                        crs.nextPage();
                        System.out.println("CRS size:  " + crs.size());
                        crs.beforeFirst();
                        //crs.next();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
}
