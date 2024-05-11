import 'package:flutter/material.dart';
import 'package:poke_frontend/pokemon_data.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class PokemonResult extends StatelessWidget {
  final String pokemonName; // Name of the Pokemon to display
  const PokemonResult({Key? key, required this.pokemonName}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(pokemonName),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Features:',
              style: TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold),
            ),
            FutureBuilder<PokemonData>(
              future: fetchPokemonData(pokemonName),
              builder: (context, snapshot) {
                print('Snapshot: $snapshot');
                print('Snapshot data: ${snapshot.data}');
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return CircularProgressIndicator();
                } else if (snapshot.hasError) {
                  return Text('Error: ${snapshot.error}');
                } else if (snapshot.hasData) {
                  final pokemonData = snapshot.data!;
                  print('Pokemon data: $pokemonData');
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('ID: ${pokemonData.id}'),
                      Text('Name: ${pokemonData.name}'),
                      Text('Height: ${pokemonData.height}'),
                      Text('Order: ${pokemonData.order}'),
                      Text('Weight: ${pokemonData.weight}'),
                    ],
                  );
                } else if (snapshot.hasData == false) {
                  return Text('No data available, is null');
                } else {
                  return Text('No data available');
                }
              },
            ),
          ],
        ),
      ),
    );
  }

  Future<PokemonData> fetchPokemonData(String name) async {
    final response = await http.get(Uri.parse('http://localhost:8080/pokemon/$name'));

    if (response.statusCode == 200) {
      final jsonData = jsonDecode(response.body);
      print('JSON Data: $jsonData');
      return PokemonData.fromJson(jsonData);
    } else {
      print('Failed to fetch Pokémon data. Status Code: ${response.statusCode}');
      throw Exception('Failed to load Pokémon data');
    }
  }
}

