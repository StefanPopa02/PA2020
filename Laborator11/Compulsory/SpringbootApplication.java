package com.popastefan;

import com.popastefan.datasource.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        Database myDb = Database.getMyDatabase();
        myDb.openDb();
        SpringApplication.run(SpringbootApplication.class, args);
        //myDb.closeDb();

    }

}
