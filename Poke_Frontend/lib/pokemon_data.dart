class PokemonData {
  late int id;
  late String name;
  late int height;
  late int order;
  late int weight;

  PokemonData({
    required this.id,
    required this.name,
    required this.height,
    required this.order,
    required this.weight,
  });

  factory PokemonData.fromJson(Map<String, dynamic> json) {
    return PokemonData(
      id: json['id'] as int,
      name: json['name'] as String,
      height: json['height'] as int,
      order: json['order'] as int,
      weight: json['weight'] as int,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'height': height,
      'order': order,
      'weight': weight,
    };
  }
}

