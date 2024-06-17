class PokemonData {
  late int id;
  late String name;
  late int height;
  late int order;
  late int weight;
  late String? frontDefault;

  PokemonData({
    required this.id,
    required this.name,
    required this.height,
    required this.order,
    required this.weight,
    required this.frontDefault,
  });

  factory PokemonData.fromJson(Map<String, dynamic> json) {
    return PokemonData(
      id: json['id'] as int,
      name: json['name'] as String,
      height: json['height'] as int,
      order: json['order'] as int,
      weight: json['weight'] as int,
      frontDefault: json['frontDefault'] as String? ?? 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVAaE0GIoWhYoTT8U8w2-daL-Fvrt0_8r1Fw&s',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'height': height,
      'order': order,
      'weight': weight,
      'front_default' : frontDefault,
    };
  }
}

