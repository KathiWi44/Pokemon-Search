import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:poke_frontend/main.dart';

void main(){
  testWidgets('Test to look up a Pokemon', (tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(const MyApp());

    // Verify that the initial page is SelectServerPage
    expect(find.text('Whos that Pokémon?'), findsOneWidget);
    expect(find.text('Enter the name of the Pokémon:'), findsOneWidget);
    expect(find.text('Pokemon name'), findsOneWidget);
    expect(find.text('Search'), findsOneWidget);

    await tester.enterText(find.byKey(const Key('pokemon_input')), 'mew');
    await tester.pumpAndSettle();
    await tester.tap(find.byKey(const Key('search_button')));
    await tester.pumpAndSettle();

    expect(find.text('Features:'), findsOneWidget);
    expect(find.text('ID'), findsOneWidget);
    expect(find.text('Name'), findsOneWidget);
    expect(find.text('Height:'), findsOneWidget);
    expect(find.text('Order'), findsOneWidget);
    expect(find.text('Weight:'), findsOneWidget);


    /*
    // Tap the Next button and trigger a frame.
    await tester.tap(find.text('Next'));
    await tester.pumpAndSettle();

    // Verify that the WeatherPage is displayed without any data

    expect(find.text('Weather Page'), findsOneWidget);
    //expect(find.widgetWithIcon(UserOptions, Icons.person), findsOneWidget);
    expect(find.text('City'), findsOneWidget);
    expect(find.text('Get Weather'), findsOneWidget);
    expect(find.text('Location:'), findsNothing);
    expect(find.text('Date:'), findsNothing);
    expect(find.text('Weather:'), findsNothing);
    expect(find.text('Temperature:'), findsNothing);
    expect(find.text('Windspeed:'), findsNothing);
    expect(find.text('Humidity:'), findsNothing);
    expect(find.text('Sun hours:'), findsNothing);

    // Enter a city name and trigger a frame.

    await tester.pumpAndSettle();

    // Verify that the WeatherPage is displayed with the city name
    expect(find.text('Weather Page'), findsOneWidget);
    //expect(find.widgetWithIcon(UserOptions, Icons.person), findsOneWidget);
    expect(find.text('City'), findsOneWidget);
    expect(find.text('munich'), findsOneWidget);
    expect(find.text('Get Weather'), findsOneWidget);
    expect(find.text('Location:'), findsNothing);
    expect(find.text('Date:'), findsNothing);
    expect(find.text('Weather:'), findsNothing);
    expect(find.text('Temperature:'), findsNothing);
    expect(find.text('Windspeed:'), findsNothing);
    expect(find.text('Humidity:'), findsNothing);
    expect(find.text('Sun hours:'), findsNothing);

    // Tap the Get Weather button and trigger a frame.
    await tester.tap(find.byKey(const Key('search_button')));
    await tester.pumpAndSettle();

    // Verify that the WeatherPage is displayed with the weather data
    expect(find.text('Weather Page'), findsOneWidget);
    //expect(find.widgetWithIcon(UserOptions, Icons.person), findsOneWidget);
    expect(find.text('City'), findsOneWidget);
    expect(find.text('Get Weather'), findsOneWidget);
    expect(find.text('Location:'), findsOneWidget);
    expect(find.text('munich'), findsExactly(2));
    expect(find.text('Date:'), findsOneWidget);
    expect(find.text('Weather:'), findsOneWidget);
    expect(find.text('Temperature:'), findsOneWidget);
    expect(find.text('Windspeed:'), findsOneWidget);
    expect(find.text('Humidity:'), findsOneWidget);
    expect(find.text('Sun hours:'), findsOneWidget);

     */
  });


}