package com.example.affirmations.data
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.wanderlust_2024, R.drawable.australia),
            Affirmation(R.string.sunset_glory_2024, R.drawable.london),
            Affirmation(R.string.urban_dreams_2023, R.drawable.mexico),
            Affirmation(R.string.echoes_of_time_2025, R.drawable.newyork),
            Affirmation(R.string.mystic_horizon_2024, R.drawable.rome),
            Affirmation(R.string.timeless_beauty_2025, R.drawable.spain))
    }
}