import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:poke_frontend/main.dart';
import 'package:integration_test/integration_test.dart';

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  group('end-to-end test', () {
    testWidgets('A valid Pokémon name is being used as input.',
            (tester) async {
          // Load app widget.
          await tester.pumpWidget(const MyApp());

          // Verify that the initial page is SelectServerPage
          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('Pokemon name'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

          await tester.enterText(find.byKey(const Key('pokemon_input')), 'mew');
          await tester.pumpAndSettle();

          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('mew'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

          await tester.tap(find.byKey(const Key('search_button')));
          await tester.pumpAndSettle();

          expect(find.text('Features:'), findsOneWidget);
          expect(find.text('ID: 11'), findsOneWidget);
          expect(find.text('Name: mew'), findsOneWidget);
          expect(find.text('Height: 4'), findsOneWidget);
          expect(find.text('Order: 248'), findsOneWidget);
          expect(find.text('Weight: 40'), findsOneWidget);
    });

    testWidgets('A Pokémon name that doesnt exist is being used as input.',
            (tester) async {
          // Load app widget.
          await tester.pumpWidget(const MyApp());

          // Verify that the initial page is SelectServerPage
          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('Pokemon name'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

          await tester.enterText(find.byKey(const Key('pokemon_input')), 'bikachu');
          await tester.pumpAndSettle();

          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('bikachu'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

          await tester.tap(find.byKey(const Key('search_button')));
          await tester.pumpAndSettle();

          expect(find.text('Features:'), findsOneWidget);
          expect(find.text('Error: Exception: Pokémon could not be found!'), findsOneWidget);
        });

    testWidgets('A Pokémon name with an invalid character is given as input.',
            (tester) async {
          // Load app widget.
          await tester.pumpWidget(const MyApp());

          // Verify that the initial page is SelectServerPage
          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('Pokemon name'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

          await tester.enterText(find.byKey(const Key('pokemon_input')), 'mr. mime');
          await tester.pumpAndSettle();

          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('mr. mime'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

          await tester.tap(find.byKey(const Key('search_button')));
          await tester.pumpAndSettle();

          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('mr. mime'), findsOneWidget);
          expect(find.text('Invalid Pokémon name. Only letters, numbers, and hyphens are allowed!'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);
        });

    testWidgets('No input is being given.',
            (tester) async {
          // Load app widget.
          await tester.pumpWidget(const MyApp());

          // Verify that the initial page is SelectServerPage
          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('Pokemon name'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

          await tester.tap(find.byKey(const Key('search_button')));
          await tester.pumpAndSettle();

          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('Pokemon name'), findsOneWidget);
          expect(find.text('Please enter the name of a Pokémon!'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);
        });
  });
}
