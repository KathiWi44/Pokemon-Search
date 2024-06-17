package com.example.PokeBackend;

public class PokemonSprites {
        private String front_default;

        public String getFrontDefault(PokemonSprites ps) {
            front_default= ps.front_default;
            return front_default;
        }

        public void setFrontDefault(String front_default) {
            this.front_default = front_default;
        }

}
