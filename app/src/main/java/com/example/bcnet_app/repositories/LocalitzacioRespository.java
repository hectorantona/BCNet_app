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
        dataSet = new ArrayList<>();
        Localitzacio l = new Localitzacio("https://media-cdn.tripadvisor.com/media/photo-s/14/84/ef/e9/best-napolitan-pizza.jpg",
                "Pizzeria Da Nanni Barcelona", "Italiana, Pizza, Comida rápida, Mediterránea, Europea, Napolitana, De Campania, Del sur de Italia",
                "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg");
        dataSet.add(l);
        dataSet.add(
                new Localitzacio("https://media-cdn.tripadvisor.com/media/photo-p/1a/dc/2b/ae/american-breakfast-served.jpg",
                        "La Desayuneria", "Americana, Café, Europea, Internacional", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://www.ecestaticos.com/imagestatic/clipping/f98/48d/f9848d91a1991696ca12e3bef23eb091/por-que-tardan-tanto-en-terminar-la-sagrada-familia-la-historia-de-sus-138-anos-de-construccion.jpg?mtime=1600953980",
                        "La Sagrada Familia", "basílica catòlica situada a la ciutat de Barcelona. És un dels exemples més coneguts del modernisme català i un edifici únic al món, que ha esdevingut tot un símbol de la ciutat. ", "Monument", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://media-cdn.tripadvisor.com/media/photo-s/1a/d4/1a/36/tagliere.jpg",
                        "Don Kilo Gourmet", "Pizza, Del sur de Italia, Italiana, Napolitana, De Campania, Mediterránea", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://www.google.com/url?sa=i&url=https%3A%2F%2Fbarcelonasecreta.com%2Fmonumentos-en-barcelona-que-no-te-puedes-perder%2F&psig=AOvVaw3biNT2adciBk9_FyLr4ygi&ust=1616744433365000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLjqkcD4yu8CFQAAAAAdAAAAABAD",
                        "Arc de Triomf", "L'Arc de Triomf és un monument de Barcelona que es troba a la confluència entre el Passeig de Lluís Companys, el Passeig de Sant Joan i la ronda de Sant Pere.", "Monument", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://ajbcn-nasia-prod.s3.amazonaws.com/nasia-pro/media/2017%2C10%2C04115437%2Cplaca_rei_muhba2-760x428.jpg",
                        "Museus d'Hstòria de Barcelona", "El Museu d'Història de Barcelona o Museu d'Història de la Ciutat és un museu de ciutat que conserva, estudia, documenta, divulga i exposa el patrimoni històric i la història de Barcelona des dels orígens fins al present.", "Museu", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://media-cdn.tripadvisor.com/media/photo-s/04/17/ed/13/bodega-biarritz-1881.jpg",
                        "Bodega Biarritz 1881", "Mediterránea, Europea, Española, Saludable, Catalana", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
        );
        dataSet.add(
                new Localitzacio("https://media-cdn.tripadvisor.com/media/photo-s/1b/9d/8f/a2/una-cerveza-fria-y-una.jpg",
                        "Brunch & Bakery", "Americana, Pub con cerveza artesanal, Bar, Café, Internacional, Pub", "Restaurant", "https://i.pinimg.com/474x/8d/49/08/8d4908649aef4c34bd95e82b2e481841.jpg")
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
