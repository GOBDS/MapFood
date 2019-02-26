package br.com.aceleradevsp.squad2.mapfood.logistic;

import com.mongodb.client.model.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service
public class LogisticService {

    MotoboyRepository repository;

    @Autowired
    public LogisticService(MotoboyRepository repository){
        this.repository = repository;
    }

}
