package com.example.bcnet_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.models.Localitzacio;

import java.util.ArrayList;
import java.util.List;

//Singleton patern
public class LocalitzacioRespository {

    //Esta hardooded pero ho hauriem d'agafar del server

    private static LocalitzacioRespository instance;
    private ArrayList<Localitzacio> dataSet = new ArrayList<>();

    public static LocalitzacioRespository getInstance() {
        if (instance == null) {
            instance = new LocalitzacioRespository();
        }
        return instance;
    }

    public MutableLiveData<List<Localitzacio>> getLocalitzacions () {
        //Aquesta funcio es la que agafaria la info dels server, aqui esta hardcoded
        setLocalitzacions();

        MutableLiveData<List<Localitzacio>> data  = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    //retrofit
    //Per fer un setting de les dades que rebem
    private void setLocalitzacions () {
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                "Imatge 1", "Descripció del contingut")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 2", "Descripció del contingut")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 3", "Descripció del contingut")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 4", "Descripció del contingut")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 5", "Descripció del contingut")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 6", "Descripció del contingut")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 7", "Descripció del contingut")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 8", "Descripció del contingut")
        );
    }
}
