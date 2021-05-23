package com.example.bcnet_app.TabbedMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    private String nomlocalitzacio;
    private String latitud;
    private String longitud;
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String nomlocalitzacio, String latitud, String longitud) {
        super(fragmentManager, lifecycle);
        this.nomlocalitzacio = nomlocalitzacio;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FragmentCovidComments(nomlocalitzacio); //FER CREADORA
            case 2:
                return new Fragment_Mapa(nomlocalitzacio, latitud, longitud);
        }
        return new ComentarisFragment(nomlocalitzacio);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
