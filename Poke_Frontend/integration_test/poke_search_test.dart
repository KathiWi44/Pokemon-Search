import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:poke_frontend/main.dart';
import 'package:integration_test/integration_test.dart';

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  group('end-to-end test', () {
    testWidgets('tap on the floating action button, verify counter',
            (tester) async {
          // Load app widget.
          await tester.pumpWidget(const MyApp());

          // Verify that the initial page is SelectServerPage
          expect(find.text('Whos that Pokémon?'), findsOneWidget);
          expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
          expect(find.text('Pokemon name'), findsOneWidget);
          expect(find.text('Search'), findsOneWidget);

        });
  });
}
