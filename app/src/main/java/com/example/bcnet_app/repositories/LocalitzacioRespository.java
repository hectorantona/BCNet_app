package com.example.bcnet_app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.bcnet_app.models.Localitzacio;

import java.util.ArrayList;
import java.util.List;

//Singleton patern
public class LocalitzacioRespository {

    //Aquest codi ha de anar a algun activity dins del onCreate sino queue no funciona
    /*
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
    Call<List<Localitzacio>> crida = jsonPlaceHolderAPI.getLoca();
    crida.enqueue(new Callback<List<Localitzacio>>() {

    }*/

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
                "Imatge 1", "Descripció del contingut", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 2", "Descripció del contingut", "Monument", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 3", "Descripció del contingut", "Museu", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 4", "Descripció del contingut", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 5", "Descripció del contingut", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 6", "Descripció del contingut", "Monument", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 7", "Descripció del contingut", "Museu", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://cnnespanol.cnn.com/wp-content/uploads/2019/12/s_64a163f16ecbb099e52f2f8271f73cbbfcfc9034be4d646f7375e4db1ca6f3d7_1573501883482_ap_19001106049831-1.jpg?quality=100&strip=info&w=320&h=240&crop=1",
                        "Imatge 8", "Descripció del contingut", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        /*dataSet.add(
                new Localitzacio("https://res.cloudinary.com/tf-lab/image/upload/w_600,h_337,c_fill,g_auto:subject,q_auto,f_auto/restaurant/c2882126-dafa-49e9-a355-762b7cf109ed/1d5b1207-8869-4173-8386-e574e8e095ac.jpg", "Addis Abeba", "Cuina Etíop, " +
                        "dilluns\tTancat\n" +
                        "dimarts\t13:00–15:30\n" +
                        "dimecres\t13:00–15:30\n" +
                        "dijous\t13:00–15:30\n" +
                        "divendres\t13:00–15:30\n" +
                        "dissabte\t13:00–15:30\n" +
                        "diumenge\t13:00–15:30\n" , "Restaurant","https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg" )
        );*/
    }
}
