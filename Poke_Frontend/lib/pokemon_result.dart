import 'package:flutter/material.dart';
import 'package:poke_frontend/pokemon_data.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class PokemonResult extends StatelessWidget {
  final String pokemonName;
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
              future: fetchPokemonData(pokemonName, http.Client()),
              builder: (context, snapshot) {
                print('Snapshot: $snapshot');
                print('Snapshot data: ${snapshot.data}');
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return CircularProgressIndicator();
                } else if (snapshot.hasError) {
                  return Text('Error: ${snapshot.error}');
                } else if (snapshot.hasData) {
                  final pokemonData = snapshot.data!;
                  final String imageUrl = pokemonData.frontDefault.toString();
                  print('Pokemon data: $pokemonData');
                  print('Pokemon data: '+ pokemonData.frontDefault!);
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Image.network(
                        imageUrl,
                        loadingBuilder: (BuildContext context, Widget child, ImageChunkEvent? loadingProgress) {
                          if (loadingProgress == null) return child;
                          return Center(
                            child: CircularProgressIndicator(
                              value: loadingProgress.expectedTotalBytes != null
                                  ? loadingProgress.cumulativeBytesLoaded / loadingProgress.expectedTotalBytes!
                                  : null,
                            ),
                          );
                        },
                        errorBuilder: (BuildContext context, Object exception,
                            StackTrace? stackTrace) {
                          print(exception.toString());
                          print(stackTrace);
                          return Text('Failed to load image'); // You can customize error handling here
                        },
                      ),

                      //Image.network(pokemonData.frontDefault ?? 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.WCeMwp3K7pWorXDBHgeqFgHaFj%26pid%3DApi&f=1&ipt=cdde14c412dd21a9acf12b9b6a8b90c8428e93f48c6db743107f85774d7539d3&ipo=images'),
                      Text('ID: ${pokemonData.id}'),
                      Text('Name: ${pokemonData.name}'),
                      Text('Height: ${pokemonData.height}'),
                      Text('Order: ${pokemonData.order}'),
                      Text('Weight: ${pokemonData.weight}'),

                      //Text('Front Default: ${pokemonData.frontDefault}'),
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

  Future<PokemonData> fetchPokemonData(String name, http.Client client) async {
    final ipAddress = '127.0.0.1';
    final port = 8080;
    final response = await client
        .get(Uri.parse('http://$ipAddress:$port/pokemon/$name'));

    if (response.statusCode == 200) {
      final jsonData = jsonDecode(response.body);
      print('JSON Data: $jsonData');
      return PokemonData.fromJson(jsonData);
    } else {
      print(
          'Failed to fetch Pokémon data. Status Code: ${response.statusCode}');
      throw Exception('Pokémon could not be found!');
    }
  }
}
