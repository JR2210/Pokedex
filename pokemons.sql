CREATE DATABASE POKEMONS;
CREATE TABLE TEntrenador (
nEntrenadorID int PRIMARY KEY,
cNombre VARCHAR (60) NOT NULL,
cApodo VARCHAR (40)
);
CREATE TABLE TPokemon (
nPokemonID int PRIMARY KEY,
cNombre VARCHAR (40) NOT NULL,
cTipo VARCHAR (40),
nEntrenadorID int,
CONSTRAINT TPokemon_nEntrenadorID_TEntrenador FOREIGN KEY (nEntrenadorID)
REFERENCES TEntrenador (nEntrenadorID)
);

insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (001, "Satoshi", "Ash Ketchum");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (002, "Kasumi", "Misty");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (003, "Takeshi", "Brock");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (004, "Kenji", "Tracey Sketchit");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (005, "Haruka", "Aura");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (006, "Shigeru Okido", "Gary Oak");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (007, "Hikari", "Maya");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (008, "Airisu", "Iris");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (009, "Dento", "Millo");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (010, "Citron", "Lem");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (011, "Natsume", "Sabrina");
insert into TEntrenador (nEntrenadorID, cNombre, cApodo) VALUES (012, "Katsura", "Blaine");

insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (001, "Pikachu", "Electrico", 001);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (002, "Charizard", "Fuego", 001);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (003, "Bulbasaur", "Planta", 001);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (004, "Snorlax", "Normal", 001);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (005, "Horsea", "Agua", 002);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (006, "Psyduck", "Agua", 002);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (007, "Starmie", "Agua", 002);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (008, "Marill", "Agua", 004);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (009, "Venonat", "Bicho", 004);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (010, "Scyther", "Bicho", 004);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (011, "Onix", "Roca", 003);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (012, "Vulpix", "Fuego", 003);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (013, "Zubat", "Veneno", 003);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (014, "Plusle", "Electrico", 005);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (015, "Riolu", "Lucha", 005);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (016, "Piplup", "Agua", 007);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (017, "Leafeon", "Planta", 007);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (018, "Buneary", "Normal", 007);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (019, "Chespin", "Planta", 010);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (020, "Magneton", "Electrico", 010);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (021, "Sunflora", "Planta", 010);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (022, "Eevee", "Normal", 006);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (023, "Blastoise", "Agua", 006);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (024, "Alakazam", "Psiquico", 006);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (025, "Nidoking", "Veneno", 006);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (026, "Arcanine", "Fuego", 006);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (027, "Dragonite", "Dragon", 008);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (028, "Excadrill", "Tierra", 008);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (029, "Crustle", "Roca", 009);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (030, "Stunfisk", "Electrico", 009);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (031, "Mr. Mime", "Psiquico", 011);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (032, "Gengar", "Fantasma", 011);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (033, "Espeon", "Psiquico", 011);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (034, "Rapidash", "Fuego", 012);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (035, "Magmar", "Fuego", 012);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (036, "Magcargo", "Fuego", 012);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (037, "Squirtle", "Agua", 001);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (038, "Lapras", "Agua", 001);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (039, "Togepi", "Hada", 002);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (040, "Geodude", "Roca", 003);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (041, "Geodude", "Roca", 013);
insert into TPokemon (nPokemonID, cNombre, cTipo, nEntrenadorID) VALUES (042, "Geodude", "Roca", 013);