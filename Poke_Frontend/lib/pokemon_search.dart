import 'package:flutter/material.dart';
import 'pokemon_result.dart';

class PokemonSearch extends StatefulWidget {
  const PokemonSearch({Key? key}) : super(key: key);

  @override
  _PokemonSearchState createState() => _PokemonSearchState();
}

class _PokemonSearchState extends State<PokemonSearch> {
  final TextEditingController _controller = TextEditingController();
  String? _errorMessage;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Whos that Pokémon?'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Enter the name of the Pokémon:',
              style: TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10),
            TextField(
              controller: _controller,
              decoration: InputDecoration(
                hintText: 'Pokemon name',
                border: OutlineInputBorder(),
                errorText: _errorMessage,
              ),
            ),
            SizedBox(height: 10),
            ElevatedButton(
              onPressed: () {
                String pokemonName = _controller.text.trim();
                if (!inputValidation(pokemonName)) {
                  setState(() {
                    _errorMessage = 'Please enter the name of a Pokémon!';
                  });
                } else {
                  String sanitizedName = sanitizeInput(pokemonName);
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => PokemonResult(pokemonName: pokemonName)),
                  );
                }
              },
              child: Text('Search'),
            ),
          ],
        ),
      ),
    );
  }

  bool inputValidation(String name) {
    return name.isNotEmpty && RegExp(r'^[a-zA-Z0-9 ]+$').hasMatch(name);
  }

  String sanitizeInput(String input) {
    return input.replaceAll(RegExp(r'[^\w\s]+'), '');
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }
}
